package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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

@Composable
fun TopBar(
    navHostController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp)
                .clickable{
                    navHostController.navigate(Screen.Setting.route) {
                        launchSingleTop = true
                    }
                }
            ,
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        navHostController = rememberNavController()
    )
}