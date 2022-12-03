// TODO: use across the days
interface Solution<T> {
    val day: String

    val parser: Parser<T>

    fun run() {
        println("Welcome to Advent of Code $day 🎄✨🎅🏻")

        parser.parse(readInput(day))
            .also { input ->
                val result1 = part1(input)
                println("part 1 result: $result1")
                val result2 = part2(input)
                println("part 2 result: $result2")
            }
    }

    fun part1(input: T): Int

    fun part2(input: T): Int

}
