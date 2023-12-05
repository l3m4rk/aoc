import kotlin.system.measureTimeMillis

// TODO(refactor): add In, Out types to support different type of results
interface Solution<T> {
    val day: String

    val year: String
        get() = "2022"

    val parser: Parser<T>

    fun run() {
        println("Welcome to Advent of Code $year $day 🎄✨🎅🏻")

        parser.parse(readInput(day))
            .also { input ->
                val result1: Int
                val elapsed1 = measureTimeMillis {
                    result1 = part1(input)
                }
                println("Part 1 result: $result1, in $elapsed1 milliseconds")
                val result2: Int
                val elapsed2 = measureTimeMillis {
                    result2 = part2(input)
                }
                println("Part 2 result: $result2, in $elapsed2 milliseconds")
            }
    }

    fun part1(input: T): Int

    fun part2(input: T): Int

}
