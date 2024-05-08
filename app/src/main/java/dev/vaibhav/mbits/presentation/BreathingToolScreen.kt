package dev.vaibhav.mbits.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vaibhav.mbits.R
import dev.vaibhav.mbits.ui.theme.blackTransparent
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
import dev.vaibhav.mbits.util.MockData
import dev.vaibhav.mbits.util.convertValueToAngle
import dev.vaibhav.mbits.util.formatDate
import java.util.Date
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingToolScreen(
    uiState: BreathingToolScreenUiState,
    initState: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        initState()
    }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(initialValue = SheetValue.PartiallyExpanded, skipPartiallyExpanded = false, skipHiddenState = true),
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContainerColor = blackTransparent,
        sheetSwipeEnabled = true,
        sheetDragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(100.dp)
                    .height(7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
            )
        },
        sheetPeekHeight = 230.dp,
        sheetContent = {
            BreathingToolBottomSheet(state = uiState, ordinal = scaffoldState.bottomSheetState.currentValue.ordinal)
        },
        ) {


        Column (
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.whatever),
                    contentScale = ContentScale.FillBounds
                )
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                title = {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Breathing Tool",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {},
                        imageVector = ImageVector.vectorResource(id = R.drawable.meditation),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                actions = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.faq),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                }
            )

            Spacer(modifier = Modifier.height(5.dp))


            DurationSelector(state = uiState,currentDuration = 30f, onDurationChange = {})

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Recommended",
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            WeatherInfoSection(
                temp = 18,
                location = "Dwarka, Delhi",
                date = Date()
            )

            Spacer(modifier = Modifier.weight(1f))

            BottomBar()


        }


    }



}

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
            if (state.toolData != null) {
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
fun WeatherInfoSection(
    temp: Int,
    location: String,
    date: Date
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(color = whiteTransparent)
            .paint(
                painter = painterResource(id = R.drawable.sun),
                sizeToIntrinsics = true,
                alignment = Alignment.TopEnd,
            )
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$temp °",
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 72.sp,
                color = Color.White
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.cloud),
                contentDescription = null,
                tint = Color.Unspecified
            )

        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.location_icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = location,
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = formatDate(date),
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.White
            )

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingToolBottomSheet(
    state: BreathingToolScreenUiState,
    ordinal: Int = 2
    //ordinal 1 means fully expanded and 2 means partially expanded
) {

    Column (
        modifier = Modifier
            .padding(16.dp)
            .heightIn(min = 100.dp)
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Black,
                            Color.White,
                            Color.Black
                        )
                    )
                ),
            thickness = 1.dp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(MockData.data.subList(0,2)) {
                BottomSheetItem(iconId = it.iconId, title = it.title, subTitle = it.subTitle)
            }

            if (ordinal == 2) {
                item(span = { GridItemSpan(2) }) {
                    BottomBar()
                }
            }

            items(MockData.data.subList(2, MockData.data.size)) {
                BottomSheetItem(iconId = it.iconId, title = it.title, subTitle = it.subTitle)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomSheetSunlightItem()

        Spacer(modifier = Modifier.height(16.dp))

        BottomBar()
    }

}

@Composable
fun BottomBar() {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            modifier = Modifier
                .height(50.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
            onClick = {
//                        isBottomSheetExpanded = false
            }) {

            Text(
                text = "SCHEDULE",
                fontFamily = interFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            modifier = Modifier
                .height(50.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0277BD)),
            onClick = {
//                        isBottomSheetExpanded = true
            }) {

            Text(
                text = "START",
                fontFamily = interFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                color = Color.White
            )
        }

    }
}

@Composable
fun BottomSheetItem(
    iconId: Int,
    title: String,
    subTitle: String
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = Color(0xFF959393).copy(alpha = 0.4f)
            )
            .padding(16.dp)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = iconId),
            contentDescription = null,
            tint = Color.Unspecified
        )
        
        Spacer(modifier = Modifier.width(10.dp))

        Column (
            modifier = Modifier.fillMaxHeight(),
           horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                text = title,
                fontFamily = interFamily,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = subTitle,
                fontFamily = interFamily,
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                color = Color.White
            )
        }

    }

}

@Composable
fun BottomSheetSunlightItem() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = Color(0xFF959393).copy(alpha = 0.4f)
            )
            .padding(16.dp)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.sunlight),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column (
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

            Text(
                text = "Sunlight",
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = "Do you want to know how much Vitamin D consumed while doing Breathing Exercise in Sunlight?",
                fontFamily = interFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = Color.White
            )


        }

        Spacer(modifier = Modifier.width(16.dp))

        Switch(
            checked = true,
            onCheckedChange = {

            },
            colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF4CAF50), checkedTrackColor = Color(0xFF969696))
            )


    }
}

@Preview(showSystemUi = false, showBackground = false, backgroundColor = 0xFF000000)
@Composable
fun BreathingToolPreview() {

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