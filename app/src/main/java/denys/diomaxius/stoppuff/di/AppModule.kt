package denys.diomaxius.stoppuff.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.stoppuff.data.datastore.AppLaunchPreferences
import denys.diomaxius.stoppuff.data.datastore.QuitPreferences
import denys.diomaxius.stoppuff.data.repository.AppLaunchRepositoryImpl
import denys.diomaxius.stoppuff.data.repository.QuitDateRepositoryImpl
import denys.diomaxius.stoppuff.domain.repository.AppLaunchRepository
import denys.diomaxius.stoppuff.domain.repository.QuitDateRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQuitPreferences(@ApplicationContext context: Context): QuitPreferences =
        QuitPreferences(context)

    @Provides
    @Singleton
    fun provideAppLaunchPreferences(@ApplicationContext context: Context): AppLaunchPreferences =
        AppLaunchPreferences(context)

    @Provides
    @Singleton
    fun provideQuitDateRepository(prefs: QuitPreferences): QuitDateRepository =
        QuitDateRepositoryImpl(prefs)

    @Provides
    @Singleton
    fun provideAppLaunchRepository(prefs: AppLaunchPreferences): AppLaunchRepository =
        AppLaunchRepositoryImpl(prefs)
}