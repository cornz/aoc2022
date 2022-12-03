fun main() {

    fun charToIntValues(char: Char): Int {
        return if (char.isUpperCase()) {
            char.code - 64 + 26
        } else {
            char.code - 96
        }
    }

    fun findCommonItem(line: String): Char? {
        val mid = line.length / 2
        val parts = arrayOf(line.substring(0, mid), line.substring(mid))
        for (c in parts[0].toCharArray()) {
            for (c2 in parts[1].toCharArray()) {
                if (c == c2) {
                    return c
                }
            }
        }
        return null
    }

    fun findCommonBadge(input: List<String>): Char? {
        for (c in input[0].toCharArray()) {
            if (c in input[1] && c in input[2])
                return c
        }
        return null
    }

    fun part1(input: List<String>): Int {
        val priorityList = arrayListOf<Int>()
        for (line in input) {
            val commonItem = findCommonItem(line)
            val commonItemPriority = commonItem?.let { charToIntValues(it) }
            priorityList.add(commonItemPriority!!)
        }
        return priorityList.sum()
    }

    fun part2(input: List<String>): Int {
        val priorityList = arrayListOf<Int>()
        for (list in input.chunked(3)) {
            val commonItem = findCommonBadge(list)
            val commonItemPriority = commonItem?.let { charToIntValues(it) }
            priorityList.add(commonItemPriority!!)
        }
        return priorityList.sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("input/Day03")
    println(part1(input))
    println(part2(input))
}
