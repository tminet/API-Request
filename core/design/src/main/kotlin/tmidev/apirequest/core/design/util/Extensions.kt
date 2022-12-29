package tmidev.apirequest.core.design.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * Check if the device is compatible with Dynamic Colors for Material 3.
 *
 * @return true when device is API 31 (Android 12) or up, false otherwise.
 */
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun isCompatibleWithDynamicColors(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S