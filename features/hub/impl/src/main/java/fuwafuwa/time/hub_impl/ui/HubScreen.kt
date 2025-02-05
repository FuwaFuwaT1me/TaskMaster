package fuwafuwa.time.hub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fuwafuwa.time.hub.ScreensProvider
import fuwafuwa.time.hub_impl.mvi.HubViewModel

@Composable
fun HubScreen(
    viewModel: HubViewModel
) {
    val screensProvider = ScreensProvider()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(screensProvider.get()) { screen ->
            Row(
                modifier = Modifier
            ) {
                Image(
                    imageVector = Icons.AutoMirrored.Outlined.List,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = screen.title
                )
            }
        }
    }
}
