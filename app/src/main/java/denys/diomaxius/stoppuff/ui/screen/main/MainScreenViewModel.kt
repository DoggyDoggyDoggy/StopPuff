package denys.diomaxius.stoppuff.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetQuitDateUseCase
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
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getQuitDateUseCase: GetQuitDateUseCase
) : ViewModel() {
    private val _quitDate = getQuitDateUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val timeSinceQuit: StateFlow<Triple<Long, Long, Long>> =
        _quitDate
            .flatMapLatest { date ->
                if (date == null) {
                    flowOf(Triple(0L, 0L, 0L))
                } else {
                    tickerFlow(initialDelay = 0L, period = 60_000L)
                        .map { getTimeSinceQuit(date) }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = Triple(0L, 0L, 0L)
            )


    private fun tickerFlow(initialDelay: Long, period: Long): Flow<Unit> = flow {
        delay(initialDelay)
        emit(Unit)
        while (true) {
            delay(period)
            emit(Unit)
        }
    }

    private fun getTimeSinceQuit(quitDate: LocalDateTime?): Triple<Long, Long, Long> {
        return quitDate?.let {
            val duration = Duration.between(it, LocalDateTime.now())
            val days = duration.toDays()
            val hours = duration.minusDays(days).toHours()
            val minutes = duration.minusDays(days).minusHours(hours).toMinutes()

            Triple(days, hours, minutes)
        } ?: Triple(0, 0, 0)
    }
}