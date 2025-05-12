package denys.diomaxius.stoppuff.ui.screen.main.menutabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Money(
    modifier: Modifier = Modifier,
    dailySpend: Int,
    days: Long
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Money saved",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "$${days * dailySpend}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(52.dp))

        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            InfoRow(label = "Per day", value = dailySpend)
            InfoRow(label = "Per week", value = dailySpend * 7)
            InfoRow(label = "Per month", value = dailySpend * 30)
            InfoRow(label = "Per year", value = dailySpend * 365)
        }
    }
}

@Composable
fun InfoRow(label: String, value: Number) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 22.sp)
        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = "$${value}",
            fontSize = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoney() {
    Money(
        dailySpend = 10,
        days = 3
        )
}