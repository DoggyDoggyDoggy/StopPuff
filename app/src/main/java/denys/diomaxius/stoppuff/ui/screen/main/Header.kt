package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.stoppuff.navigation.Screen

@Composable
fun MainHeader(
    modifier: Modifier,
    days: Long,
    hours: Long,
    minutes: Long
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Time vape free",
            fontSize = 32.sp
        )

        Text(
            text = buildString {
                if (days > 0) {
                    append("${days}D ")
                }
                if (hours > 0) {
                    append("${hours}H ")
                }

                append("${minutes}M")

            },
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val canNavigateBack = navHostController.previousBackStackEntry != null

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Stop Puff",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (currentRoute != Screen.Setting.route) {
                IconButton(onClick = {
                    navHostController.navigate(Screen.Setting.route) {
                        launchSingleTop = true
                    }
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        navHostController = rememberNavController()
    )
}