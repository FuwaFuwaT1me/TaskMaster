package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.utli.number.round

@Composable
fun AppItem(
    app: App,
    onClick: () -> Unit
) {
    val isNameExist = app.name != null

    Row(
        Modifier.clickable {
            onClick()
        }
    ) {
        AsyncImage(
            modifier = Modifier
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
        ) {
            if (isNameExist) {
                Text(
                    text = app.name ?: "", // Never null here
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = app.packageName,
                fontSize = 12.sp,
                fontWeight = if (isNameExist) FontWeight.Normal else FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Text(
                text = "App = ${app.appSize.round(2)}"
            )
            Text(
                text = "Data = ${app.dataSize.round(2)}"
            )
            Text(
                text = "Cache = ${app.cacheSize.round(2)}"
            )
        }
    }
}
