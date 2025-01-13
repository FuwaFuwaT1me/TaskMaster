package fuwafuwa.time.core_data.entity.sorting

import fuwafuwa.time.core.model.app.App
import kotlin.reflect.KProperty1

sealed interface SortingType {

    val field: KProperty1<App, Comparable<*>>
    val name: String

    fun getComparator(sortingDirection: SortingDirection): Comparator<App>? {
        return when (sortingDirection) {
            SortingDirection.NONE -> null
            SortingDirection.ASCENDING -> compareBy(field)
            SortingDirection.DESCENDING -> compareByDescending(field)
        }
    }

    data object Size : SortingType {

        override val field = App::totalSize
        override val name = "size"
    }

    data object Name : SortingType {

        override val field = App::name
        override val name = "name"
    }
}
