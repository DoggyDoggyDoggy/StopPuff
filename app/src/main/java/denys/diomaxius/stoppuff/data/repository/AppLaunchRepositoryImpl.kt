package denys.diomaxius.stoppuff.data.repository

import denys.diomaxius.stoppuff.data.datastore.AppLaunchPreferences
import denys.diomaxius.stoppuff.domain.repository.AppLaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppLaunchRepositoryImpl @Inject constructor(
    private val prefs: AppLaunchPreferences
) : AppLaunchRepository {
    override fun getFirstLaunchDate(): Flow<Boolean> = prefs.getFirstLaunchDate()
    override suspend fun setFirstLaunchDate() = prefs.setFirstLaunchDate()
}