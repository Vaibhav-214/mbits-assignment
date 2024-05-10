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
import dev.vaibhav.mbits.presentation.components.BottomBar
import dev.vaibhav.mbits.presentation.components.BreathingToolBottomSheet
import dev.vaibhav.mbits.presentation.components.CustomCircularValueSelector
import dev.vaibhav.mbits.presentation.components.DurationSelector
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
import dev.vaibhav.mbits.util.mapToIcon
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


@Preview(showSystemUi = false, showBackground = false, backgroundColor = 0xFF000000)
@Composable
fun BreathingToolPreview() {
    BreathingToolScreen(uiState = BreathingToolScreenUiState()) {
        
    }
}