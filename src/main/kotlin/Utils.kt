import java.io.File

internal fun readInput(name: String): List<String> {
    return File("src/main/resources/$name.txt")
        .readLines()
}

internal fun readNumbers(name: String): List<Int> =
    readInput(name).map { it.toInt() }
