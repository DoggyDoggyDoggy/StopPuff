package denys.diomaxius.stoppuff.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetDailySpendingUseCase
import denys.diomaxius.stoppuff.domain.usecase.GetQuitDateUseCase
import denys.diomaxius.stoppuff.domain.usecase.GetTimeSinceQuitUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getQuitDateUseCase: GetQuitDateUseCase,
    private val getTimeSinceQuit: GetTimeSinceQuitUseCase,
    getDailySpendingUseCase: GetDailySpendingUseCase
) : ViewModel() {
    private val _quitDate = getQuitDateUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _dailySpend = getDailySpendingUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val dailySpend: StateFlow<Int?> = _dailySpend

    // Public StateFlow emitting a Triple(days, hours, minutes) updated every minute
    @OptIn(ExperimentalCoroutinesApi::class)
    val timeSinceQuit: StateFlow<Triple<Long, Long, Long>> =
        _quitDate
            .flatMapLatest { date -> // When quitDate changes, cancel old stream and start new
                if (date == null) {
                    flowOf(Triple(0L, 0L, 0L))
                } else {
                    //Start a ticker that emits Unit every minute
                    tickerFlow().map { getTimeSinceQuit(date) }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = Triple(0L, 0L, 0L)
            )

    // Helper function to create a cold Flow that emits a tick (Unit) immediately and then every `period` ms
    private fun tickerFlow(initialDelay: Long = 0L, period: Long = 60_000L): Flow<Unit> = flow {
        delay(initialDelay)
        emit(Unit)
        // Enter an infinite loop emitting at fixed intervals until cancelled
        while (true) {
            delay(period)
            emit(Unit)
        }
    }
}