private const val DAY = "day1"

fun main() {
    println("Welcome to Advent of Code $DAY 🎄✨🎅🏻")

    val elvesAndSnacks = mutableListOf<Int>()

    val currentCalories = mutableListOf<Int>()
    readInput(DAY)
        .map { it.toIntOrNull() }
        .forEach {
            if (it == null) {
                elvesAndSnacks += currentCalories.sum()
                currentCalories.clear()
            } else {
                currentCalories += it
            }
        }
    val resultPart1 = elvesAndSnacks.max()
    val resultPart2 = elvesAndSnacks.sorted().takeLast(3).sum()
    println("Part 1 result $resultPart1")
    println("Part 2 result $resultPart2")
}
