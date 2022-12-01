fun main() {

    fun getBiggestKcal(input: List<String>): ArrayList<Int> {
        val summedUpValues = arrayListOf<Int>();
        var summedUpValue = 0;
        for (it in input) {
            when (it) {
                "" -> {
                    summedUpValues.add(summedUpValue)
                    summedUpValue = 0;
                }
                input[input.lastIndex] -> {
                    summedUpValue += it.toInt()
                    summedUpValues.add(summedUpValue)
                }
                else -> {
                    summedUpValue += it.toInt()
                }
            }
        }

        return summedUpValues
    }

    fun getBiggestKcalMax(input: ArrayList<Int>): Int {
        return input.max()
    }

    fun getBiggestKcalSumUpTopThree(input: ArrayList<Int>): Int {
        val sorted =  input.sortedDescending()
        return sorted[0]+sorted[1]+sorted[2]
    }


    fun part1(input: List<String>): Int {
        return getBiggestKcalMax(getBiggestKcal(input))
    }

    fun part2(input: List<String>): Int {
        return getBiggestKcalSumUpTopThree(getBiggestKcal(input))
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("input/Day01")
    println(part1(input))
    println(part2(input))
}
