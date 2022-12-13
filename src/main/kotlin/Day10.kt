fun main() {
    Day10.run()
}

object Day10 : Solution<List<String>> {
    override val day: String = "day10"
    override val parser = Parser.strings(day)

    private const val ADD = "addx"
    private const val ADD_CYCLES = 2
    private const val NOOP = "noop"
    private const val NOOP_CYCLES = 1

    private val cyclesForSignal = listOf(20, 60, 100, 140, 180, 220)

    sealed interface Command {

        val cycles: Int
        data class Add(
            val n: Int,
            override val cycles: Int = ADD_CYCLES
        ) : Command
        object Noop : Command {
            override val cycles: Int = NOOP_CYCLES
        }
    }

    private fun List<String>.parseCommands(): List<Command> {
        return this.map { line ->
            if (line.startsWith(ADD)) {
                line.split(" ")
                    .last()
                    .toInt()
                    .let { Command.Add(it) }
            } else if (line.startsWith(NOOP)) {
                Command.Noop
            } else {
                throw IllegalStateException("Unknown command!")
            }
        }
    }

    private var registerX = 1
    private var currentCycle: Int = 0
    private var resultSignal = 0

    override fun part1(input: List<String>): Int {
        return input
            .parseCommands()
//            .also { println(it) }
            .forEach { command ->
                when (command) {
                    is Command.Add -> {
                        currentCycle++
                        if (currentCycle in cyclesForSignal) {
                            resultSignal += registerX * currentCycle
                        }
                        currentCycle++
                        if (currentCycle in cyclesForSignal) {
                            resultSignal += registerX * currentCycle
                        }
                        registerX += command.n
                    }
                    Command.Noop -> {
                        currentCycle++
                        if (currentCycle in cyclesForSignal) {
                            resultSignal += registerX * currentCycle
                        }
                    }
                }
            }
            .let {
                resultSignal
            }
    }

    override fun part2(input: List<String>): Int {
        return -1
    }
}
