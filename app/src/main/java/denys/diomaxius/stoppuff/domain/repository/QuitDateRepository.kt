package denys.diomaxius.stoppuff.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface QuitDateRepository {
    fun getQuitDate(): Flow<LocalDate?>
    suspend fun saveQuitDate(date: LocalDate)
}