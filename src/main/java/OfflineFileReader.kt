import model.AllianceModel
import model.Team

open class OfflineFileReader {

     fun readRankFile(): List<Team>{
        val teams = mutableListOf<Team>()
        val inputStream = object {}.javaClass.getResourceAsStream("/rank.txt")
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    val parts = it.split(",").map { it.trim() }
                    teams.add(Team(parts[1],parts[2],parts[0]))
                }
            }
        } else {
            println("rank.txt not found!")
        }
        return teams
    }

    fun readAllianceFile():List<AllianceModel>{
        val allianceModels = mutableListOf<AllianceModel>()
        val inputStream = object {}.javaClass.getResourceAsStream("/alliances.txt")
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    val parts = it.split(",").map { it.trim() }
                    allianceModels.add(AllianceModel(parts[0],parts[1],parts[2]))
                }
            }
        } else {
            println("alliances.txt not found!")
        }
        return allianceModels
    }

    fun readEliminationOrder():List<Int>{
        val eliminatedAlliance = mutableListOf<Int>()
        val inputStream = object {}.javaClass.getResourceAsStream("/eliminationOrder.txt")
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    eliminatedAlliance.add(it.toInt())
                }
            }
        } else {
            println("eliminationOrder.txt not found!")
        }
        return eliminatedAlliance
    }
}