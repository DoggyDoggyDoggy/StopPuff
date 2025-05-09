package denys.diomaxius.stoppuff.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface QuitDateRepository {
    fun getQuitDate(): Flow<LocalDateTime?>
    suspend fun saveQuitDate(date: LocalDateTime)
    fun getDailySpending(): Flow<Int?>
    suspend fun saveDailySpending(amount: Int)
}