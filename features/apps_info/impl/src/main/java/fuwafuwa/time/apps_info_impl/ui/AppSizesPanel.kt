package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core_compose.theme.GrayBlue
import fuwafuwa.time.utli.data.DataConverter

@Composable
fun AppSizeBar(
    modifier: Modifier,
    app: App
) {
    val totalSize = app.appSize + app.dataSize + app.cacheSize
    val displayedDataUnit = DataConverter.convertBytesToNearestType(totalSize)

    Box(
        modifier = modifier
            .border(
                border = BorderStroke(2.dp, GrayBlue),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = "${displayedDataUnit.size} ${displayedDataUnit.name}",
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun AppSizeBarPreview() {
    AppSizeBar(
        modifier = Modifier,
        app = App(
            name = "K-on beat game",
            packageName = "k.on.fuwafuwatime",
            processName = "k.process.on",
            apkSize = 1256,
            appSize = 1,
            dataSize = 2,
            cacheSize = 3,
            totalSize = 123,
            iconPath = "",
            versionName = "1.25.7.8.20",
            versionCode = 256
        )
    )
}
