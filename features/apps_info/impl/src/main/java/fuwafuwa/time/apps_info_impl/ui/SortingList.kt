package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fuwafuwa.time.core_data.entity.sorting.AppSortingProperty
import fuwafuwa.time.core_data.entity.sorting.SortingDirection

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SortingList(
    sorters: List<AppSortingProperty>,
    onSorterSelected: (AppSortingProperty) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        sorters.forEach { sortingProperty ->
            FilterChip(
                label = {
                    Text(
                        text = sortingProperty.sortingType.name
                    )
                },
                selected = sortingProperty.sortingDirection != SortingDirection.NONE,
                onClick = {
                    onSorterSelected(sortingProperty)
                },
                trailingIcon = {
                    when (sortingProperty.sortingDirection) {
                        SortingDirection.NONE -> {}
                        SortingDirection.ASCENDING -> {
                            Icon(Icons.Filled.KeyboardArrowUp, "")
                        }
                        SortingDirection.DESCENDING -> {
                            Icon(Icons.Filled.KeyboardArrowDown, "")
                        }
                    }
                }
            )
        }
    }
}
