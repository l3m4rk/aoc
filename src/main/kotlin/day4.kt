fun main() {
    Day4.run()
}

private infix fun IntRange.fullyOverlaps(range: IntRange): Boolean {
    return contains(range.first) && contains(range.last) ||
            range.contains(first) && range.contains(last)
}

private infix fun IntRange.overlaps(range: IntRange): Boolean {
    return contains(range.first) || contains(range.last) ||
            range.contains(first) || range.contains(last)
}

object Day4 : Solution<List<Pair<IntRange, IntRange>>> {
    override val day: String = "day4"
    override val parser = Parser {
        readInput(day).map { line ->
            line.split(",")
                .let { (first, second) ->
                    val (firstStart, firstEnd) = first.split("-").map { it.toInt() }
                    val (secondStart, secondEnd) = second.split("-").map { it.toInt() }
                    Pair(firstStart..firstEnd, secondStart..secondEnd)
                }
        }
    }

    override fun part1(input: List<Pair<IntRange, IntRange>>): Int {
        return input.count { (r1, r2) -> r1 fullyOverlaps r2 }
    }

    override fun part2(input: List<Pair<IntRange, IntRange>>): Int {
        return input.count { (r1, r2) -> r1 overlaps r2 }
    }

}