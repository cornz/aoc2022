import java.util.*

fun main() {
    fun getDirSizes(input: List<String>): HashMap<String, Int> {
        val directories = Stack<String>()
        val directorySizes = HashMap<String, Int>()

        for ((index, line) in input.withIndex()) {
            val splitLine = line.split(" ")

            if (splitLine[1] == "cd") {
                if (splitLine[2] == "..") {
                    directories.pop()
                } else {
                    directories.push(splitLine[2] + "_" + index)
                }
            } else if (splitLine[0].toIntOrNull() != null) {
                val size = splitLine[0].toInt()

                for (directory in directories) {
                    if (directorySizes.containsKey(directory)) {
                        directorySizes[directory] = directorySizes.getValue(directory) + size
                    } else {
                        directorySizes[directory] = size
                    }
                }
            }
        }

        return directorySizes
    }


    fun part1(input: List<String>): Int {
        return getDirSizes(input).values.filter { it <= 100000 }.sum()

    }

    fun part2(input: List<String>): Int {
        val spaceAvailable = 70000000
        val spaceUnused = spaceAvailable - getDirSizes(input).values.max()
        val spaceRequired = 30000000 - spaceUnused
        return getDirSizes(input).values.filter { it - spaceRequired > 0 }.min()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("input/Day07")
    println(part1(input))
    println(part2(input))
}
