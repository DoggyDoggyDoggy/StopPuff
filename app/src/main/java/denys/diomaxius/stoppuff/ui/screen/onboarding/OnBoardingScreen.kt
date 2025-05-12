@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.stoppuff.navigation.Screen
import denys.diomaxius.stoppuff.ui.screen.components.Loading
import denys.diomaxius.stoppuff.ui.screen.onboarding.slide.FirstSlide
import denys.diomaxius.stoppuff.ui.screen.onboarding.slide.SecondSlide
import denys.diomaxius.stoppuff.ui.screen.onboarding.slide.FourthSlide
import denys.diomaxius.stoppuff.ui.screen.onboarding.slide.ThirdSlide


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    viewModel: OnBoardingScreenViewModel = hiltViewModel()
) {
    val firstLaunch by viewModel.firstLaunch.collectAsState(initial = null)
    val dailySpend by viewModel.dailySpend.collectAsState(initial = null)

    if (firstLaunch != null) {
        if (firstLaunch == true) {
            navHostController.navigate(Screen.Main.route) {
                navHostController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) { inclusive = true }
                }
                launchSingleTop = true
            }
        } else {
            val slides = listOf<@Composable () -> Unit>(
                { FirstSlide() },
                { SecondSlide() },
                {
                    ThirdSlide(
                        dailySpend = dailySpend,
                        saveDailySpending = {viewModel.saveDailySpending(it)}
                    )
                },
                {
                    FourthSlide(
                        navHostController = navHostController,
                        saveLastDatePuff = { viewModel.saveLastDatePuff() },
                        setFirstLaunch = { viewModel.setFirstLaunch() }
                    )
                }
            )

            val pagerState = rememberPagerState { slides.size }

            Content(
                pagerState = pagerState,
                slides = slides
            )
        }
    } else {
        Loading()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(
    pagerState: PagerState,
    slides: List<@Composable () -> Unit>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) {
            slides[it]()
        }
    }
}