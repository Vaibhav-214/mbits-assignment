package dev.vaibhav.mbits.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vaibhav.mbits.R
import dev.vaibhav.mbits.presentation.BreathingToolScreenUiState
import dev.vaibhav.mbits.ui.theme.blackTransparent
import dev.vaibhav.mbits.ui.theme.interFamily
import dev.vaibhav.mbits.util.mapToIcon
import dev.vaibhav.mbits.util.subListOrEmpty


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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                           blackTransparent,
                            Color.White,
                            blackTransparent
                        )
                    )
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val list = state.toolData.breathingToolData.practice

            items(list.subListOrEmpty(0,2)) {
                val practiceData = it.mapToIcon()
                BottomSheetItem(
                    iconId = practiceData.iconId,
                    title = practiceData.data.title,
                    subTitle = practiceData.data.values[0].title
                )

            }


            if (ordinal == 2) {
                item(span = { GridItemSpan(2) }) {
                    BottomBar()
                }
            }

            items(list.subListOrEmpty(2,list.size)) {
                val practiceData = it.mapToIcon()
                BottomSheetItem(iconId = practiceData.iconId,
                    title = practiceData.data.title,
                    subTitle = practiceData.data.values[0].title)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomSheetSunlightItem()

        Spacer(modifier = Modifier.height(16.dp))

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
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
