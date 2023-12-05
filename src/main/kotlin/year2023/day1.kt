package year2023

import Parser
import Solution

// https://adventofcode.com/2023/day/1

fun main() {
    Day1.run()
}

object Day1 : Solution<List<String>> {
    override val day: String = "day1"

    override val year: String
        get() = "2023"
    override val parser: Parser<List<String>>
        get() = Parser.strings(day, year)

    override fun part1(input: List<String>): Int {
        return input
            .map { it.findNumber() }
            .foldRight(0) { i: Int, acc: Int -> acc + i }
            .also(::println)
    }

    private fun String.findNumber(): Int {
        return this
            .filter { it.isDigit() }
            .map { it.digitToInt() }
            .let { it.first() * 10 + it.last() }
    }

    override fun part2(input: List<String>): Int {
        return -1
    }
}
