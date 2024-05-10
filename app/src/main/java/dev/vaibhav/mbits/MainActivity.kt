package dev.vaibhav.mbits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.vaibhav.mbits.presentation.BreathingToolScreen
import dev.vaibhav.mbits.presentation.BreathingToolScreenUiState
import dev.vaibhav.mbits.presentation.BreathingToolScreenViewModel
import dev.vaibhav.mbits.ui.theme.AssignmentTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: BreathingToolScreenViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            BreathingToolScreen(uiState = state) {
                viewModel.initScreenState()
            }
        }
    }
}

