package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.AppLaunchRepository
import javax.inject.Inject

class SetFirstLaunchUseCase @Inject constructor(
    private val repository: AppLaunchRepository
) {
    suspend operator fun invoke() = repository.setFirstLaunchDate()
}