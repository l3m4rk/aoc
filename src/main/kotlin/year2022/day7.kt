package year2022

import Parser
import Solution
import java.util.LinkedList

fun main() {
    Day7.run()
}

object Day7 : Solution<List<String>> {
    //    override val day: String = "day7_test"
    override val day: String = "day7"
    override val parser = Parser.strings(day)

    class File(
        val name: String,
        val files: MutableList<File> = mutableListOf(),
        val isDirectory: Boolean = false,
        val parent: File? = null,
        val size: Long = 0,
    ) {
        fun size(): Long = if (isDirectory) {
            files.sumOf { if (it.isDirectory) it.size() else it.size }
        } else size

        override fun toString(): String {
            return "$name"
        }

        fun root(): File {
            var p = this
            while (p.parent != null) {
                p = p.parent!!
            }
            return p
        }
    }

    override fun part1(input: List<String>): Int {
        var currentFile: File? = null
        input.forEach { line ->
            if (line.startsWith('$')) {
                val commandAndArgs = line.split(' ').drop(1)
                when (commandAndArgs.first()) {
                    "cd" -> {
                        val destination = commandAndArgs.last()
                        currentFile = when (destination) {
                            "/" -> File(destination, isDirectory = true)
                            ".." -> currentFile!!.parent
                            else -> currentFile!!.files.first { it.name == destination }
                        }
                    }

                    "ls" -> {
                        // skip
                        // we will read all the files below
                    }
                }
            } else {
                // we are processing the items listed by ls command
                val (first, name) = line.split(" ")
                when (first) {
                    "dir" -> {
                        val dir = File(name, isDirectory = true, parent = currentFile)
                        currentFile!!.files += dir
                    }

                    else -> {
                        val fileSize = first.toLong()
                        val file = File(name, size = fileSize, parent = currentFile)
                        currentFile!!.files += file
                    }
                }
            }
        }
        val current = currentFile!!.root()

        findDirs(current)
            .map { it to it.size() }
            .sortedBy { it.second }
            .also { println(it) }
            .let { dirsWithSizes ->
                val spaceThreshold = 100_000
                var totalSize = 0L
                for (i in 0..dirsWithSizes.lastIndex) {
                    var s = 0L
                    for (j in i..dirsWithSizes.lastIndex) {
                        if (totalSize + s > spaceThreshold) {
                            break
                        } else {
                            totalSize += s
                        }
                        s += dirsWithSizes[j].second
                    }
//                    val (_, size) = dirsWithSizes[i]
//                    totalSize += size
//                    val (_, nextSize) = dirsWithSizes[i + 1]
//                    println("Next ${totalSize + nextSize}")
//                    if (totalSize + nextSize > spaceThreshold) {
//                        break
//                    }
                }
                totalSize
            }
            .also { println(it) }
        return 0
    }

    private fun findDirs(root: File): List<File> {
        val r = mutableListOf<File>()
        val queue = LinkedList<File>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val f = queue.removeFirst()
            f.files.filter { it.isDirectory }.forEach { queue.addLast(it) }
            r += f
        }
        return r
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}