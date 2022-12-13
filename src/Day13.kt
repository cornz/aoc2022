import kotlin.math.min


fun main() {
    data class Index(var index: Int = 0)

    fun processList(input: String, index: Index): List<Any> {
        val list = mutableListOf<Any>()

        while (index.index < input.count()) {
            val char = input[index.index]
            index.index++
            when (char) {
                '[' -> list.add(processList(input, index))
                ',' -> continue
                ']' -> return list
                else -> {
                    val startIndex = index.index - 1
                    while (input[index.index].isDigit()) {
                        index.index++
                    }
                    list.add(input.subSequence(startIndex, index.index).toString().toInt())
                }
            }
        }

        return list
    }

    fun processPair(input: List<String>): Pair<List<Any>, List<Any>> {
        return Pair(processList(input[0], Index()), processList(input[1], Index()))
    }

    fun processPairs(left: List<Any>, right: List<Any>): Boolean? {
        for (i in 0 until min(left.size, right.size)) {
            if (left[i] is Int && right[i] is Int) {
                val leftInt = left[i] as Int
                val rightInt = right[i] as Int

                if (leftInt == rightInt) continue
                if (leftInt < rightInt) return true
                if (leftInt > rightInt) return false
            } else if (left[i] is List<*> && right[i] is List<*>) {
                val arrayResult = processPairs(left[i] as List<Any>, right[i] as List<Any>)
                if (arrayResult != null) return arrayResult
            } else if (left[i] is Int) {
                val arrayResult = processPairs(listOf(left[i]), right[i] as List<Any>)
                if (arrayResult != null) return arrayResult
            } else if (right[i] is Int) {
                val arrayResult = processPairs(left[i] as List<Any>, listOf(right[i]))
                if (arrayResult != null) return arrayResult
            }
        }

        if (left.size < right.size) {
            return true
        } else if (left.size > right.size) {
            return false
        }

        return null
    }

    fun isPairInRightOrder(pair: Pair<List<Any>, List<Any>>): Boolean {
        return processPairs(pair.first, pair.second)!!
    }


    fun part1(input: List<String>): Int {
        val res = mutableListOf<Boolean>()
        val inputs = input.filter { x -> x.isNotEmpty() }.chunked(2)
        val pairs = inputs.map { processPair(it) }
        for (pair in pairs) {
            res.add(isPairInRightOrder(pair))
        }
        var count = 0
        for ((index, result) in res.withIndex()) {
            if (result) {
                count += index + 1
            }
        }
        return count
    }

    val isALlInRightOrder = Comparator<List<Any>> { a, b ->
        val result = processPairs(a, b)
        if (result == null) {
            0
        } else if (result) {
            -1
        } else {
            1
        }
    }

    fun part2(input: List<String>): Int {
        val holder = mutableListOf<List<Any>>()
        val signals = input.filter { x -> x.isNotEmpty() }
        for (signal in signals) {
            val processSignal = processList(signal, Index())
            holder.add(processSignal)
        }
        holder.add(listOf(listOf(2)))
        holder.add(listOf(listOf(6)))

        val sortedSignals = holder.sortedWith(isALlInRightOrder)

        val res = sortedSignals.withIndex().filter { x -> x.value !is ArrayList<*> }


        return res.map { x -> x.index + 1 }.reduce { acc, i -> acc * i }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day13_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 140)

    val input = readInput("input/Day13")
    println(part1(input))
    println(part2(input))
}
