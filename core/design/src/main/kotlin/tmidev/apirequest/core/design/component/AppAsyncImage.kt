package tmidev.apirequest.core.design.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

/**
 * Compose an Image with [AsyncImagePainter].
 *
 * @param model the image model to be displayed.
 * @param contentDescription text used by accessibility services. This value will be used only if
 * the [model] is successfully loaded.
 * @param errorIcon the [ImageVector] to represents a error state.
 * @param size [Dp] value of this image.
 * @param innerPadding [Dp] value to be applied as inner padding on this image. Default is 0.
 *
 * @see [rememberAsyncImagePainter].
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppAsyncImage(
    model: Any,
    contentDescription: String,
    errorIcon: ImageVector,
    size: Dp,
    innerPadding: Dp = 0.dp
) {
    val modifier = Modifier
        .size(size = size)
        .padding(all = innerPadding)

    val rememberAsyncImage = rememberAsyncImagePainter(model = model)
    val isImageStateError = rememberAsyncImage.state is AsyncImagePainter.State.Error

    AnimatedContent(targetState = isImageStateError) { isError ->
        when (isError) {
            true -> Image(
                modifier = modifier,
                imageVector = errorIcon,
                contentDescription = null
            )
            false -> Image(
                modifier = modifier,
                painter = rememberAsyncImage,
                contentDescription = contentDescription
            )
        }
    }
}