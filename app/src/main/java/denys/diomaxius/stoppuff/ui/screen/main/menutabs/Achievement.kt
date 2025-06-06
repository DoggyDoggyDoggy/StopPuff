package denys.diomaxius.stoppuff.ui.screen.main.menutabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Achievement(
    text: String,
    achieved: Boolean
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 12.dp).padding(start = 12.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(if (achieved) Color.Green else Color.Gray),
                imageVector = Icons.Default.Check,
                tint = if (achieved) Color.White else Color.DarkGray,
                contentDescription = "Check"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = text,
                fontSize = 28.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementPreview() {
    Achievement(
        text = "1 Day Puff Free",
        achieved = true
    )
}