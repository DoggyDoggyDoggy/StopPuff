package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class GetQuitDateUseCase @Inject constructor(
    private val repository: QuitDateRepository
) {
    operator fun invoke(): Flow<LocalDateTime?> = repository.getQuitDate()
}