package year2022

import Parser
import Solution
import readInput

fun main() {
    Day2.run()
}

object Day2 : Solution<List<List<String>>> {
    override val day = "day2"
    override val parser = Parser { readInput(day).map { it.split(" ") } }

    override fun part1(input: List<List<String>>): Int {
        return input.foldIndexed(0) { _: Int, total: Int, step: List<String> ->
            val yourRoundScore =
                step.map { it.toShapeScore() }
                    .let { (opponentScore, yourScore) ->
                        playRound(yourScore, opponentScore)
                    }

            total + yourRoundScore
        }
    }

    override fun part2(input: List<List<String>>): Int {
        return input.foldIndexed(0) { _: Int, total: Int, step: List<String> ->
            val opponentTurn = step.first()
            val yourTurn = makeChoseForMark(step.last(), opponentTurn)

            val opponentScore = opponentTurn.toShapeScore()
            val yourShapeScore = yourTurn.toShapeScore()

            val yourRoundScore = playRound(yourShapeScore, opponentScore)

            total + yourRoundScore
        }
    }
}

/**
 * New rules
 * Y – draw
 * X - lose
 * Z - win
 */
// X - Rock 1
// Y – Paper 2
// Z - Scissors 3
// Opponent
// A – Rock 1
// B - Paper 2
// C – Scissors 3
private fun makeChoseForMark(mark: String, otherTurn: String): String {
    return when (mark) {
        "X" -> {
            when (otherTurn) {
                "A" -> "Z"
                "B" -> "X"
                "C" -> "Y"
                else -> throw IllegalStateException()
            }
        }

        "Y" -> {
            when (otherTurn) {
                "A" -> "X"
                "B" -> "Y"
                "C" -> "Z"
                else -> throw IllegalStateException()
            }
        }

        "Z" -> {
            when (otherTurn) {
                "A" -> "Y"
                "B" -> "Z"
                "C" -> "X"
                else -> throw IllegalStateException()
            }
        }

        else -> throw IllegalStateException()
    }
}

/**
 * @returns your round result = your shape score + round result calculated like
 * win 6
 * draw 3
 * loose 0
 */
private fun playRound(youShapeScore: Int, otherShapeScore: Int): Int {
    return youShapeScore +
            when (youShapeScore) {
                1 -> { // Rock
                    when (otherShapeScore) {
                        1 -> 3 // Rock
                        2 -> 0 // Paper
                        3 -> 6 // Scissors
                        else -> throw IllegalStateException("Not possible")
                    }
                }

                2 -> { // Paper
                    when (otherShapeScore) {
                        1 -> 6 // Rock
                        2 -> 3 // Paper
                        3 -> 0 // Scissors
                        else -> throw IllegalStateException("Not possible")
                    }
                }

                3 -> { // Scissors
                    when (otherShapeScore) {
                        1 -> 0 // Rock
                        2 -> 6 // Paper
                        3 -> 3 // Scissors
                        else -> throw IllegalStateException("Not possible")
                    }
                }

                else -> throw IllegalStateException("Not possible")
            }
}

// You
// X - Rock 1
// Y – Paper 2
// Z - Scissors 3
// Opponent
// A – Rock 1
// B - Paper 2
// C – Scissors 3
private fun String.toShapeScore(): Int {
    return when (this) {
        "A", "X" -> 1
        "B", "Y" -> 2
        "C", "Z" -> 3
        else -> throw IllegalStateException("Wrong choice")
    }
}

private fun String.toShapeName(): String {
    return when (this) {
        "A", "X" -> "Rock"
        "B", "Y" -> "Paper"
        "C", "Z" -> "Scissors"
        else -> throw IllegalStateException("Can't happen")
    }
}
