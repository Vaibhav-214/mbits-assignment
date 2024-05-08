package dev.vaibhav.mbits.util

import dev.vaibhav.mbits.R

object MockData {
    val data = listOf<MockBreathingToolData>(
        MockBreathingToolData(
            iconId = R.drawable.exercise,
            title = "Excercise",
            subTitle = "Ujjayi+3"
        ),
        MockBreathingToolData(
            iconId = R.drawable.speedometer,
            title = "Peace",
            subTitle = "Slow"
        ),
        MockBreathingToolData(
            iconId = R.drawable.rise,
            title = "Level",
            subTitle = "Beginner 2"
        ),
        MockBreathingToolData(
            iconId = R.drawable.music,
            title = "Music",
            subTitle = "Vital Life...."
        ),
        MockBreathingToolData(
            iconId = R.drawable.language,
            title = "Language",
            subTitle = "English"
        ),
        MockBreathingToolData(
            iconId = R.drawable.pause,
            title = "Break",
            subTitle = "2 Min"
        ),
        MockBreathingToolData(
            iconId = R.drawable.goal,
            title = "Goal",
            subTitle = "De-Stress"
        ),
    )
}

data class MockBreathingToolData(
    val iconId: Int,
    val title: String,
    val subTitle: String
)