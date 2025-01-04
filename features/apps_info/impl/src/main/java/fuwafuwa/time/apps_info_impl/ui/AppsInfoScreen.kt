package fuwafuwa.time.apps_info_impl.ui

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fuwafuwa.time.apps_info_impl.mvi.AppsInfoViewModel

@Composable
fun AppsInfoScreen(
    viewModel: AppsInfoViewModel
) {
    val state by viewModel.model.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (state.permissionConfig.usageStatsPermission) {
            viewModel.getApps()
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
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.apps) { app ->
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

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}