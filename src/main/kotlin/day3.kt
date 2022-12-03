fun main() {
    Day3.run()
}

object Day3 : Solution<List<String>> {
    override val day: String = "day3"

    private const val priorities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun run() {
        super.run()

        readInput(day)
            .fold(0) { sumOfPriorities: Int, rucksack: String ->
                println("=====")
                val part1 = rucksack.take(rucksack.length / 2).toList()
                val part2 = rucksack.takeLast(rucksack.length / 2).toList()
                println("Part1: $part1")
                println("Part2: $part2")
                val common = part1.intersect(part2).first()
                println("common element: $common")
                val itemPriority = priorities.indexOf(common) + 1
                println("item priority: $itemPriority")
                sumOfPriorities + itemPriority
            }
            .also { println(it) }
    }

    override fun part1(input: List<String>): Int {
        TODO("Not yet implemented")
    }

    override fun part2(input: List<String>): Int {
        TODO("Not yet implemented")
    }


}