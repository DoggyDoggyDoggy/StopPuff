@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package denys.diomaxius.stoppuff.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.stoppuff.R
import denys.diomaxius.stoppuff.navigation.Screen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    viewModel: OnBoardingScreenViewModel = hiltViewModel()
) {
    val slides = listOf<@Composable () -> Unit>(
        { FirstSlide() },
        { SecondSlide() },
        {
            ThirdSlide(
                navHostController = navHostController,
                saveLastDatePuff = { viewModel.saveLastDatePuff() }
            )
        }
    )

    val pagerState = rememberPagerState { slides.size }

    Content(
        pagerState = pagerState,
        slides = slides
    )
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

@Composable
fun FirstSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.onboarding_slide_one_name),
            fontWeight = FontWeight.Medium,
            fontSize = 26.sp
        )

        Text(
            text = stringResource(R.string.onboarding_slide_one_description),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SecondSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.onboarding_slide_two_name),
            fontWeight = FontWeight.Medium,
            fontSize = 26.sp
        )

        Text(
            text = stringResource(R.string.onboarding_slide_two_description),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ThirdSlide(
    navHostController: NavHostController,
    saveLastDatePuff: () -> Unit
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
fun FirstSlidePreview() {
    FirstSlide()
}

@Preview(showBackground = true)
@Composable
fun SecondSlidePreview() {
    SecondSlide()
}

@Preview(showBackground = true)
@Composable
fun ThirdSlidePreview() {
    ThirdSlide(
        navHostController = rememberNavController()
    ) {}
}