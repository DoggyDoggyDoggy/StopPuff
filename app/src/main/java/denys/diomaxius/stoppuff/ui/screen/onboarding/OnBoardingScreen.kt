@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    val pagerState = rememberPagerState { 2 }
    val slides = listOf<@Composable () -> Unit>(
        { FirstSlide() },
        { SecondSlide() }
    )

    Content(
        pagerState = pagerState,
        slides = slides
    )
}

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

@Composable
fun FirstSlide() {
    Text(text = "First Slide")
}

@Composable
fun SecondSlide() {
    Text(text = "Second Slide")
}

/*
    Button(
        onClick = {
            navHostController.navigate(Screen.Main.route) {
                popUpTo(Screen.Onboarding.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    ) {
        Text(text = "MainScreen")
    }*/