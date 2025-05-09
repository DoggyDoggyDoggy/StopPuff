package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailySpendingUseCase @Inject constructor(
    private val repository: QuitDateRepository
) {
    operator fun invoke(): Flow<Int?> = repository.getDailySpending()
}