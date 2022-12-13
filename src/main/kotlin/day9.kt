fun main() {
    Day9.run()
}

object Day9 : Solution<List<String>> {
    override val day: String = "day9"
//    override val day: String = "day9_test"
    override val parser: Parser<List<String>> = Parser.strings(day)


    override fun part1(input: List<String>): Int {
        val moves = input
            .map {
                it.split(" ").let { it.first() to it.last().toInt() }
            }

        val visitedPoints = mutableSetOf<Pair<Int, Int>>()

        val startI = 0
        val startJ = 0
        var tailI = startI
        var tailJ = startJ
        var headI = startI
        var headJ = startJ

        fun moveTail() {
            // move Right
            if (headJ == tailJ + 2) {
                tailJ++
                if (headI > tailI) tailI++
                if (headI < tailI) tailI--
            }
            // move Left
            if (headJ == tailJ - 2) {
                tailJ--
                if (headI > tailI) tailI++
                if (headI < tailI) tailI--

            }
            // move Down
            if (headI == tailI + 2) {
                tailI++
                if (headJ > tailJ) tailJ++
                if (headJ < tailJ) tailJ--
            }
            // move UP
            if (headI == tailI - 2) {
                tailI--
                if (headJ > tailJ) tailJ++
                if (headJ < tailJ) tailJ--
            }
            visitedPoints += tailI to tailJ
        }

        moves.forEach { (direction, steps) ->
            repeat(steps) {
                when (direction) {
                    "R" -> headJ++
                    "L" -> headJ--
                    "U" -> headI--
                    "D" -> headI++
                    else -> throw IllegalArgumentException("Unknown direction!")
                }
                moveTail()
            }
        }
        return visitedPoints.size
    }

    override fun part2(input: List<String>): Int {
        return -1
    }
}

private fun printGrid(
    gridSize: Int,
    headPosition: Pair<Int, Int>,
    tailPosition: Pair<Int, Int>
) {
    val (hI, hj) = headPosition
    val (tI, tj) = tailPosition
    for (i in 0 until gridSize) {
        for (j in 0 until gridSize) {
            if (i == hI && j == hj) {
                print("H")
            } else if (i == tI && j == tj) {
                print("T")
            } else {
                print("*")
            }
            print("\t")
        }
        println()
    }
}
