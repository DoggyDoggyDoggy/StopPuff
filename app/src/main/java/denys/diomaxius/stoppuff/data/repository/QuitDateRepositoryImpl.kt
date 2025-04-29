package denys.diomaxius.stoppuff.data.repository

import denys.diomaxius.stoppuff.data.datastore.QuitPreferences
import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class QuitDateRepositoryImpl @Inject constructor(
    private val prefs: QuitPreferences
) : QuitDateRepository {
    override fun getQuitDate(): Flow<LocalDate?> = prefs.getQuitDate()
    override suspend fun saveQuitDate(date: LocalDate) = prefs.saveQuitDate(date)
}