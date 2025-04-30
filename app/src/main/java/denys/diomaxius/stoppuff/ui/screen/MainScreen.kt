package denys.diomaxius.stoppuff.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val quitDate by viewModel.quitDate.collectAsState()

    Content(quitDate = quitDate)
}

@Composable
fun Content(
    quitDate: LocalDate?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Days no smoke",
            fontSize = 32.sp
        )

        Text(
            text = "${getDayNoVape(quitDate)}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content(LocalDate.now())
}

fun getDayNoVape(
    quitDate: LocalDate?
): Long {
    return quitDate?.let { ChronoUnit.DAYS.between(it, LocalDate.now()) } ?: 0L
}