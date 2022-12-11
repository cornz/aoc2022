import java.math.BigInteger

data class Monkey(
    var id: Int, var items: MutableList<Int>, var operation: Pair<String, String>, var divisor: Int,
    var throwTo: Pair<Int, Int>, var numberOfInspections: Int
)

data class LongMonkey(
    var id: Int, var items: MutableList<BigInteger>, var operation: Pair<String, String>,
    var divisor: Int, var throwTo: Pair<Int, Int>, var numberOfInspections: BigInteger
)

fun main() {
    fun processMonkeys(input: List<String>): List<Monkey> {
        val monkeys = input.filter { x -> x.isNotEmpty() }.chunked(6)
        val processedMonkeys = mutableListOf<Monkey>()
        for (monkeyText in monkeys) {
            val id = monkeyText[0].filter { x -> x.isDigit() }.toInt()
            val items = monkeyText[1].split(": ")[1].split(", ").map { x -> x.toInt() }
            val operation = monkeyText[2].split("old ")[1].split(" ")
            val divisor = monkeyText[3].filter { x -> x.isDigit() }.toInt()
            val throwTo = Pair(
                monkeyText[4].filter { x -> x.isDigit() }.toInt(),
                monkeyText[5].filter { x -> x.isDigit() }.toInt()
            )
            val monkey = Monkey(id, items.toMutableList(), Pair(operation[0], operation[1]), divisor, throwTo, 0)
            processedMonkeys.add(monkey)
        }
        return processedMonkeys
    }

    fun processLongMonkeys(input: List<String>): List<LongMonkey> {
        val monkeys = input.filter { x -> x.isNotEmpty() }.chunked(6)
        val processedMonkeys = mutableListOf<LongMonkey>()
        for (monkeyText in monkeys) {
            val id = monkeyText[0].filter { x -> x.isDigit() }.toInt()
            val items = monkeyText[1].split(": ")[1].split(", ").map { x -> x.toBigInteger() }
            val operation = monkeyText[2].split("old ")[1].split(" ")
            val divisor = monkeyText[3].filter { x -> x.isDigit() }.toInt()
            val throwTo = Pair(
                monkeyText[4].filter { x -> x.isDigit() }.toInt(),
                monkeyText[5].filter { x -> x.isDigit() }.toInt()
            )
            val monkey = LongMonkey(
                id, items.toMutableList(), Pair(operation[0], operation[1]), divisor, throwTo,
                BigInteger.ZERO
            )
            processedMonkeys.add(monkey)
        }
        return processedMonkeys
    }


    fun processItem(item: Int, monkey: Monkey): Int {
        var worryLevel = item
        var actionActor = monkey.operation.second
        if (monkey.operation.second == "old") {
            actionActor = worryLevel.toString()
        }
        if (monkey.operation.first == "+") {
            worryLevel += actionActor.toInt()
        } else if (monkey.operation.first == "*") {
            worryLevel *= actionActor.toInt()
        }
        return worryLevel
    }

    fun processItem(item: BigInteger, monkey: LongMonkey): BigInteger {
        var worryLevel = item
        var actionActor = monkey.operation.second
        if (monkey.operation.second == "old") {
            actionActor = worryLevel.toString()
        }
        if (monkey.operation.first == "+") {
            worryLevel = worryLevel.plus(actionActor.toBigInteger())
        } else if (monkey.operation.first == "*") {
            worryLevel = worryLevel.multiply(actionActor.toBigInteger())
        }
        return worryLevel
    }

    fun floorWorryLevel(worryLevel: Int): Int {
        var worryLevel1 = worryLevel
        worryLevel1 = worryLevel1.floorDiv(3)
        return worryLevel1
    }

    fun throwItemToMonkey(worryLevel: Int, monkey: Monkey, monkeys: List<Monkey>) {
        if (worryLevel.mod(monkey.divisor) == 0) {
            monkeys[monkey.throwTo.first].items.add(worryLevel)
        } else {
            monkeys[monkey.throwTo.second].items.add(worryLevel)
        }
        monkey.numberOfInspections++
    }

    fun throwItemToMonkey(worryLevel: BigInteger, monkey: LongMonkey, monkeys: List<LongMonkey>) {
        if (worryLevel.mod(BigInteger.valueOf(monkey.divisor.toLong())).equals(BigInteger.ZERO)) {
            monkeys[monkey.throwTo.first].items.add(worryLevel)
        } else {
            monkeys[monkey.throwTo.second].items.add(worryLevel)
        }
        monkey.numberOfInspections++
    }

    fun monkeyBusiness(input: List<Monkey>): Int {
        return input.map { x -> x.numberOfInspections }.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }

    fun monkeyBusiness(input: List<LongMonkey>): BigInteger {
        return input.map { x -> x.numberOfInspections }.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }

    fun part1(input: List<String>): Int {
        val monkeys = processMonkeys(input)
        repeat(20) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    var worryLevel = processItem(item, monkey)
                    worryLevel = floorWorryLevel(worryLevel)
                    throwItemToMonkey(worryLevel, monkey, monkeys)
                }
                monkey.items.clear()

            }
        }
        return monkeyBusiness(monkeys)
    }

    fun part2(input: List<String>): BigInteger {

        val monkeys = processLongMonkeys(input)
        val lcm = monkeys.map { it.divisor }.reduce { acc, i -> acc * i }
        var i = 0
        repeat(10000) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    var worryLevel = processItem(item, monkey)
                    worryLevel = worryLevel.mod(BigInteger.valueOf(lcm.toLong()))
                    throwItemToMonkey(worryLevel, monkey, monkeys)
                }
                monkey.items.clear()

            }
            i++
            print(i)
            print("\r")
        }
        return monkeyBusiness(monkeys)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day11_test")
    check(part1(testInput) == 10605)
    check(part2(testInput).compareTo(BigInteger.valueOf(2713310158)) == 0)

    val input = readInput("input/Day11")
    println(part1(input))
    println(part2(input))
}
