package tmidev.apirequest.presentation.screen_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentUsersBinding
import tmidev.apirequest.presentation.common.genericAdapterOf

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsersViewModel by viewModels()

    private val usersAdapter: ListAdapter<UserItem, UsersViewHolder> by lazy {
        genericAdapterOf {
            UsersViewHolder.create(
                parent = it,
                userPostsClick = { userId ->
                    val directions = UsersFragmentDirections.toPostsFragment(userId = userId)
                    findNavController().navigate(directions = directions)
                },
                userAlbumsClick = { userId ->
                    val directions = UsersFragmentDirections.toAlbumsFragment(userId = userId)
                    findNavController().navigate(directions = directions)
                }
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupSwipeRefreshListener()
    }

    private fun setupRecyclerView() = binding.recyclerViewUsers.run {
        setHasFixedSize(true)
        adapter = usersAdapter
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is UsersUiState.Loading -> binding.root.isRefreshing = true
                    is UsersUiState.Success -> {
                        binding.root.isRefreshing = false
                        usersAdapter.submitList(uiState.users)
                        binding.viewFlipperData.displayedChild = FLIPPER_SUCCESS
                    }
                    is UsersUiState.Error -> binding.run {
                        root.isRefreshing = false
                        textViewError.text = getString(uiState.message)
                        viewFlipperData.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    private fun setupSwipeRefreshListener() = binding.root.run {
        setOnRefreshListener {
            isRefreshing = true
            viewModel.reloadUsers()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_SUCCESS = 0
        private const val FLIPPER_ERROR = 1
    }
}