package denys.diomaxius.stoppuff.data.constants

data class Achievement(val title: String, val daysRequired: Int)

object Achievements {
    val achievements = listOf(
        Achievement("1 Day Puff Free", 1),
        Achievement("2 Days Puff Free", 2),
        Achievement("3 Days Puff Free", 3),
        Achievement("4 Days Puff Free", 4),
        Achievement("5 Days Puff Free", 5),
        Achievement("6 Days Puff Free", 6),
        Achievement("7 Days Puff Free", 7),
        Achievement("2 Weeks Puff Free", 14),
        Achievement("3 Weeks Puff Free", 21),
        Achievement("4 Weeks Puff Free", 28),
        Achievement("1 Month Puff Free", 30),
        Achievement("2 Months Puff Free", 60),
        Achievement("3 Months Puff Free", 90),

    )
}
