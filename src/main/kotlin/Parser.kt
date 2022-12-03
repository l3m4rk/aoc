fun interface Parser<T> {

    fun parse(input: List<String>): T

    companion object {
        fun strings(name: String): Parser<List<String>> = Parser { readInput(name) }
        fun ints(name: String): Parser<List<Int>> = Parser { readInput(name).map { it.toInt() } }
    }
}
