package denys.diomaxius.stoppuff.data.constants

data class Achievement(val title: String, val daysRequired: Int)

object Achievements {
    val achievements = listOf(
        Achievement("1 Day Puff Free", 1),
        Achievement("3 Days Puff Free", 3),
        Achievement("7 Days Puff Free", 7)
    )
}
