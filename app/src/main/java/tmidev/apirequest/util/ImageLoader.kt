package tmidev.apirequest.util

import android.widget.ImageView

interface ImageLoader {
    operator fun invoke(
        imageView: ImageView,
        imageUrl: String
    )
}