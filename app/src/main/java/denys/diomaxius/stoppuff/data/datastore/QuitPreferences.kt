package denys.diomaxius.stoppuff.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

val Context.dataStore by preferencesDataStore(name = "quit_prefs")

class QuitPreferences (private val context: Context) {
    companion object {
        private val QUIT_DATE_KEY = stringPreferencesKey("quit_date")
    }

    fun getQuitDate(): Flow<LocalDate?> = context.dataStore.data
        .map { prefs ->
            prefs[QUIT_DATE_KEY]?.let(LocalDate::parse)
        }

    suspend fun saveQuitDate(date: LocalDate) {
        context.dataStore.edit { prefs ->
            prefs[QUIT_DATE_KEY] = date.toString()
        }
    }
}