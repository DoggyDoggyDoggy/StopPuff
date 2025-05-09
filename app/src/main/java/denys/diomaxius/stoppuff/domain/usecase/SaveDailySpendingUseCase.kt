package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import javax.inject.Inject

class SaveDailySpendingUseCase @Inject constructor(
    private val repository: QuitDateRepository
) {
    suspend operator fun invoke(amount: Int) = repository.saveDailySpending(amount)
}