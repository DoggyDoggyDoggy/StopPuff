package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetQuitDateUseCase @Inject constructor(
    private val repository: QuitDateRepository
) {
    operator fun invoke(): Flow<LocalDate?> = repository.getQuitDate()
}