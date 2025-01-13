package fuwafuwa.time.apps_info_impl.filter

import fuwafuwa.time.core_data.entity.sorting.AppSortingProperty
import fuwafuwa.time.core_data.entity.sorting.SortingType

class SorterProvider {

    fun provide(): List<AppSortingProperty> {
        return listOf(
            AppSortingProperty(sortingType = SortingType.Size),
            AppSortingProperty(sortingType = SortingType.Name),
        )
    }
}