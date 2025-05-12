package denys.diomaxius.stoppuff.domain.usecase

import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class GetTimeSinceQuitUseCase @Inject constructor() {
    operator fun invoke(quitDate: LocalDateTime?): Triple<Long, Long, Long> {
        return quitDate?.let {
            val duration = Duration.between(it, LocalDateTime.now())
            val days = duration.toDays()
            val hours = duration.minusDays(days).toHours()
            val minutes = duration.minusDays(days).minusHours(hours).toMinutes()

            Triple(days, hours, minutes)
        } ?: Triple(0, 0, 0)
    }
}