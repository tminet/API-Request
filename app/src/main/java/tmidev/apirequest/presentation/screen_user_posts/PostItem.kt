package tmidev.apirequest.presentation.screen_user_posts

import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.presentation.common.GenericListItem

data class PostItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    override val key: Long = id.toLong()
) : GenericListItem

fun List<Post>.toPostsItem() = map { post ->
    PostItem(
        userId = post.userId,
        id = post.id,
        title = post.title,
        body = post.body
    )
}