package tmidev.apirequest.presentation.screen_user_albums

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
import tmidev.apirequest.databinding.FragmentAlbumsBinding
import tmidev.apirequest.presentation.common.genericAdapterOf

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AlbumsViewModel by viewModels()

    private val albumsAdapter: ListAdapter<AlbumItem, AlbumsViewHolder> by lazy {
        genericAdapterOf {
            AlbumsViewHolder.create(parent = it) { albumId ->
                val directions =
                    AlbumsFragmentDirections.toPhotosFragment(albumId = albumId)
                findNavController().navigate(directions = directions)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAlbumsBinding.inflate(
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

    private fun setupRecyclerView() = binding.recyclerViewUserAlbums.run {
        setHasFixedSize(true)
        adapter = albumsAdapter
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is AlbumsUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is AlbumsUiState.Success -> {
                        albumsAdapter.submitList(uiState.albums)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is AlbumsUiState.Error -> binding.run {
                        textViewError.text = getString(uiState.message)
                        binding.root.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    private fun setupReloadListener() = binding.buttonRetry.run {
        setOnClickListener {
            viewModel.reloadUserAlbums()
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