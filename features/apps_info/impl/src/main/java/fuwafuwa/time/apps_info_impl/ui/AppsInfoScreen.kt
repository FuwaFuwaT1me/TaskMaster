package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import fuwafuwa.time.apps_info_impl.mvi.AppsInfoViewModel

@Composable
fun AppsInfoScreen(
    viewModel: AppsInfoViewModel
) {
    val state by viewModel.model.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.apps) { app ->
                AppItem(app = app)
            }
        }
    }
}