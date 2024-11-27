package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fuwafuwa.time.core.model.app.App

@Composable
fun AppItem(app: App) {
    Text(text = app.name)
}