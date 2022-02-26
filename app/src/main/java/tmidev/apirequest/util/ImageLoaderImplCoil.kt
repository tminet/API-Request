package tmidev.apirequest.util

import android.widget.ImageView
import coil.load
import javax.inject.Inject

class ImageLoaderImplCoil @Inject constructor() : ImageLoader {
    override fun invoke(
        imageView: ImageView,
        imageUrl: String
    ) {
        imageView.load(imageUrl)
    }
}