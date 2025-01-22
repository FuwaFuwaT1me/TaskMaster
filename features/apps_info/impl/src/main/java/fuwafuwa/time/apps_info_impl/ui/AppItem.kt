package fuwafuwa.time.apps_info_impl.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core_compose.theme.LightGrayBlue
import fuwafuwa.time.utli.bitmap.saveAsPngToFile
import java.io.File

@Composable
fun AppItem(
    app: App,
    onClick: () -> Unit
) {
    val isNameExist = app.name != app.packageName

    Row(
        modifier = Modifier
            .background(
                color = LightGrayBlue,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(40.dp)
                .height(40.dp)
                .weight(0.15f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(app.iconPath)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(0.6f)
                .align(Alignment.CenterVertically)
        ) {
            if (isNameExist) {
                Text(
                    text = app.name,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = app.packageName,
                fontSize = if (isNameExist) 12.sp else 16.sp,
                fontWeight = if (isNameExist) FontWeight.Normal else FontWeight.Bold,
                lineHeight = if (isNameExist) 12.sp else 16.sp
            )
            Text(
                text = "Version: ${app.versionName}",
                fontSize = 12.sp,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        AppSizeBar(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            app = app
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AppItemPreview() {
    val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint().apply {
        style = Paint.Style.FILL
        color = android.graphics.Color.BLUE
    }
    canvas.drawRect(0f, 0f, 100f, 100f, paint)

    val file = File.createTempFile("k-on", "bocchi")
    bitmap.saveAsPngToFile(file)

    AppItem(
        app = App(
            name = "K-on beat game",
            packageName = "k.on.fuwafuwatime",
            processName = "k.process.on",
            apkSize = 1256,
            appSize = 388,
            dataSize = 3821,
            cacheSize = 421238,
            totalSize = 123123,
            iconPath = file.absolutePath,
            versionName = "1.25.7.8.20",
            versionCode = 256
        )
    ) {}
}
