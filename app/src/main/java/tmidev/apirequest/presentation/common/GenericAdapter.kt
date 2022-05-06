package tmidev.apirequest.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

inline fun <T : GenericListItem, VH : GenericViewHolder<T>> genericAdapterOf(
    crossinline createViewHolder: (ViewGroup) -> VH
): ListAdapter<T, VH> {
    val diffCallback = GenericDiffCallback<T>()

    return object : ListAdapter<T, VH>(diffCallback) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            createViewHolder(parent)

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(data = getItem(position))
        }
    }
}