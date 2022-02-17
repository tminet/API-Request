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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.databinding.FragmentUserAlbumsBinding

@AndroidEntryPoint
class UserAlbumsFragment : Fragment() {
    private var _binding: FragmentUserAlbumsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserAlbumsViewModel by viewModels()
    private val userAlbumsAdapter = UserAlbumsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentUserAlbumsBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeViewModel()

        binding.buttonRetry.setOnClickListener {
            viewModel.reloadUserAlbums()
        }
    }

    private fun setupAdapter() {
        userAlbumsAdapter.apply {
            albumClick = { albumId ->
                val directions =
                    UserAlbumsFragmentDirections.toAlbumPhotosFragment(albumId = albumId)
                findNavController().navigate(directions = directions)
            }
        }

        binding.recyclerViewUserAlbums.apply {
            setHasFixedSize(true)
            adapter = userAlbumsAdapter
        }
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collectLatest { uiState ->
                when (uiState) {
                    is UserAlbumsUiState.Loading -> binding.root.displayedChild = FLIPPER_LOADING
                    is UserAlbumsUiState.Success -> {
                        userAlbumsAdapter.submitList(uiState.albums)
                        binding.root.displayedChild = FLIPPER_SUCCESS
                    }
                    is UserAlbumsUiState.Error -> binding.apply {
                        textViewError.text = getString(uiState.message)
                        binding.root.displayedChild = FLIPPER_ERROR
                    }
                }
            }
        }
    }

    companion object {
        private const val FLIPPER_LOADING = 0
        private const val FLIPPER_SUCCESS = 1
        private const val FLIPPER_ERROR = 2
    }
}