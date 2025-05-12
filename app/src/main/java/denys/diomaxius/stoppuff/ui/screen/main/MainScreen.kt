package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.stoppuff.data.constants.Achievement
import denys.diomaxius.stoppuff.data.constants.Achievements
import denys.diomaxius.stoppuff.data.constants.MenuTab
import denys.diomaxius.stoppuff.ui.screen.main.menutabs.Achievement
import denys.diomaxius.stoppuff.ui.screen.main.menutabs.Money
import denys.diomaxius.stoppuff.ui.screen.main.menutabs.Tips

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val timeTriple by viewModel.timeSinceQuit.collectAsState()
    val dailySpend by viewModel.dailySpend.collectAsState()

    val (days, hours, minutes) = timeTriple

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            days = days,
            hours = hours,
            minutes = minutes,
            achievements = Achievements.achievements,
            dailySpend = dailySpend ?: 0
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    achievements: List<Achievement>,
    days: Long,
    hours: Long,
    minutes: Long,
    dailySpend: Int
) {
    var tab by remember {
        mutableStateOf(MenuTab.menuTabs[0])
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainHeader(
            modifier = Modifier.weight(0.3f),
            days = days,
            hours = hours,
            minutes = minutes
        )

        ViewSwitcher(
            switchMenuTab = { tab = it }
        )

        Spacer(modifier = Modifier.height(6.dp))

        when (tab) {
            MenuTab.menuTabs[0] -> {
                LazyColumn(
                    modifier = Modifier
                        .weight(0.7f)
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

            MenuTab.menuTabs[1] -> {
                Money(
                    modifier = Modifier.weight(0.7f),
                    dailySpend = dailySpend,
                    days = days
                )
            }

            MenuTab.menuTabs[2] -> {
                Tips(
                    modifier = Modifier.weight(0.7f)
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
        minutes = 24,
        dailySpend = 10
    )
}