package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val quitDate by viewModel.quitDate.collectAsState()

    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            quitDate = quitDate
        )
    }

}

@Composable
fun Content(
    modifier: Modifier,
    quitDate: LocalDate?
) {
    Column(
        modifier = modifier.fillMaxSize(),
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

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = Modifier.padding(12.dp).size(32.dp),
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content(
        Modifier,
        LocalDate.now()
    )
}

fun getDayNoVape(
    quitDate: LocalDate?
): Long {
    return quitDate?.let { ChronoUnit.DAYS.between(it, LocalDate.now()) } ?: 0L
}