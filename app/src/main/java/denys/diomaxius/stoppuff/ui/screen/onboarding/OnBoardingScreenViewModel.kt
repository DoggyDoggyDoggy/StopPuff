package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.SaveQuitDateUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val saveQuitDateUseCase: SaveQuitDateUseCase
) : ViewModel() {
    fun saveLastDatePuff(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            saveQuitDateUseCase(date)
        }
    }
}