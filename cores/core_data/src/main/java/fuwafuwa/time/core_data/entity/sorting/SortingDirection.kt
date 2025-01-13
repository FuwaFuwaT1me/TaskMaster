package fuwafuwa.time.core_data.entity.sorting

enum class SortingDirection {
    NONE,
    ASCENDING,
    DESCENDING
}

fun SortingDirection.proceedType(): SortingDirection {
    val index = SortingDirection.entries.indexOf(this) + 1
    return SortingDirection.entries[index % 3]
}
