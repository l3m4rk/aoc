fun main() {
    Day8.run()
}

object Day8 : Solution<List<String>> {
    override val day = "day8"
    override val parser = Parser.strings(day)

    override fun part1(input: List<String>): Int {
        return input
            .fold(mutableListOf()) { grid: MutableList<MutableList<Int>>, line: String ->
                grid.apply {
                    add(line.toCharArray().map { it.digitToInt() }.toMutableList())
                }
            }
//            .also { printGrid(it) }
            .let { grid ->
                val visibleOnTheEdge = grid.size * 2 + grid[0].size * 2 - 4
                println("visible on the edge: $visibleOnTheEdge")
                val visibleInside = countTreesVisibleInside(grid)
                println("visible inside: $visibleInside")
                visibleOnTheEdge + visibleInside
            }
    }

    private fun countTreesVisibleInside(grid: MutableList<MutableList<Int>>): Int {
        var visibleTreesCount = 0
        for (i in 1 until grid.lastIndex) {
            for (j in 1 until grid[i].lastIndex) {
                val tree = grid[i][j]

                //left 0 to j - 1, i = const
                var left = true
                for (k in 0 until j) {
                    if (grid[i][k] >= tree) {
                        left = false
                        break
                    }
                }
                // right j + 1 .. grid[i].lastIndex, i = const
                var right = true
                for (k in j + 1..grid[i].lastIndex) {
                    if (grid[i][k] >= tree) {
                        right = false
                        break
                    }
                }
                // top i: 0..i - 1 j = const
                var top = true
                for (k in 0 until i) {
                    if (grid[k][j] >= tree) {
                        top = false
                        break
                    }
                }

                // bottom i + 1 .. grid.lastIndex , j = const
                var bottom = true
                for (k in i + 1..grid.lastIndex) {
                    if (grid[k][j] >= tree) {
                        bottom = false
                        break
                    }
                }

                if (left || right || top || bottom) {
                    visibleTreesCount++
                }
            }
        }
        return visibleTreesCount
    }

    override fun part2(input: List<String>): Int {
        return -1
    }
}

// TODO: generalize and move to utils
fun printGrid(grid: MutableList<MutableList<Int>>) {
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            print("${grid[i][j]} ")
        }
        println()
    }
}

fun removeTreesAroundTheEdgeAndCountThem(grid: Array<CharArray>): Array<CharArray> {


    return grid
}
