class Day1 : Solution<List<Int>> {
    override val day: String = "day1"

    override fun run() {
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

    override fun part1(input: List<Int>): Int {
        TODO("Not yet implemented")
    }

    override fun part2(input: List<Int>): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    Day1().run()
}
