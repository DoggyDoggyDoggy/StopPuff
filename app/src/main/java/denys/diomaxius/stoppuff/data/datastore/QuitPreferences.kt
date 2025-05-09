package denys.diomaxius.stoppuff.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

val Context.dataStore by preferencesDataStore(name = "quit_prefs")

class QuitPreferences(private val context: Context) {
    companion object {
        private val QUIT_DATE_KEY = stringPreferencesKey("quit_date")
        private val DAILY_SPENDING_KEY = intPreferencesKey("daily_spending")
    }

    fun getQuitDate(): Flow<LocalDateTime?> = context.dataStore.data
        .map { prefs ->
            prefs[QUIT_DATE_KEY]?.let(LocalDateTime::parse)
        }

    suspend fun saveQuitDate(date: LocalDateTime) {
        context.dataStore.edit { prefs ->
            prefs[QUIT_DATE_KEY] = date.toString()
        }
    }

    fun getDailySpending(): Flow<Int?> = context.dataStore.data
        .map { prefs ->
            prefs[DAILY_SPENDING_KEY]
        }

    suspend fun saveDailySpending(amount: Int) {
        context.dataStore.edit { prefs ->
            prefs[DAILY_SPENDING_KEY] = amount
        }
    }
}