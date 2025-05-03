package denys.diomaxius.stoppuff.domain.usecase

import denys.diomaxius.stoppuff.domain.repository.AppLaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFirstLaunchUseCase @Inject constructor(
    private val repository: AppLaunchRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.getFirstLaunchDate()
}