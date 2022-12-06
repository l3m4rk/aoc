fun main() {
    Day1.run()
}

object Day1 : Solution<List<List<String>>> {
    override val day = "day1"
    override val parser = Parser { readInput(day).splitByEmpty() }

    override fun part1(input: List<List<String>>): Int {
        return input.map { it.sumOf { it.toInt() } }.max()
    }

    // TODO: consider moving to utils and generalize
    fun List<String>.splitByEmpty(): List<List<String>> {
        val result = mutableListOf<MutableList<String>>()
        var current = mutableListOf<String>()
        for (s in this) {
            if (s.isNotEmpty()) {
                current += s
            } else {
                result += current
                current = mutableListOf()
            }
        }
        return result
    }

    override fun part2(input: List<List<String>>): Int {
        return input.map { it.sumOf { it.toInt() } }.sorted().takeLast(3).sum()
    }
}
