fun main() {
    fun getSequence(input: String, lengthOfMarker: Int): String {
        // mjqjpqmgbljsphdztnvjfqwrcgsmlb
        val arr = input.toCharArray().map { c -> c.code }
        val windows = arr.windowed(size = lengthOfMarker, step = 1)
        for (window in windows) {
            if (window.size == window.toSet().size) {
                return window.map { code -> code.toChar() }.joinToString("")
            }
        }
        return ""
    }

    fun findEndIndex(input: String, testinput: String): Int {
        val start = input.indexOf(testinput)

        return start + testinput.length
    }


    fun part1(input: List<String>): Int {
        val inp = input[0]
        val target = getSequence(inp, 4)
        return findEndIndex(inp, target)
    }

    fun part2(input: List<String>): Int {
        val inp = input[0]
        val target = getSequence(inp, 14)
        return findEndIndex(inp, target)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("input/Day06")
    println(part1(input))
    println(part2(input))
}
