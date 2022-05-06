package tmidev.apirequest.presentation.common

interface GenericListItem {
    val key: Long

    fun areItemsTheSame(
        other: GenericListItem
    ): Boolean = this.key == other.key

    fun areContentsTheSame(
        other: GenericListItem
    ): Boolean = this == other
}