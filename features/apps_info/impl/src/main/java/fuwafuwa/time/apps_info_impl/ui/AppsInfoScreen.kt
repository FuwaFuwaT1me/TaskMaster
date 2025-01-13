package fuwafuwa.time.apps_info_impl.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fuwafuwa.time.apps_info_impl.R
import fuwafuwa.time.apps_info_impl.mvi.AppsInfoViewModel
import fuwafuwa.time.apps_info_impl.mvi.ChangeSortingProperty
import fuwafuwa.time.core_compose.theme.GrayBlue
import fuwafuwa.time.core_data.entity.sorting.proceedType
import fuwafuwa.time.utli.context.getActivity

@Composable
fun AppsInfoScreen(
    viewModel: AppsInfoViewModel
) {
    val state by viewModel.model.state.collectAsState()
    val context = LocalContext.current
    var showFilters by remember { mutableStateOf(false) }

    val apps = if (state.searchString.isNotEmpty()) {
        state.filteredApps
    } else if (state.sortedApps.isNotEmpty()) {
        state.sortedApps
    } else {
        state.apps
    }

    LaunchedEffect(Unit) {
        if (state.permissionConfig.usageStatsPermission) {
            viewModel.updateApps()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!state.permissionConfig.usageStatsPermission) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("You haven't granted permission to read applications yet")
                Button(
                    onClick = {
                        context.getActivity()?.let {
                            viewModel.grantUsageStatsPermission(it)
                        }
                    }
                ) {
                    Text(
                        text = "Grant permission"
                    )
                }
            }
        } else if (state.apps.isEmpty()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Reading applications..."
                )
                Spacer(modifier = Modifier.size(8.dp))
                CircularProgressIndicator(
                    color = Color.Blue
                )
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchBar(
                        modifier = Modifier.weight(1f)
                            .height(52.dp),
                        debounceDuration = 500L
                    ) { searchString ->
                        viewModel.searchForApps(searchString)
                    }

                    Button(
                        modifier = Modifier.height(52.dp)
                            .width(52.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GrayBlue
                        ),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            showFilters = !showFilters
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.filter),
                            "",
                            modifier = Modifier.size(36.dp),
                            tint = Color.White
                        )
                    }
                }

                if (showFilters) {
                    SortingList(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        sorters = state.sortingProperties,
                        onSorterSelected = { sorter ->
                            val changedIndex = state.sortingProperties.indexOf(sorter)
                            val newSortingProperties = state.sortingProperties.mapIndexed { index, sortingProperty ->
                                if (index == changedIndex) {
                                    sortingProperty.copy(sortingDirection = sortingProperty.sortingDirection.proceedType())
                                } else {
                                    sortingProperty
                                }
                            }

                            viewModel.sendAction(
                                ChangeSortingProperty(newSortingProperties)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(4.dp))
                }

                if (state.searchInProgress) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Searching for applications..."
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            CircularProgressIndicator(
                                color = GrayBlue
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Text(
                                modifier = Modifier
                                    .background(GrayBlue, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                                    .padding(horizontal = 6.dp, vertical = 8.dp),
                                text = "Total: ${apps.size}",
                                color = Color.White
                            )
                        }

                        items(apps) { app ->
                            AppItem(app = app) {
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                intent.setData(Uri.parse("package:${app.packageName}"))
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}
