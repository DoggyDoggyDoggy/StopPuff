package denys.diomaxius.stoppuff.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.stoppuff.domain.usecase.GetQuitDateUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getQuitDateUseCase: GetQuitDateUseCase
) : ViewModel() {
    private val _quitDate = getQuitDateUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val quitDate: StateFlow<LocalDateTime?> = _quitDate
}