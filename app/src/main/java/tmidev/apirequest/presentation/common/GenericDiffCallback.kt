package tmidev.apirequest.presentation.common

import androidx.recyclerview.widget.DiffUtil

class GenericDiffCallback<T : GenericListItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ) = oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ) = oldItem.areContentsTheSame(newItem)
}