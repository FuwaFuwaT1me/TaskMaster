package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.model.app.AppSize
import fuwafuwa.time.core.model.app.CacheSize
import fuwafuwa.time.core.model.app.DataSize
import fuwafuwa.time.core.model.app.SpaceUsedSize
import fuwafuwa.time.core_compose.components.ChartPart
import fuwafuwa.time.core_compose.components.CircularDiagram
import fuwafuwa.time.utli.data.DataConverter

@Composable
fun AppSizeDialog(
    app: App,
    modifier: Modifier = Modifier
) {
    val sortedSizes = sortSizes(app)
    val parts = sortedSizes.map {
        ChartPart(
            percent = 100f * it.size / app.totalSize,
            color = getColorBySize(it)
        )
    }

    Row(
        modifier = modifier
            .height(150.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sortedSizes.forEach { size ->
                val convertedSize = DataConverter.convertBytesToNearestType(size.size)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Canvas(
                        modifier = Modifier.size(8.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        drawCircle(color = getColorBySize(size))
                    }
                    Text(
                        text = "${size.title}: ${convertedSize.size} ${convertedSize.name}"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(32.dp))

        CircularDiagram(
            modifier = Modifier.size(120.dp)
                .align(Alignment.CenterVertically),
            parts = parts,
            useCenter = true
        )
    }
}

private fun sortSizes(app: App): List<SpaceUsedSize> {
    return listOf(app.appSize, app.dataSize, app.cacheSize).sortedByDescending {
        it.size
    }
}

private fun getColorBySize(size: SpaceUsedSize): Color {
    return when (size) {
        is AppSize -> Color(0xfff65e58)
        is CacheSize -> Color(0xff486198)
        is DataSize -> Color(0xffffb558)
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
