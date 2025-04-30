package denys.diomaxius.stoppuff.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.stoppuff.ui.screen.main.MainScreen
import denys.diomaxius.stoppuff.ui.screen.onboarding.OnBoardingScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(route = Screen.Onboarding.route) {
            OnBoardingScreen(navHostController = navHostController)
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }
    }
}