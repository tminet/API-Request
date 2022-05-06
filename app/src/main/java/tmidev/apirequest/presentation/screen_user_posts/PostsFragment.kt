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
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentPostsBinding
import tmidev.apirequest.presentation.common.genericAdapterOf

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostsViewModel by viewModels()

    private val postsAdapter: ListAdapter<PostItem, PostsViewHolder> by lazy {
        genericAdapterOf {
            PostsViewHolder.create(parent = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentPostsBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupReloadListener()
    }

    private fun setupRecyclerView() = binding.recyclerViewUserPosts.run {
        setHasFixedSize(true)
        adapter = postsAdapter
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is PostsUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is PostsUiState.Success -> {
                        postsAdapter.submitList(uiState.posts)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is PostsUiState.Error -> binding.run {
                        textViewError.text = getString(uiState.message)
                        binding.root.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    private fun setupReloadListener() = binding.buttonRetry.run {
        setOnClickListener {
            viewModel.reloadUserPosts()
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