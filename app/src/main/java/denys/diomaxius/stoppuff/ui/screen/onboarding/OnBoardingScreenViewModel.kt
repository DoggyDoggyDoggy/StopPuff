package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.SaveQuitDateUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val saveQuitDateUseCase: SaveQuitDateUseCase
) : ViewModel() {
    fun saveLastDatePuff(dateTime: LocalDateTime = LocalDateTime.now()) {
        viewModelScope.launch {
            saveQuitDateUseCase(dateTime)
        }
    }
}