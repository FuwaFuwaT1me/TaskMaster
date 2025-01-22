package fuwafuwa.time.apps_info_impl.usecase

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core_data.entity.sorting.AppSortingProperty
import javax.inject.Inject

class SortingAppsUseCase @Inject constructor() {

    fun sort(apps: List<App>, sortingProperties: List<AppSortingProperty>): List<App> {
        if (sortingProperties.isEmpty()) return emptyList()
        val comparator = getComparator(sortingProperties)
        return comparator?.let {
            apps.sortedWith(comparator)
        } ?: apps
    }

    private fun getComparator(
        sortingProperties: List<AppSortingProperty>
    ): Comparator<App>? {
        return sortingProperties.fold(null as Comparator<App>?) { acc, sortingProperty ->
            val newComparator = sortingProperty.sortingType.getComparator(
                sortingProperty.sortingDirection
            )
            newComparator?.let {
                acc?.then(it) ?: it
            } ?: acc
        }
    }
}
