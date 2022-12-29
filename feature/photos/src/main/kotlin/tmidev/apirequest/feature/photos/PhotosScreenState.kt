package tmidev.apirequest.feature.photos

import tmidev.apirequest.core.model.Photo

sealed interface PhotosScreenState {
    object Loading : PhotosScreenState

    data class Success(
        val photos: List<Photo>
    ) : PhotosScreenState

    data class Error(
        val stringRes: Int
    ) : PhotosScreenState
}