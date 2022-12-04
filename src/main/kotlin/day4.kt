fun main() {
    Day4.run()
}

data class Range(
    val start: Int,
    val end: Int
) {
    fun contains(e: Int): Boolean = e in start..end
}

// 3-4 2-6
// 2-6 3-4
fun fullyOverlap(r1: Range, r2: Range): Boolean {
    return (r1.contains(r2.start) && r1.contains(r2.end)) ||
            (r2.contains(r1.start) && r2.contains(r1.end))
}

object Day4 : Solution<List<String>> {
    override val day: String = "day4"
    override val parser = Parser { readInput(day) }

    override fun part1(input: List<String>): Int {
        return input
            .map { line ->
                line.split(",")
                    .let { (first, second) ->
                        val (firstStart, firstEnd) = first.split("-").map { it.toInt() }
                        val first = Range(firstStart, firstEnd)
                        val (secondStart, secondEnd) = second.split("-").map { it.toInt() }
                        val second = Range(secondStart, secondEnd)
                        Pair(first, second)
                    }
            } // TODO extract parsing
            .fold(0) { fullyContainedIntervals: Int, (r1, r2): Pair<Range, Range> ->
                fullyContainedIntervals + when {
                    fullyOverlap(r1, r2) -> 1
                    else -> 0
                }
            }
    }

    override fun part2(input: List<String>): Int {
        return 0 // TODO
    }

}