fun main() {
    Day9.run()
}

object Day9 : Solution<List<Pair<String, Int>>> {
    override val day: String = "day9"

    //    override val day: String = "day9_test"
    override val parser = Parser {
        readInput(day).map {
            it.split(" ").let { it.first() to it.last().toInt() }
        }
    }

    data class Point(var x: Int, var y: Int)

    override fun part1(input: List<Pair<String, Int>>): Int {
        val moves = input

        val visitedPoints = mutableSetOf<Pair<Int, Int>>()

        var tailI = 0
        var tailJ = 0
        var headI = 0
        var headJ = 0

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

    override fun part2(input: List<Pair<String, Int>>): Int {
        val knots = MutableList(10) { Point(0, 0) }
        val moves = input
        val head = knots.first()
        val tail = knots.last()

        val visitedPoints = mutableSetOf<Point>()

        fun moveKnot(current: Point, next: Point) {
            // move Right
            if (current.y == next.y + 2) {
                next.y++
                if (current.x > next.x) next.x++
                if (current.x < next.x) next.x--
            }
            // move Left
            if (current.y == current.y - 2) {
                next.y--
                if (current.x > next.x) next.x++
                if (current.x < next.x) next.x--
            }
            // move Down
            if (current.x == next.x + 2) {
                next.x++
                if (current.y > next.y) next.y++
                if (current.y < next.y) next.y--
            }
            // move UP
            if (current.x == next.x - 2) {
                next.x--
                if (current.y > next.y) next.y++
                if (current.y < next.y) next.y--
            }
        }

        moves.forEach { (direction, steps) ->
            repeat(steps) {
                when (direction) {
                    "R" -> head.y++
                    "L" -> head.y--
                    "U" -> head.x--
                    "D" -> head.x++
                    else -> throw IllegalArgumentException("Unknown direction!")
                }
                knots.chunked(2).forEach { (current, next) ->
                    moveKnot(current, next)
                }
                visitedPoints += knots.last()
            }
        }
        return visitedPoints.size
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
