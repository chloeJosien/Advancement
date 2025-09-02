import model.AllianceModel
import model.Team

open class OfflineFileReader {

     fun readRankFile(fileName: String): List<Team>{
        val teams = mutableListOf<Team>()
        val inputStream = object {}.javaClass.getResourceAsStream(fileName)
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    val parts = it.split(",").map { it.trim() }
                    teams.add(Team(parts[1],parts[2],parts[0]))
                }
            }
        } else {
            println(fileName+ " not found!")
        }
        return teams
    }

    fun readAllianceFile(fileName: String):List<AllianceModel>{
        val allianceModels = mutableListOf<AllianceModel>()
        val inputStream = object {}.javaClass.getResourceAsStream(fileName)
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    val parts = it.split(",").map { it.trim() }
                    allianceModels.add(AllianceModel(parts[0],parts[1],parts[2]))
                }
            }
        } else {
            println(fileName+ " not found!")
        }
        return allianceModels
    }

    fun readEliminationOrder(fileName: String):List<Int>{
        val eliminatedAlliance = mutableListOf<Int>()
        val inputStream = object {}.javaClass.getResourceAsStream(fileName)
        if (inputStream != null) {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    eliminatedAlliance.add(it.toInt())
                }
            }
        } else {
            println(fileName+ " not found!")
        }
        return eliminatedAlliance
    }
}