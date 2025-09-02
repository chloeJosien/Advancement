import model.*

open class AdvancementCalculator {
    private val reader: OfflineFileReader = OfflineFileReader()
    private val calculations: Calculations = Calculations()

    open fun manualAdvancement(rankFilePath: String, allianceFilePath: String, eliminationOrderFilePath: String, awardFilePath: String): List<Advancement>{
        //read in all the files
        val teams = reader.readRankFile(rankFilePath)
        val alliances = reader.readAllianceFile(allianceFilePath)
        val eliminatedAlliances = reader.readEliminationOrder(eliminationOrderFilePath)
        val awards = parseAwardFile(awardFilePath)

        return advancement(teams, alliances, eliminatedAlliances, awards)
    }

    private fun advancement(teams:List<Team>, alliances: List<AllianceModel>, eliminatedAlliances: List<Int>, awards: AwardModel) : List<Advancement>{
        val advancement = mutableListOf<Advancement>()

        //get qual ranking points
        val competingTeams = teams.filter { !it.rank.isNullOrEmpty() } //since not all teams compete in the robot games
        calculations.calulateQualPointsAllTeams(competingTeams)

        //get alliances
        calculations.calculateAlliancePoints(teams,alliances)

        //win points
        calculations.calculateWinPoints(teams,alliances,eliminatedAlliances)

        //get awards
        calculations.calculateAwardPoints(teams,awards)
        //total
        for( team in teams){
            team.total = team.qualificationPoints + team.alliancePoints + team.playOffPoints + team.awardPoints
            advancement.add(Advancement(team.teamName,team.teamNumber,team.total!!))
        }
        return advancement.sortedByDescending { it.advancementPoints}
    }
}