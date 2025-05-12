package denys.diomaxius.stoppuff.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import denys.diomaxius.stoppuff.data.constants.MenuTab

@Composable
fun ViewSwitcher(
    switchMenuTab: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        repeat(3) {
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { switchMenuTab(MenuTab.menuTabs[it]) }
                ,
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = MenuTab.menuTabs[it]
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewSwitcherPreview() {
    ViewSwitcher(
        switchMenuTab = {}
    )
}