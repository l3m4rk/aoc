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

fun overlapAtAll(r1: Range, r2: Range): Boolean {
    return r1.contains(r2.start) || r1.contains(r2.end) ||
            r2.contains(r1.start) || r2.contains(r1.end)
}

object Day4 : Solution<List<Pair<Range, Range>>> {
    override val day: String = "day4"
    override val parser = Parser {
        readInput(day).map { line ->
            line.split(",")
                .let { (first, second) ->
                    val (firstStart, firstEnd) = first.split("-").map { it.toInt() }
                    val (secondStart, secondEnd) = second.split("-").map { it.toInt() }
                    Pair(Range(firstStart, firstEnd), Range(secondStart, secondEnd))
                }
        }
    }

    override fun part1(input: List<Pair<Range, Range>>): Int {
        return input.fold(0) { fullyContainedIntervals: Int, (r1, r2): Pair<Range, Range> ->
            fullyContainedIntervals + when {
                fullyOverlap(r1, r2) -> 1
                else -> 0
            }
        }
    }

    override fun part2(input: List<Pair<Range, Range>>): Int {
        return input.fold(0) { overlappedAtAll: Int, (r1, r2) ->
            overlappedAtAll + if (overlapAtAll(r1, r2)) 1 else 0
        }
    }

}