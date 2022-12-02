fun main() {

    fun toNum(alpha: String): Int {
        val ret = when (alpha) {
            "A", "X" -> 1
            "B", "Y" -> 2
            else -> 3
        }

        return ret
    }

    fun getDecidedOutcomeChar(alpha: String): String {
        val ret = when (alpha) {
            "X" -> "loss"
            "Z" -> "win"
            else -> "draw"
        }

        return ret
    }

    fun computeOutcomeScore(result: Pair<Int,Int>): Int {
        val (a,b) = result
        return when (a-b) {
            -1, 2 -> {
                6
            }

            1, -2 -> {
                0
            }

            else -> 3
        }
    }

    fun verifyOutcome(desiredOutcome: Pair<Int,String>): Boolean {
        val (a,b) = desiredOutcome
        if (a == 6 && b == "win") {
            return true
        }
        if (a == 3 && b == "draw") {
            return true
        }
        if (a == 0 && b == "loss") {
            return true
        }
        return false
    }


    fun part1(input: List<String>): Int {
        var totalScore = 0
        for (line in input) {
            var score = 0
            val values = line.split(" ");
            val left = toNum(values[0])
            val right = toNum(values[1])
            val outcome = computeOutcomeScore(Pair(left,right))
            score = right + outcome
            totalScore += score
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        for (line in input) {
            var score = 0
            val values = line.split(" ");
            val left = toNum(values[0])
            var right = 0
            val desiredOutcome = getDecidedOutcomeChar(values[1])
            for (char in listOf("X","Y","Z")) {
                val testRight = toNum(char)
                val testOutcome = computeOutcomeScore(Pair(left,testRight))
                if (verifyOutcome(Pair(testOutcome, desiredOutcome))) {
                    right = toNum(char)
                }
            }

            val outcome = computeOutcomeScore(Pair(left,right))
            score = right + outcome
            totalScore += score
        }
        return totalScore
    }


    

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("input/Day02")
    println(part1(input))
    println(part2(input))
}
