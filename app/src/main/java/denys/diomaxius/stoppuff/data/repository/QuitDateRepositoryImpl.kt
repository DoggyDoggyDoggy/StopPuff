package denys.diomaxius.stoppuff.data.repository

import denys.diomaxius.stoppuff.data.datastore.QuitPreferences
import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class QuitDateRepositoryImpl @Inject constructor(
    private val prefs: QuitPreferences
) : QuitDateRepository {
    override fun getQuitDate(): Flow<LocalDateTime?> = prefs.getQuitDate()
    override suspend fun saveQuitDate(date: LocalDateTime) = prefs.saveQuitDate(date)
    override fun getDailySpending(): Flow<Int?> = prefs.getDailySpending()
    override suspend fun saveDailySpending(amount: Int) = prefs.saveDailySpending(amount)
}