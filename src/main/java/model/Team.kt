package model

data class Team(
    val teamNumber: String,
    val teamName: String,
    val rank: String? = null,
    var qualificationPoints: Int = 2,
    var alliancePoints: Int = 0,
    var playOffPoints: Int = 0,
    val awardPoints: Int = 0,
    var total: Int? = 0
)
