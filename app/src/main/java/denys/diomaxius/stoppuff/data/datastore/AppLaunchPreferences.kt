package denys.diomaxius.stoppuff.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.appLaunchDataStore by preferencesDataStore(name = "app_launch_prefs")

class AppLaunchPreferences(private val context: Context) {
    companion object {
        private val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch_date")
    }

    fun getFirstLaunchDate(): Flow<Boolean> = context.appLaunchDataStore.data
        .map { prefs ->
            prefs[FIRST_LAUNCH_KEY] ?: false
        }

    suspend fun setFirstLaunchDate() {
        context.appLaunchDataStore.edit { prefs ->
            prefs[FIRST_LAUNCH_KEY] = true
        }
    }
}