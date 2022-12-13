fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day10_test")
    check(part1(testInput) == 1)
    //check(part2(testInput) == 1)

    val input = readInput("input/Day10")
    println(part1(input))
    println(part2(input))
}
