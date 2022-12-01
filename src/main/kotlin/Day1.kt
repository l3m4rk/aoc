fun main() {
    println("Welcome to Advent of Code 🎄✨🎅🏻")

    val elvesAndSnacks = mutableListOf<Int>()

    val currentCalories = mutableListOf<Int>()
    readInput("day1")
        .map { it.toIntOrNull() }
        .forEach {
            if (it == null) {
                elvesAndSnacks += currentCalories.sum()
                currentCalories.clear()
            } else {
                currentCalories += it
            }
        }
    val result = elvesAndSnacks.max()
    println(result)
}
