fun main() {
    Day10.run()
}

object Day10 : Solution<List<String>> {
    override val day: String = "day10_test"
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

    override fun part1(input: List<String>): Int {
        var registerX = 1
        var currentCycle: Int = 0
        var resultSignal = 0

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

    private const val lit = '#'
    private const val dark = '.'
    private val CRT = Array(6) { CharArray(40) { dark } }

    private fun showCRT() {
        repeat(6) { row ->
            repeat(40) { column ->
                print(CRT[row][column])
            }
            println()
        }
    }
    override fun part2(input: List<String>): Int {
        var registerX = 1
        var currentCycle = 0

        val rows = 6
        val columns = 40

        val memory = CharArray(240)
        return input
            .parseCommands()
            .forEachIndexed { i, command ->
                if (i == 1) {
                    println("index $i command $command")
                    return@forEachIndexed
                }
//                println("$command ===========")
                when (command) {
                    is Command.Add -> {
                        //draw pixel
                        var i = currentCycle / columns
                        var j = currentCycle % columns
//                        CRT[i][j] = lit
                        currentCycle++
                        memory[currentCycle - 1] = lit

                        //draw pixel
                        i = currentCycle / columns
                        j = currentCycle % columns
//                        CRT[i][j] = lit
                        currentCycle++
                        memory[currentCycle - 1] = lit


                        i = currentCycle / columns
                        j = currentCycle % columns
//                        CRT[i][j] = lit
                        memory[currentCycle] = lit

                        registerX += command.n
                        if (memory[currentCycle - 1] == memory[currentCycle - 2] &&
                            memory[currentCycle - 1] == memory[currentCycle]) {
                            CRT[i][registerX] = lit
                        } else {
                            CRT[i][registerX] = dark
                        }

                        // debug remove
                        if (currentCycle in 0..4) {
                            showCRT()
                        }
                    }
                    Command.Noop -> {
                        currentCycle++
                    }
                }
            }
//            .also { showCRT() }
            .let {
                -1
            }
    }
}
