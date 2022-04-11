package tmidev.apirequest.presentation.screen_album_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentAlbumPhotosBinding
import tmidev.apirequest.util.ImageLoader
import javax.inject.Inject

@AndroidEntryPoint
class AlbumPhotosFragment : Fragment() {
    private var _binding: FragmentAlbumPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AlbumPhotosViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val albumPhotosAdapter: AlbumPhotosAdapter by lazy {
        AlbumPhotosAdapter(imageLoader = imageLoader)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAlbumPhotosBinding.inflate(
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

    private fun setupRecyclerView() {
        val flexboxLayoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.SPACE_EVENLY
        }

        binding.recyclerViewAlbumPhotos.run {
            setHasFixedSize(true)
            layoutManager = flexboxLayoutManager
            adapter = albumPhotosAdapter
        }
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is AlbumPhotosUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is AlbumPhotosUiState.Success -> {
                        albumPhotosAdapter.submitList(uiState.photos)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is AlbumPhotosUiState.Error -> binding.apply {
                        textViewError.text = getString(uiState.message)
                        binding.root.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    private fun setupReloadListener() = binding.buttonRetry.run {
        setOnClickListener {
            viewModel.reloadAlbumPhotos()
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