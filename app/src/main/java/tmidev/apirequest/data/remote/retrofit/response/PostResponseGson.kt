package tmidev.apirequest.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import tmidev.apirequest.domain.model.Post

data class PostResponseGson(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) {
    fun toPost() = Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
