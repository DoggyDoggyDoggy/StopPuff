package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

    val testList = listOf(
        "1 Day Puff Free",
        "3 Days Puff Free",
        "7 Days Puff Free"
    )

    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            quitDate = quitDate,
            achievements = testList
        )
    }

}

@Composable
fun Content(
    modifier: Modifier,
    quitDate: LocalDate?,
    achievements: List<String>
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier.weight(0.2f),
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(achievements) {
                Achievement(it)
            }
        }
    }
}

@Composable
fun Achievement(
    text: String = "1 Day Puff Free"
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check"
                )
            }

            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = text,
                fontSize = 28.sp,
            )
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp),
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
        LocalDate.now(),
        achievements = listOf(
            "1 Day Puff Free",
            "3 Days Puff Free",
            "7 Days Puff Free"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AchievementPreview() {
    Achievement()
}

fun getDayNoVape(
    quitDate: LocalDate?
): Long {
    return quitDate?.let { ChronoUnit.DAYS.between(it, LocalDate.now()) } ?: 0L
}