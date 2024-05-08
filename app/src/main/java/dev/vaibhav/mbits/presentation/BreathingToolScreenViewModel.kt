package dev.vaibhav.mbits.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.mbits.domain.BreathingToolRepository
import dev.vaibhav.mbits.domain.Data
import dev.vaibhav.mbits.domain.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BreathingToolScreenViewModel @Inject constructor(
    private val breathingToolRepository: BreathingToolRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BreathingToolScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun initScreenState() = viewModelScope.launch {
        val response = breathingToolRepository.getBreathingToolData(
            userId = "6309a9379af54f142c65fbfe",
            date = Date(1711860932992)
        )
        when(response) {
            is Response.Success -> {
                _uiState.update {
                    it.copy(toolData = response.data)
                }
            }
            is Response.Error -> {
                _uiState.update { it.copy(errorMessage = response.error.message) }
            }
            else -> {

            }
        }

    }


}

data class BreathingToolScreenUiState(
    val setDuration: Int = 0,
    val vitaminDCounter: Long = 0,
    val errorMessage: String? = null,
    val toolData: Data? = null
)

