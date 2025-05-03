package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.stoppuff.data.constants.Achievement
import denys.diomaxius.stoppuff.data.constants.Achievements
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val quitDate by viewModel.quitDate.collectAsState()

    val (days, hours, minutes) = getTimeSinceQuit(quitDate)

    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            days = days,
            hours = hours,
            minutes = minutes,
            achievements = Achievements.achievements
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    achievements: List<Achievement>,
    days: Long,
    hours: Long,
    minutes: Long
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainHeader(
            modifier = Modifier.weight(0.4f),
            days = days,
            hours = hours,
            minutes = minutes
        )

        LazyColumn(
            modifier = Modifier
                .weight(0.6f)
                .padding(horizontal = 16.dp)
        ) {
            items(achievements) {
                Achievement(
                    text = it.title,
                    achieved = days >= it.daysRequired
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content(
        modifier = Modifier,
        achievements = Achievements.achievements,
        days = 6,
        hours = 11,
        minutes = 24
    )
}

fun getTimeSinceQuit(quitDate: LocalDateTime?): Triple<Long, Long, Long> {
    return quitDate?.let {
        val duration = Duration.between(it, LocalDateTime.now())
        val days = duration.toDays()
        val hours = duration.minusDays(days).toHours()
        val minutes = duration.minusDays(days).minusHours(hours).toMinutes()

        Triple(days, hours, minutes)
    } ?: Triple(0, 0, 0)
}