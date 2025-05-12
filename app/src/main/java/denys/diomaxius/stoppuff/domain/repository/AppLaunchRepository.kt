package denys.diomaxius.stoppuff.domain.repository

import kotlinx.coroutines.flow.Flow


interface AppLaunchRepository {
    fun getFirstLaunchDate(): Flow<Boolean>
    suspend fun setFirstLaunchDate()
}