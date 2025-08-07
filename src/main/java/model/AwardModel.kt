package model

import java.io.BufferedReader
import java.io.InputStreamReader

data class AwardModel(
    val inspire1: String,
    val inspire2: String,
    val inspire3: String,
    val firstPlace: List<String> = emptyList(),
    val secondPlace: List<String> = emptyList(),
    val thirdPlace: List<String> = emptyList()
)

fun parseAwardFile(filePath: String): AwardModel {
    val inputStream = object {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("Resource not found: $filePath")
    val reader = BufferedReader(InputStreamReader(inputStream))

    val map = mutableMapOf<String, List<String>>()

    reader.useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(",").map { it.trim() }
            if (parts.isNotEmpty()) {
                val key = parts[0]
                val values = if (parts.size > 1) parts.drop(1) else emptyList()
                map[key] = values
            }
        }
    }

    return AwardModel(
        inspire1 = map["inspire1"]?.firstOrNull() ?: "",
        inspire2 = map["inspire2"]?.firstOrNull() ?: "",
        inspire3 = map["inspire3"]?.firstOrNull() ?: "",
        firstPlace = map["firstPlace"] ?: emptyList(),
        secondPlace = map["secondPlace"] ?: emptyList(),
        thirdPlace = map["thirdPlace"] ?: emptyList()
    )
}