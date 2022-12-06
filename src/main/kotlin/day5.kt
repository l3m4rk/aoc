fun main() {
    Day5.run()
}

object Day5 : Solution<List<String>> {
    override val day: String = "day5"
    override val parser: Parser<List<String>> = Parser.strings(day)

    private data class Move(
        val amountOfCrates: Int, // amount of crates
        val from: Int, // pile
        val to: Int // pile
    )

    override fun part1(input: List<String>): Int {
        val (rearrangementProcedure, stacksOfCrates) = parseInput(input)

        rearrangeCrates9000(rearrangementProcedure, stacksOfCrates)
            .also { println(it) }
            .values
            .fold(StringBuilder()) { sb, list -> sb.append(list.last()) }
            .also { message -> println("Part 1: $message") }
        return -1 // TODO: return string
    }

    private fun parseInput(input: List<String>): Pair<List<Move>, MutableMap<Int, MutableList<String>>> {
        //input parsing starts
        val stacks = input.takeWhile { it.isNotEmpty() }
        //        stacks.forEachIndexed { index, s ->
        //            println("stack $index: $s")
        //        }

        val rearrangementProcedure =
            input.subList(input.indexOf("") + 1, input.size)
                .map { line ->
                    val count = line.split(" ")[1].toInt()
                    val from = line.split(" ")[3].toInt()
                    val to = line.split(" ")[5].toInt()
                    Move(count, from, to)
                }
        //        println(rearrangementProcedure)

        val cratePiles = stacks
            .dropLast(1)
            .map {
                it.chunked(4)
                    .map {
                        it.trim()
                            .replace("]", "")
                            .replace("[", "")
                            .ifEmpty { "-" }
                    }
            }
            .reversed()
        //        println("crate piles: $cratePiles")

        val stackCount = stacks.last()
            .split(" ")
            .mapNotNull { it.toIntOrNull() }.size
        //        println("stack count $stackCount")

        val stacksOfCrates = mutableMapOf<Int, MutableList<String>>()
        for (indexOfStack in 0 until stackCount) {
            val list = mutableListOf<String>()
            cratePiles.forEach { pile ->
                if (indexOfStack <= pile.lastIndex && pile[indexOfStack] != "-") {
                    list.add(pile[indexOfStack])
                }
            }
            stacksOfCrates.putIfAbsent(indexOfStack + 1, list)
        }
        //        println(stacksOfCrates)
        //input parsing ends
        return Pair(rearrangementProcedure, stacksOfCrates)
    }

    private fun rearrangeCrates9000(
        rearrangementProcedure: List<Move>,
        crates: MutableMap<Int, MutableList<String>>
    ): Map<Int, MutableList<String>> {
        rearrangementProcedure.forEach { command ->
            repeat(command.amountOfCrates) {
                val from = command.from
                val cratePile = crates[from]
                val crate = cratePile!!.removeLast()
                crates[command.to]!!.add(crate)
            }
        }
        return crates
    }

    private fun rearrangeCrates9001(
        rearrangementProcedure: List<Move>,
        crates: MutableMap<Int, MutableList<String>>
    ): Map<Int, MutableList<String>> {
        rearrangementProcedure.forEach { command ->
            val cratesToMove = crates[command.from]!!.takeLast(command.amountOfCrates)
            repeat(command.amountOfCrates) {
                crates[command.from]!!.removeLast()
            }
            crates[command.to]!!.addAll(cratesToMove)
        }
        return crates
    }

    override fun part2(input: List<String>): Int {
        val (rearrangementProcedure, stacksOfCrates) = parseInput(input)

        rearrangeCrates9001(rearrangementProcedure, stacksOfCrates)
            .also { println(it) }
            .values
            .fold(StringBuilder()) { sb, list -> sb.append(list.last()) }
            .also { message -> println("Part 2: $message") }

        return -1
    }
}