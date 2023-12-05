package year2022

import Parser
import Solution
import year2022.Day1.splitByEmpty
import kotlin.math.min

fun main() {
    Day13.run()
}

typealias PairOfInts = List<Pair<List<Int>, List<Int>>>

private object Day13 : Solution<List<String>> {
    override val day: String = "day13_test"
    override val parser = Parser.strings(day)

    override fun part1(input: List<String>): Int {
        var right = 0
        input.splitByEmpty()
            .also { println(it) }
            .forEach {
                val (first, second) = it
                println("current: $first and $second")

                val length = min(first.length, second.length)
                for (i in 0 until length) {
                    val a = first[i]
                    val b = second[i]
                    if (a == b) continue
                    if (a < b) {
                        right++
                        break
                    }
                }

//                pairs +=
            }
        return right
    }

    fun check(part: String) {
        if (part.startsWith("[") && part.endsWith("]")) {
            val p = part.slice(1 until part.lastIndex).split(",")
            p.forEach {
                val intVal = it.toIntOrNull()?.let {

                } ?: check(it)
            }
        }

    }

    override fun part2(input: List<String>): Int {
        return -1
    }

}