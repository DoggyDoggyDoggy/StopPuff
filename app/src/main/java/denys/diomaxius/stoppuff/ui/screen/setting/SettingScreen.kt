package denys.diomaxius.stoppuff.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.stoppuff.ui.screen.main.TopBar
import androidx.compose.runtime.getValue


@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val dailySpending by viewModel.dailySpend.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(navHostController = navHostController)
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            navHostController = navHostController,
            saveDailySpending = { viewModel.saveDailySpending(it) },
            dailySpending = dailySpending,
            resetLastDatePuff = { viewModel.resetLastDatePuff() }
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    saveDailySpending: (String) -> Unit,
    dailySpending: Int?,
    resetLastDatePuff: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResetProgress(
            resetLastDatePuff = resetLastDatePuff
        )

        Spacer(modifier = Modifier.height(16.dp))

        ChangeDailySpending(
            saveDailySpending = saveDailySpending,
            dailySpending = dailySpending
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.padding(bottom = 24.dp),
            onClick = {
                navHostController.popBackStack()
            }
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun ResetProgress(
    modifier: Modifier = Modifier,
    resetLastDatePuff: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Back to day one — no puffing starts now",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { resetLastDatePuff() }
        ) {
            Text(text = "Reset")
        }
    }
}

@Composable
fun ChangeDailySpending(
    modifier: Modifier = Modifier,
    saveDailySpending: (String) -> Unit,
    dailySpending: Int?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Spend money on vaping every day",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = "${dailySpending ?: ""}",
            onValueChange = { saveDailySpending(it) },
            label = { Text(text = "Enter amount") },
            placeholder = { Text(text = "For example 10") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(
        navHostController = rememberNavController()
    )
}