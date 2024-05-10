package dev.vaibhav.mbits.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vaibhav.mbits.presentation.BreathingToolScreenUiState
import dev.vaibhav.mbits.ui.theme.color1
import dev.vaibhav.mbits.ui.theme.color2
import dev.vaibhav.mbits.ui.theme.color3
import dev.vaibhav.mbits.ui.theme.color4
import dev.vaibhav.mbits.ui.theme.color5
import dev.vaibhav.mbits.ui.theme.color6
import dev.vaibhav.mbits.ui.theme.color7
import dev.vaibhav.mbits.ui.theme.color8
import dev.vaibhav.mbits.ui.theme.interFamily
import dev.vaibhav.mbits.ui.theme.whiteTransparent
import dev.vaibhav.mbits.util.convertValueToAngle
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt


@Composable
fun DurationSelector(
    state: BreathingToolScreenUiState,
    currentDuration: Float,
    onDurationChange: (Float) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(color = whiteTransparent)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomCircularValueSelector(
            currentValue = currentDuration,
            onPositionChange = {
                onDurationChange(it)
            }
        )


        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            BreathingToolStatItem(time = state.toolData.breathingProgressData.recommended, title = "Recommended")
            BreathingToolStatItem(
                time = state.toolData.breathingProgressData.target,
                title = "Goal",
                brush = Brush.horizontalGradient(
                    listOf(
                        color1, color2, color3, color4, color5, color6, color7, color8),
                )
            )
            BreathingToolStatItem(time = state.toolData.breathingProgressData.achieved, title = "Achieved")


        }

    }

}



@Composable
fun BreathingToolStatItem(
    time: Int,
    brush: Brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
    title: String
) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "$time",
            fontFamily = interFamily,
            fontWeight = FontWeight.W700,
            fontSize = 14.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(3.dp)
                .background(brush = brush),
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = title,
            fontFamily = interFamily,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.White
        )

    }
}
@Composable
fun CustomCircularValueSelector(
    currentValue: Float = 60f,// from 0 - 60 mins
    maxValue: Int = 60,
    onPositionChange: (Float) -> Unit

) {

    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    var positionValue by remember{ mutableStateOf(currentValue) }// our arc is in 270 degree

    var changeAngle by remember{ mutableStateOf(0f) }

    var dragStartedAngle by remember { mutableStateOf(0f) }


    var oldPositionValue by remember { mutableStateOf(currentValue) }


    Column(
        modifier = Modifier
            .size(220.dp)
            .drawBehind {
                val componentSize = size / 1.25f
                circleCenter = Offset(size.width / 2f, size.height / 2f)

                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = Color.White,
                    indicatorStrokeWidth = 100f,
                )
                foregroundIndicator(
                    sweepAngle = (positionValue / 60f) * 270,
                    componentSize = componentSize,
                    indicatorStrokeWidth = 100f,

                    )
            }
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedAngle = -atan2(
                            x = circleCenter.x - offset.x,
                            y = circleCenter.y - offset.y
                        ) * (180f / PI).toFloat() // this gives an angle from -180 degree to 180 degree

                        dragStartedAngle = (dragStartedAngle + 180f).mod(360f)
                    },
                    onDrag = { change, _ ->
                        var touchAngle = -atan2(
                            y = circleCenter.y - change.position.y,
                            x = circleCenter.x - change.position.x
                        ) * (180f / PI).toFloat()
                        touchAngle = (touchAngle + 180f).mod(360f)


                        val currentAngle = convertValueToAngle(oldPositionValue)


                        if (touchAngle !in 225f..315f) {
                            changeAngle = if (
                                (touchAngle in 0f..225f && currentAngle in 0f..225f) ||
                                (touchAngle in 315f..360f && currentAngle in 315f..360f)
                            ) {
                                currentAngle - touchAngle
                            } else if (touchAngle in 315f..360f && currentAngle in 0f..225f) {
                                360 - touchAngle + currentAngle
                            } else {
                                -(360 - currentAngle + touchAngle)
                            }
                        } else {
                            return@detectDragGestures
                        }

                        val lowerThreshold = (currentAngle - 20).mod(360f)
                        val higherThreshold = (currentAngle + 20).mod(360f)


                        if (higherThreshold > lowerThreshold) {
                            if (dragStartedAngle !in 225f..315f && dragStartedAngle in lowerThreshold..higherThreshold) {
                                val newValue =
                                    (oldPositionValue + (changeAngle * (maxValue / 270f)).roundToInt())
                                positionValue = if (newValue < 0f) {
                                    0f
                                } else if (newValue > 60f) {
                                    60f
                                } else {
                                    newValue
                                }
                            }
                        } else {
                            if (
                                dragStartedAngle !in 225f..315f &&
                                (dragStartedAngle in lowerThreshold..360f || dragStartedAngle in 0f..higherThreshold)
                            ) {
                                val newValue =
                                    (oldPositionValue + (changeAngle * (maxValue / 270f)).roundToInt())

                                positionValue = if (newValue < 0f) {
                                    0f
                                } else if (newValue > 60f) {
                                    60f
                                } else {
                                    newValue
                                }
                            }
                        }


                    },
                    onDragEnd = {
                        oldPositionValue = positionValue
                        onPositionChange(positionValue)
                    }
                )
            }

        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(
            time = positionValue.toInt()
        )
    }

}
//
fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 135f,
        sweepAngle = 270f,
        useCenter = false,
        style = Stroke(
            width = 30f,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        brush = Brush.sweepGradient(listOf(color1, color2, color3, color4, color5, color6, color7, color8)),
        size = componentSize,
        startAngle = 135f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = 30f,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElements(
    time: Int
) {

    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Duration",
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = interFamily,
            fontWeight = FontWeight.W400
        )
        Text(
            text = "$time minutes",
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = interFamily,
            fontWeight = FontWeight.W700
        )

    }
}