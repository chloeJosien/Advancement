import model.Advancement
import model.AllianceModel
import model.Team
import org.apache.commons.math3.special.Erf;


open class CalculateAdvancement {
    val reader: OfflineFileReader = OfflineFileReader()


    open fun advancement(teams:List<Team>) : List<Advancement>{
        val advancement = mutableListOf<Advancement>()

        //get qual ranking points
        val competingTeams = teams.filter { !it.rank.isNullOrEmpty() } //since not all teams compete in the robot games
        calulateQualPointsAllTeams(competingTeams)

        //get alliances
        val alliances = reader.readAllianceFile()
        calculateAlliancePoints(teams,alliances)

        //win points
//        val eliminatedAlliances = reader.readEliminationOrder()
//        calculateWinPoints(teams,alliances,eliminatedAlliances)

        //get awards

        //total
        for( team in teams){
            team.total = team.qualificationPoints + team.alliancePoints + team.playOffPoints + team.awardPoints
            advancement.add(Advancement(team.teamName,team.teamNumber,team.total!!))
        }
        return advancement.sortedByDescending { it.advancementPoints}
    }

    fun calulateQualPointsAllTeams(teams: List<Team>){
        val totalTeams = teams.size
        for(team in teams){
            calulateQualPoints(team, team.rank!!.toInt(), totalTeams)
        }
    }

    //equation found in Game Manual for the 2025-2026 season
    fun calulateQualPoints(team: Team, rank: Int, totalTeams: Int){
        val alpha = 1.07
        val part1Top = totalTeams - (2*rank) +2
        val part1Bottom = alpha*totalTeams
        val part1 = Erf.erfInv(part1Top/part1Bottom)
        val part2 = 7 / Erf.erfInv(1/alpha)
        val qualPoints = Math.ceil(part1*part2+9)

        team.qualificationPoints = if(qualPoints<2) 2 else qualPoints.toInt()
    }

    fun calculateAlliancePoints(teams: List<Team>, allianceModels: List<AllianceModel>){
        for(alliance in allianceModels){
            val points = 21 - alliance.allianceNumber.toInt()
            teams.find { it.teamNumber == alliance.captain }?.alliancePoints = points
            teams.find { it.teamNumber == alliance.firstPick }?.alliancePoints = points
        }
    }

    fun calculateWinPoints(teams: List<Team>, allianceModels: List<AllianceModel>, eliminatedAlliances: List<Int>){
        val alliancePoints = listOf(40,20,10,5)
        //top 4 teams get points
        for(i in 0..4){
            //alliances are in eliminated order so winner is listed last
            val allianceNumber = eliminatedAlliances[eliminatedAlliances.size-1-i]
            val alliance = allianceModels.find { it.allianceNumber == allianceNumber.toString() }

            teams.find { it.teamNumber == alliance?.captain }?.playOffPoints = alliancePoints[i]
            teams.find { it.teamNumber == alliance?.firstPick }?.playOffPoints = alliancePoints[i]
        }
    }


}