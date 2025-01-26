package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.model.app.AppSize
import fuwafuwa.time.core.model.app.CacheSize
import fuwafuwa.time.core.model.app.DataSize
import fuwafuwa.time.core.model.app.SpaceUsedSize

@Composable
fun AppSizeDialog(app: App) {
    val sortedSizes = sortSizes(app)

    Row(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            sortedSizes.forEach { size ->
                Text(
                    text = "${size.title}: ${size.size}"
                )
            }
        }
    }
}

private fun sortSizes(app: App): List<SpaceUsedSize> {
    return listOf(app.appSize, app.dataSize, app.cacheSize).sortedByDescending {
        it.size
    }
}

@Preview
@Composable
fun AppSizeDialogPreview() {
    AppSizeDialog(
        app = App(
            name = "K-on beat game",
            packageName = "k.on.fuwafuwatime",
            processName = "k.process.on",
            apkSize = 1256,
            appSize = AppSize(1),
            dataSize = DataSize(2),
            cacheSize = CacheSize(3),
            totalSize = 123,
            iconPath = "",
            versionName = "1.25.7.8.20",
            versionCode = 256
        )
    )
}
