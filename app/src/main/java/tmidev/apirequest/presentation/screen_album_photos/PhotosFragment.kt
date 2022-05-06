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
import androidx.recyclerview.widget.ListAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentPhotosBinding
import tmidev.apirequest.presentation.common.genericAdapterOf
import tmidev.apirequest.util.ImageLoader
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotosViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val photosAdapter: ListAdapter<PhotoItem, PhotosViewHolder> by lazy {
        genericAdapterOf {
            PhotosViewHolder.create(
                parent = it,
                imageLoader = imageLoader
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentPhotosBinding.inflate(
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
            adapter = photosAdapter
        }
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is PhotosUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is PhotosUiState.Success -> {
                        photosAdapter.submitList(uiState.photos)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is PhotosUiState.Error -> binding.run {
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