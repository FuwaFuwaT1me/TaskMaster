package fuwafuwa.time.core_data.entity.sorting

import fuwafuwa.time.core.model.app.App

sealed interface SortingType {

    val field: (App) -> Comparable<*>
    val name: String

    fun getComparator(sortingDirection: SortingDirection): Comparator<App>? {
        return when (sortingDirection) {
            SortingDirection.NONE -> null
            SortingDirection.ASCENDING -> compareBy(field)
            SortingDirection.DESCENDING -> compareByDescending(field)
        }
    }

    data object Size : SortingType {

        override val field: (App) -> Comparable<*> = { app ->
            app.totalSize
        }
        override val name = "size"
    }

    data object Name : SortingType {

        override val field: (App) -> Comparable<*> = { app ->
            app.name.lowercase()
        }
        override val name = "name"
    }
}
