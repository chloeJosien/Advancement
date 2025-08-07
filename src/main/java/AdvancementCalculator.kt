import model.*
import org.apache.commons.math3.special.Erf;


open class AdvancementCalculator {
    val reader: OfflineFileReader = OfflineFileReader()
    val calculations: Calculations = Calculations()
    //easy update for offline workings
    val allianceFilePath: String = "/alliances.txt"
    val eliminationOrderFilePath: String = "/eliminationOrder.txt"
    val awardFilePath : String ="/awards.txt"


    open fun advancement(teams:List<Team>) : List<Advancement>{
        val advancement = mutableListOf<Advancement>()

        //get qual ranking points
        val competingTeams = teams.filter { !it.rank.isNullOrEmpty() } //since not all teams compete in the robot games
        calculations.calulateQualPointsAllTeams(competingTeams)

        //get alliances
        val alliances = reader.readAllianceFile()
        calculations.calculateAlliancePoints(teams,alliances)

        //win points
        val eliminatedAlliances = reader.readEliminationOrder()
        calculations.calculateWinPoints(teams,alliances,eliminatedAlliances)

        //get awards
        val awards = parseAwardFile(awardFilePath)
        calculations.calculateAwardPoints(teams,awards)
        //total
        for( team in teams){
            team.total = team.qualificationPoints + team.alliancePoints + team.playOffPoints + team.awardPoints
            advancement.add(Advancement(team.teamName,team.teamNumber,team.total!!))
        }
        return advancement.sortedByDescending { it.advancementPoints}
    }
}