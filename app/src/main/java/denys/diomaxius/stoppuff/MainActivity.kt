package denys.diomaxius.stoppuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import denys.diomaxius.stoppuff.navigation.AppNavGraph
import denys.diomaxius.stoppuff.ui.theme.StopPuffTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopPuffTheme {
                AppNavGraph()
            }
        }
    }
}