package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import java.time.LocalDate
import javax.inject.Inject

class SaveQuitDateUseCase @Inject constructor(
    private val repository: QuitDateRepository
) {
    suspend operator fun invoke(date: LocalDate) = repository.saveQuitDate(date)
}