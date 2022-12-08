fun main() {


    fun scenicScore(input: List<String>): Int {
        val scenicScores = mutableListOf<Int>()
        val twoDArray = input.map { x -> x.toCharArray().map { y -> y.digitToInt() } }
        for ((listcounter, list) in twoDArray.withIndex()) {
            if (listcounter > 0 && listcounter < twoDArray.size - 1) {
                for ((numbercounter, number) in list.withIndex()) {
                    if (numbercounter > 0 && numbercounter < list.size - 1) {
                        val left = list.slice(0 until numbercounter)
                        val right = list.slice(numbercounter + 1 until list.size)
                        val up = twoDArray.slice(0 until listcounter).map { x -> x[numbercounter] }
                        val down = twoDArray.slice(listcounter + 1 until twoDArray.size).map { x -> x[numbercounter] }
                        var leftScore = 0
                        var rightScore = 0
                        var upScore = 0
                        var downScore = 0
                        for (num in left.reversed()) {
                            if (num >= number) {
                                leftScore += 1
                                break
                            } else {
                                leftScore += 1
                            }
                        }
                        for (num in right) {
                            if (num >= number) {
                                rightScore += 1
                                break
                            } else {
                                rightScore += 1
                            }
                        }
                        for (num in up.reversed()) {
                            if (num == number) {
                                upScore += 1
                                break
                            } else {
                                upScore += 1
                            }
                        }
                        for (num in down) {
                            if (num >= number) {
                                downScore += 1
                                break
                            } else {
                                downScore += 1
                            }
                        }
                        scenicScores.add(leftScore * rightScore * upScore * downScore)
                    }
                }
            }
        }
        return scenicScores.max()
    }

    fun isVisible(input: List<String>): Int {
        var visibleCounter = 0
        val twoDArray = input.map { x -> x.toCharArray().map { y -> y.digitToInt() } }
        for ((listcounter, list) in twoDArray.withIndex()) {
            if (listcounter > 0 && listcounter < twoDArray.size - 1) {
                for ((numbercounter, number) in list.withIndex()) {
                    if (numbercounter > 0 && numbercounter < list.size - 1) {
                        val left = list.slice(0 until numbercounter)
                        val right = list.slice(numbercounter + 1 until list.size)
                        val up = twoDArray.slice(0 until listcounter).map { x -> x[numbercounter] }
                        val down = twoDArray.slice(listcounter + 1 until twoDArray.size).map { x -> x[numbercounter] }
                        if (left.all { x -> x < number } || right.all { x -> x < number } || up.all { x -> x < number } || down.all { x -> x < number }) {
                            visibleCounter += 1
                        }
                    }
                }
            }
        }
        return visibleCounter
    }

    fun getOuterBounds(input: List<String>): Int {
        return input.size * 2 + (input[0].length - 2) * 2
    }


    fun part1(input: List<String>): Int {
        val outerBounds = getOuterBounds(input)
        val visibleTrees = isVisible(input)
        return outerBounds + visibleTrees
    }

    fun part2(input: List<String>): Int {
        return scenicScore(input)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("input/Day08")
    println(part1(input))
    println(part2(input))
}
