fun main() {

    fun runInstruction(cycleRegister: Triple<String, Int, Int>): Triple<String, Int, Int> {
        var input = cycleRegister.first
        return if (input == "noop") {
            cycleRegister.copy("", cycleRegister.second + 1, cycleRegister.third)
        } else if (input.startsWith("addx ")) {
            input = input.replace("addx", "add")
            cycleRegister.copy(input, cycleRegister.second + 1, cycleRegister.third)
        } else {
            val toAddToRegister = input.split(" ")[1].toInt()
            cycleRegister.copy("", cycleRegister.second + 1, cycleRegister.third + toAddToRegister)
        }
    }

    fun part1(input: List<String>): Int {
        var cycle = 0
        var register = 1
        val solution = mutableListOf<Int>()
        for (line in input) {
            var newInst: String
            var inst = Triple(line, cycle, register)
            var ret = runInstruction(inst)
            newInst = ret.first

            if (newInst.startsWith("add ")) {
                cycle = ret.second
                register = ret.third
                if ((cycle - 20).mod(40) == 0) {
                    solution.add(register * cycle)
                }
                inst = Triple(newInst, cycle, register)
                ret = runInstruction(inst)
            }
            cycle = ret.second

            if ((cycle - 20).mod(40) == 0) {
                solution.add(register * cycle)
            }
            register = ret.third
        }
        return solution.sum()
    }

    fun part2(input: List<String>): Int {
        var cycle = 0
        var register = 1
        var solution = CharArray(40) { '.' }
        val solutions = mutableListOf<String>()
        var spritePosition = listOf(0, 1, 2)
        for (line in input) {
            if (spritePosition.contains(cycle)) {
                solution[cycle] = '#'
            }
            var newInst: String
            var inst = Triple(line, cycle, register)
            var ret = runInstruction(inst)
            newInst = ret.first
            if (newInst.startsWith("add ")) {
                cycle = ret.second
                register = ret.third
                spritePosition = listOf(register - 1, register, register + 1)
                if (cycle.mod(40) == 0) {
                    solutions.add(String(solution))
                    solution = CharArray(40) { '.' }
                    cycle = 0
                }
                if (spritePosition.contains(cycle)) {
                    solution[cycle] = '#'
                }

                inst = Triple(newInst, cycle, register)
                ret = runInstruction(inst)
            }
            cycle = ret.second
            register = ret.third
            spritePosition = listOf(register - 1, register, register + 1)
            if (cycle.mod(40) == 0) {
                solutions.add(String(solution))
                solution = CharArray(40) { '.' }
                cycle = 0
            }
        }
        for (solution in solutions) {
            System.out.println(solution)
        }
        return solution.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day10_test")
    check(part1(testInput) == 13140)
    //check(part2(testInput) == 1)

    val input = readInput("input/Day10")
    println(part1(input))
    println(part2(input))
}
