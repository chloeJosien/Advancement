package model

data class EventData(
    val ranking: List<Team>,
    val alliances: List<AllianceModel>,
    val eliminations: List<Int>,
    val inspire1: String = "",
    val inspire2: String = "",
    val inspire3: String = "",
    val firstPlace: List<String> = emptyList(),
    val secondPlace: List<String> = emptyList(),
    val thirdPlace: List<String> = emptyList()
)
