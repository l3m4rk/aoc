fun main() {
    Day6.run()
    // TODO: remove tests from here
    listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
    ).forEach { (testInput, expectedResult) ->
        assert(Day6.part1(testInput) == expectedResult)
    }
}

object Day6 : Solution<String> {
    override val day = "day6"
    override val parser: Parser<String> = Parser { readInput(day).first() }

    // too bad it's O(n2)
    private fun String.isMarker(): Boolean {
        for (ch in this) {
            if (this.count { it == ch } > 1) return false
        }
        return true
    }

    override fun part1(input: String): Int {
        return input
            .windowed(4)
            .first { it.isMarker() }
            .let { marker ->
                val index = input.indexOf(marker) + marker.length
                println("marker $marker with index $index")
                index
            }
    }

    override fun part2(input: String): Int {
        return 0 // TODO: part 2
    }
}