class Day1 {
    private val day: String = "day1"

    fun run() {
        val elvesAndSnacks = mutableListOf<Int>()

        val currentCalories = mutableListOf<Int>()
        readInput(day)
            .map { it.toIntOrNull() }
            .forEach { // TODO: refactor to be more functional
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
}

fun main() {
    Day1().run()
}
