package denys.diomaxius.stoppuff.ui.screen.onboarding.slide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.stoppuff.navigation.Screen

@Composable
fun FourthSlide(
    navHostController: NavHostController,
    saveLastDatePuff: () -> Unit,
    setFirstLaunch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(1.dp)) // Third child

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Last puffs",
                fontWeight = FontWeight.Medium,
                fontSize = 26.sp
            )

            Text(
                text = "Vape last time before you start",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

        Button(
            modifier = Modifier.padding(bottom = 32.dp),
            onClick = {
                saveLastDatePuff()
                setFirstLaunch()
                navHostController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        ) {
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FourthSlidePreview() {
    FourthSlide(
        navHostController = rememberNavController(),
        saveLastDatePuff = {},
        setFirstLaunch = {}
    )
}