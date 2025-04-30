package denys.diomaxius.stoppuff.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetQuitDateUseCase
import denys.diomaxius.stoppuff.domain.usecase.SaveQuitDateUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getQuitDateUseCase: GetQuitDateUseCase,
    private val saveQuitDateUseCase: SaveQuitDateUseCase
) : ViewModel() {

    private val _quitDate = getQuitDateUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val quitDate: StateFlow<LocalDate?> = _quitDate

    fun onSaveQuitDate(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            saveQuitDateUseCase(date)
        }
    }
}