package model

data class Event(
    val eventName: String,
    val eventCode: String?,
    val teams: List<Team> = emptyList()
)
