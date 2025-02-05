package fuwafuwa.time.hub_impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.core_compose.theme.GrayBlue
import fuwafuwa.time.hub_impl.HubScreenInfo
import fuwafuwa.time.hub_impl.ScreensProvider
import fuwafuwa.time.hub_impl.mvi.HubViewModel

@Composable
fun HubScreen(
    viewModel: HubViewModel
) {
    val state by viewModel.model.state.collectAsState()

    ScreensGrid(state.hubScreenInfos) { screen ->
        screen.navigateByScreen(viewModel)
    }
}

@Composable
private fun ScreensGrid(
    hubScreenInfos: List<HubScreenInfo>,
    onNavigateTo: (HubScreenInfo) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBlue)
            .padding(12.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(hubScreenInfos) { screen ->
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .clickable {
                        onNavigateTo(screen)
                    }
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth()
                        .height(120.dp),
                    imageVector = screen.icon,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = screen.title
                )

                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

private fun HubScreenInfo.navigateByScreen(viewModel: HubViewModel) {
    when (this) {
        HubScreenInfo.AppsInfo -> viewModel.sendNavigationEvent(AppsInfoNavEvent())
        HubScreenInfo.Processes -> TODO()
    }
}

@Preview
@Composable
fun HubScreenPreview() {
    ScreensGrid(ScreensProvider().get()) {}
}
