package fuwafuwa.time.core_data.entity.sorting

data class AppSortingProperty(
    val sortingType: SortingType,
    val sortingDirection: SortingDirection = SortingDirection.NONE
)
