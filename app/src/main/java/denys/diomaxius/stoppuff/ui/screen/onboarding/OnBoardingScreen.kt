package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import denys.diomaxius.stoppuff.navigation.Screen

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    Button(
        onClick = {
            navHostController.navigate(Screen.Main.route) {
                popUpTo(Screen.Onboarding.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    ) {
        Text(text = "MainScreen")
    }
}