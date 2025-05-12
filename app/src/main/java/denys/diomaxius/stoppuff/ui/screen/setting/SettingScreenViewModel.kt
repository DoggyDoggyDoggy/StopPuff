package denys.diomaxius.stoppuff.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetDailySpendingUseCase
import denys.diomaxius.stoppuff.domain.usecase.SaveDailySpendingUseCase
import denys.diomaxius.stoppuff.domain.usecase.SaveQuitDateUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val saveDailySpendingUseCase: SaveDailySpendingUseCase,
    private val saveQuitDateUseCase: SaveQuitDateUseCase,
    getDailySpendingUseCase: GetDailySpendingUseCase
) : ViewModel() {
    private val _dailySpend = getDailySpendingUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val dailySpend: StateFlow<Int?> = _dailySpend

    fun saveDailySpending(str: String) {
        viewModelScope.launch {
            var amount = 0
            if (str.length < 5) {
                if (str.all { it.isDigit() } && str.isNotEmpty()) {
                    amount = str.toInt()
                }
            }
            saveDailySpendingUseCase(amount)
        }
    }

    fun resetLastDatePuff(dateTime: LocalDateTime = LocalDateTime.now()) {
        viewModelScope.launch {
            saveQuitDateUseCase(dateTime)
        }
    }
}