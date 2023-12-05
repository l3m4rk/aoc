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
    }

    private fun String.findNumber(): Int {
        return this
            .filter { it.isDigit() }
            .map { it.digitToInt() }
            .let { it.first() * 10 + it.last() }
    }

    private fun String.allIndexesOf(key: String): List<Pair<Int, Int?>> {
        val value = VALID_DIGITS[key]
        return Regex(key).findAll(this).map { it.range.first }.toList().map { it to value }
    }

    private fun String.allIndexesOf(char: Char): List<Pair<Int, Int>> {
        return this.mapIndexedNotNull { index, c -> index.takeIf { c == char } }.toList().map { it to char.digitToInt() }
    }

    private fun String.findNumberWithDigitsAndCharacters(): Int {
//        println("String: $this")
        val stringPairs = mutableListOf<Pair<Int, Int?>>()
        val digitPairs = mutableListOf<Pair<Int, Int?>>()

        VALID_DIGITS.keys.forEach { k ->
            stringPairs += allIndexesOf(k)
        }

//        println("String pairs $stringPairs")

        this
            .filter { it.isDigit() }
            .forEach {
                digitPairs += allIndexesOf(it)
            }

//        println("Digit pairs $digitPairs")

        val pairs = (stringPairs + digitPairs).sortedBy { it.first }
//        println("All pairs $pairs")
        val firstDigit = pairs.first().second!!
        val lastDigit = pairs.last().second!!
        val result = firstDigit * 10 + lastDigit
//        println("first $firstDigit, last $lastDigit, result = $result")
        return result
    }

    private val VALID_DIGITS = hashMapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    override fun part2(input: List<String>): Int {
        return input
            .map { it.findNumberWithDigitsAndCharacters() }
            .foldRight(0) { i: Int, acc: Int -> acc + i }
    }
}
