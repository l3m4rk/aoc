fun main() {
    Day6.run()
}

object Day6 : Solution<String> {
    override val day = "day6"
    override val parser: Parser<String> = Parser.line(day)

    // too bad it's O(n2)
    private fun String.isMarker(): Boolean {
        for (ch in this) {
            if (this.count { it == ch } > 1) return false
        }
        return true
    }

    // start-of-packet marker
    override fun part1(input: String): Int {
        return input
            .windowed(4)
            .first { it.isMarker() }
            .let { marker -> input.indexOf(marker) + marker.length }
    }

    // start-of-message marker
    override fun part2(input: String): Int {
        return input
            .windowed(14)
            .first { it.isMarker() }
            .let { marker -> input.indexOf(marker) + marker.length }
    }
}