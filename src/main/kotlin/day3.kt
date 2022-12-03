fun main() {
    Day3.run()
}

object Day3 : Solution<List<String>> {
    override val day: String = "day3"
    override val parser: Parser<List<String>> = Parser.strings(day)

    private const val priorities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun part1(input: List<String>): Int =
        input.fold(0) { sumOfPriorities: Int, rucksack: String ->
            println("=====")
            val part1 = rucksack.take(rucksack.length / 2).toList()
            val part2 = rucksack.takeLast(rucksack.length / 2).toList()
            println("Part1: $part1")
            println("Part2: $part2")
            val common = part1.intersect(part2).first()
            println("common element: $common")
            val itemPriority = priorityOf(common)
            println("item priority: $itemPriority")
            sumOfPriorities + itemPriority
        }

    private fun priorityOf(common: Char) = priorities.indexOf(common) + 1

    override fun part2(input: List<String>): Int {
        return input
            .chunked(3)
            .fold(0) { sumOfPriorities, group ->
                val (first, second, third) = group.map { it.toSet() }
                val common = third.intersect(first.intersect(second)).first()
                sumOfPriorities + priorityOf(common)
            }
    }
}