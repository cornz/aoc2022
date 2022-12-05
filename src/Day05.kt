fun main() {

    fun getStartIndexAndNumberOfStacks(input: List<String>): Pair<Int, Int>? {
        for ((index, value) in input.reversed().withIndex()) {
            if (value.startsWith(" 1")) {
                return Pair(index, value.split(" ").last().toInt())
            }
        }
        return null
    }

    fun createStacks(input: List<String>): List<MutableList<String>> {
        val startIndexAndNumberOfStacks = getStartIndexAndNumberOfStacks(input)
        val stacks = List(startIndexAndNumberOfStacks!!.second) { mutableListOf<String>() }
        val numberOfStacks = startIndexAndNumberOfStacks.second
        for (line in input.reversed().slice(startIndexAndNumberOfStacks.first + 1 until input.size)) {
            val chunks = line.chunked(4)
            for (i in 0 until minOf(numberOfStacks, chunks.size)) {
                val char = chunks[i].trim()
                if (char != "") {
                    stacks[i].add(0, char.replace("[", "").replace("]", ""))
                }
            }
        }
        return stacks
    }

    fun getInstruction(input: String): Triple<Int, Int, Int> {

        // move 1 from 2 to 1
        val inputs = input.split(" ")
        return Triple(inputs[1].toInt(), inputs[3].toInt() - 1, inputs[5].toInt() - 1)
    }


    fun part1(input: List<String>): String {
        val stacks = createStacks(input)
        for (line in input) {
            if (!line.startsWith("move")) {
                continue
            }
            val instruction = getInstruction(line)
            val howManyToMove = instruction.first
            val fromStack = instruction.second
            val toStack = instruction.third
            for (i in 0 until howManyToMove) {
                val takeThis = stacks[fromStack][0]
                stacks[fromStack].removeFirstOrNull()
                stacks[toStack].add(0, takeThis)
            }
        }
        var ret = ""
        for (stack in stacks) {
            ret += stack[0]
        }
        return ret
    }

    fun part2(input: List<String>): String {
        val stacks = createStacks(input)
        for (line in input) {
            if (!line.startsWith("move")) {
                continue
            }
            val instruction = getInstruction(line)
            val howManyToMove = instruction.first
            val fromStack = instruction.second
            val toStack = instruction.third
            val takeThis = stacks[fromStack].take(howManyToMove)
            stacks[toStack].addAll(0, takeThis)
            for (i in 0 until howManyToMove) {
                stacks[fromStack].removeFirstOrNull()
            }
        }
        var ret = ""
        for (stack in stacks) {
            ret += stack[0]
        }
        return ret
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("input/Day05")
    println(part1(input))
    println(part2(input))
}
