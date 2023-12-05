import java.io.File

internal fun readInput(name: String, year: String? = null): List<String> {
    val path = if (year == null) "src/main/resources/$name.txt" else "src/main/resources/$year/$name.txt"
    return File(path)
        .readLines()
}

internal fun readNumbers(name: String): List<Int> =
    readInput(name).map { it.toInt() }
