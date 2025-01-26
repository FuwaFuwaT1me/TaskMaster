package fuwafuwa.time.core_compose.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ChartPart(
    val percent: Float,
    val color: Color
)

@Composable
fun CircularDiagram(
    parts: List<ChartPart>,
    strokeWidth: Float,
    useCenter: Boolean = false
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val maxAngle by animateFloatAsState(
        targetValue = if (animationPlayed) 360f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        label = ""
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier.size(300.dp)
    ) {
        Canvas(
            modifier = Modifier.size(200.dp).align(Alignment.Center)
        ) {
            var currentEnd = 0f
            parts.forEach { part ->

                val sweepAngle = part.percent.mapValueToDifferentRange(
                    inMin = 0f,
                    inMax = 100f,
                    outMin = 0f,
                    outMax = maxAngle
                )

                drawArc(
                    color = part.color,
                    startAngle = currentEnd,
                    sweepAngle = sweepAngle,
                    useCenter = useCenter,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(size.width - strokeWidth, size.height - strokeWidth),
                    style = if (useCenter) {
                        Fill
                    } else {
                        Stroke(width = strokeWidth)
                    }
                )

                currentEnd += sweepAngle
            }
        }
    }
}

fun Float.mapValueToDifferentRange(
    inMin: Float,
    inMax: Float,
    outMin: Float,
    outMax: Float
) = (this - inMin) * (outMax - outMin) / (inMax - inMin) + outMin

@Preview
@Composable
fun PreviewCircularDiagram() {
    CircularDiagram(
        parts = listOf(
            ChartPart(12f, Color(0xfff65e58)),
            ChartPart(38f, Color(0xff1b3361)),
            ChartPart(22f, Color(0xff486198)),
            ChartPart(28f, Color(0xffffb558)),
        ),
        useCenter = true,
        strokeWidth = 80f
    )
}
