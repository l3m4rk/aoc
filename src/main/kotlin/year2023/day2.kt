package year2023

import Parser
import Solution

// https://adventofcode.com/2023/day/2

fun main() {
    Day2.run()
}

object Day2 : Solution<List<String>> {
    override val day: String
        get() = "day2"

    override val year: String = "2023"
    override val parser: Parser<List<String>> = Parser.strings(day, year)

    private const val RED_CUBES_COUNT = 12u
    private const val GREEN_CUBES_COUNT = 13u
    private const val BLUE_CUBES_COUNT = 14u

    private data class Game(val id: Int, val cubeSets: List<CubeSet>, val isPossible: Boolean)

    private data class CubeSet(val red: UInt, val green: UInt, val blue: UInt)

    private fun CubeSet.isPossible(): Boolean {
        return red <= RED_CUBES_COUNT &&
                blue <= BLUE_CUBES_COUNT &&
                green <= GREEN_CUBES_COUNT
    }

    override fun part1(input: List<String>): Int {
        return input
            .map(::parseGame)
            .filter(Game::isPossible)
            .sumOf(Game::id)
            .also(::println)
    }

    private fun parseGame(gameInput: String): Game {
        val cubeSets = mutableListOf<CubeSet>()
        val gameId = gameInput.substringBefore(':').split(" ")[1].toInt()
        println("gameId: $gameId")
        val sets = gameInput.substringAfter(":").split(';')
        println("sets: $sets")
        cubeSets += sets.map(::parseSet)
        println("cube sets: $cubeSets")
        val isPossible = cubeSets.all { it.isPossible() }
        return Game(gameId, cubeSets, isPossible)
    }


    /**
     * Converts input like
     * 14 green, 6 blue, 12 red
     * into [CubeSet] object
     */
    private fun parseSet(setInput: String): CubeSet {
        return setInput.split(",").let { draws ->
            var red = 0u
            var green = 0u
            var blue = 0u
            for (draw in draws) {
                val d = draw.trim().split(" ")
                val amount = d[0].toUInt()
                val type = d[1]

                when (type) {
                    "green" -> green += amount
                    "blue" -> blue += amount
                    "red" -> red += amount
                }
            }
            CubeSet(red, green, blue)
        }
    }

    override fun part2(input: List<String>): Int {
        return -1
    }

}
