package tmidev.apirequest.presentation.screen_user_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentUserPostsBinding

@AndroidEntryPoint
class UserPostsFragment : Fragment() {
    private var _binding: FragmentUserPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserPostsViewModel by viewModels()
    private val userPostsAdapter = UserPostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentUserPostsBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeViewModel()

        binding.buttonRetry.setOnClickListener {
            viewModel.reloadUserPosts()
        }
    }

    private fun setupAdapter() {
        binding.recyclerViewUserPosts.apply {
            setHasFixedSize(true)
            adapter = userPostsAdapter
        }
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is UserPostsUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is UserPostsUiState.Success -> {
                        userPostsAdapter.submitList(uiState.posts)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is UserPostsUiState.Error -> binding.apply {
                        textViewError.text = getString(uiState.message)
                        binding.root.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_LOADING = 0
        private const val FLIPPER_SUCCESS = 1
        private const val FLIPPER_ERROR = 2
    }
}