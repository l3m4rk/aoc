fun main() {
    Day8.run()
}

object Day8 : Solution<List<List<Int>>> {
    override val day = "day8"
    override val parser = Parser { readInput(day).map { it.map { it.digitToInt() } } }

    override fun part1(input: List<List<Int>>): Int {
        val visibleTreesEdge = input.size * 2 + input[0].size * 2 - 4
        val visibleTreesInside = countTreesVisibleInside(input)
        return visibleTreesEdge + visibleTreesInside
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
        for (i in 0..grid.lastIndex) {
            for (j in 0..grid[i].lastIndex) {
                val treeHeight = grid[i][j]

                //left 0 to j - 1, i = const
                var left = 0
                for (k in j - 1 downTo 0) {
                    left++
                    if (grid[i][k] >= treeHeight) {
                        break
                    }
                }
                if (left == 0) left = 1

                // right j + 1 .. grid[i].lastIndex, i = const
                var right = 0
                for (k in j + 1..grid[i].lastIndex) {
                    right++
                    if (grid[i][k] >= treeHeight) {
                        break
                    }
                }
                if (right == 0) right = 1

                // up i: from i - 1 to 0 j = const
                var up = 0
                for (k in i - 1 downTo 0) {
                    up++
                    if ((grid[k][j] >= treeHeight)) {
                        break
                    }
                }
                if (up == 0) up = 1

                // bottom i + 1 .. grid.lastIndex , j = const
                var down = 0
                for (k in i + 1..grid.lastIndex) {
                    down++
                    if (grid[k][j] >= treeHeight) {
                        break
                    }
                }
                if (down == 0) down = 1

                val scenicScore = up * down * left * right
                scenicScores += scenicScore
                trees += Tree(i, j, treeHeight, scenicScore)
            }
        }
        return scenicScores.max()
    }

    override fun part2(input: List<List<Int>>): Int {
        return countTreesScenicScore(input)
    }
}
