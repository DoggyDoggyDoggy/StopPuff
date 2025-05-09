package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetDailySpendingUseCase
import denys.diomaxius.stoppuff.domain.usecase.GetFirstLaunchUseCase
import denys.diomaxius.stoppuff.domain.usecase.SaveDailySpendingUseCase
import denys.diomaxius.stoppuff.domain.usecase.SaveQuitDateUseCase
import denys.diomaxius.stoppuff.domain.usecase.SetFirstLaunchUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val saveQuitDateUseCase: SaveQuitDateUseCase,
    private val setFirstLaunchUseCase: SetFirstLaunchUseCase,
    getFirstLaunchUseCase: GetFirstLaunchUseCase,
    getDailySpendingUseCase: GetDailySpendingUseCase,
    private val saveDailySpendingUseCase: SaveDailySpendingUseCase
) : ViewModel() {

    private val _firstLaunch = getFirstLaunchUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val firstLaunch: StateFlow<Boolean?> = _firstLaunch

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

    fun saveLastDatePuff(dateTime: LocalDateTime = LocalDateTime.now()) {
        viewModelScope.launch {
            saveQuitDateUseCase(dateTime)
        }
    }

    fun setFirstLaunch() {
        viewModelScope.launch {
            setFirstLaunchUseCase()
        }
    }
}