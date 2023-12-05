fun interface Parser<T> {

    fun parse(input: List<String>): T

    companion object {
        fun line(name: String): Parser<String> = Parser { readInput(name).first() }
        fun strings(name: String, year: String? = null): Parser<List<String>> = Parser { readInput(name, year) }
        fun ints(name: String): Parser<List<Int>> = Parser { readInput(name).map { it.toInt() } }
    }
}
