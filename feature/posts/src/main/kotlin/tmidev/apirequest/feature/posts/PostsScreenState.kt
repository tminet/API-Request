package tmidev.apirequest.feature.posts

import tmidev.apirequest.core.model.Post

sealed interface PostsScreenState {
    object Loading : PostsScreenState

    data class Success(
        val posts: List<Post>
    ) : PostsScreenState

    data class Error(
        val stringRes: Int
    ) : PostsScreenState
}