fun main() {
    Day8.run()
}

object Day8 : Solution<List<List<Int>>> {
    override val day = "day8"
    override val parser = Parser {
        readInput(day)
            .fold(mutableListOf()) { grid: MutableList<List<Int>>, line: String ->
                grid.apply {
                    add(line.toCharArray().map { it.digitToInt() })
                }
            }.toList()
    }

    override fun part1(input: List<List<Int>>): Int {
        return input
            .let { grid ->
                val visibleTreesEdge = grid.size * 2 + grid[0].size * 2 - 4
                val visibleTreesInside = countTreesVisibleInside(grid)
                visibleTreesEdge + visibleTreesInside
            }
    }

    private fun countTreesVisibleInside(grid: List<List<Int>>): Int {
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

    data class Tree(
        val i: Int,
        val j: Int,
        val height: Int,
        val scenicScore: Int
    )
    private fun countTreesScenicScore(grid: List<List<Int>>): Int {
        val scenicScores = mutableListOf<Int>()
        val trees = mutableListOf<Tree>()
        for (i in 0 .. grid.lastIndex) {
            for (j in 0 .. grid[i].lastIndex) {
                val treeHeight = grid[i][j]

                //left 0 to j - 1, i = const
                var left = 0
                for (k in j - 1 downTo  0) {
                    if (grid[i][k] < treeHeight) {
                        left++
                    }
                    if (grid[i][k] >= treeHeight) {
                        left++
                        break
                    }
                }
                if (left == 0) left = 1

                // right j + 1 .. grid[i].lastIndex, i = const
                var right = 0
                for (k in j + 1..grid[i].lastIndex) {
                    if (grid[i][k] < treeHeight) {
                        right++
                    }
                    if (grid[i][k] >= treeHeight) {
                        right++
                        break
                    }
                }
                if (right == 0) right = 1

                // up i: from i - 1 to 0 j = const
                var up = 0
                for (k in i - 1 downTo 0) {
                    if (grid[k][j] < treeHeight) {
                        up++
                    }
                    if ((grid[k][j] >= treeHeight)) {
                        up++
                        break
                    }
                }
                if (up == 0) up = 1

                // bottom i + 1 .. grid.lastIndex , j = const
                var down = 0
                for (k in i + 1..grid.lastIndex) {
                    if (grid[k][j] < treeHeight) {
                        down++
                    }
                    if (grid[k][j] >= treeHeight) {
                        down++
                        break
                    }
                }
                if (down == 0) down = 1

                // debug
//                if ((i == 1 && j == 2) || (i == 3 && j == 2)) {
//                    println("($i,$j): up $up left $left down $down right $right")
//                }

                val scenicScore = up * down * left * right
                scenicScores += scenicScore
                trees += Tree(i, j, treeHeight, scenicScore)
            }
        }
        //debug =]
//        trees.chunked(grid.size)
//            .also {
//                println("first score ${it[1][2]}")
//                println("second score ${it[3][2]}")
//            }
        return scenicScores.max()
    }

    override fun part2(input: List<List<Int>>): Int {
        return countTreesScenicScore(input)
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
