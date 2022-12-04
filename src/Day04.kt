fun main() {

    fun getUpperAndLowerBounds(input: String): Pair<Int, Int> {
        //2-4
        val parts = input.split("-")
        val startIndex = parts[0].toInt()
        val endIndex = parts[1].toInt()

        return Pair(startIndex, endIndex)
    }

    fun intersection(input: String): Boolean {
        val intersections = arrayListOf<List<Int>>()
        val elements = input.split(",")
        //2-4, 3-5
        for (element in elements) {
            val parts = element.split("-")
            val startIndex = parts[0].toInt()
            val endIndex = parts[1].toInt()
            val toBeIntersected = arrayListOf<Int>()
            for (i in startIndex..endIndex) {
                toBeIntersected.add(i)
            }
            intersections.add(toBeIntersected)
        }
        val numbers = intersections[0].intersect(intersections[1].toSet())

        return numbers.isNotEmpty()
    }

    fun containsEachOther(input: List<String>): Int {
        var howManyPairs = 0
        for (it in input) {
            val parts = it.split(",")
            val firstPart = getUpperAndLowerBounds(parts[0])
            val secondPart = getUpperAndLowerBounds(parts[1])
            if ((firstPart.first >= secondPart.first && firstPart.second <= secondPart.second) ||
                secondPart.first >= firstPart.first && secondPart.second <= firstPart.second
            ) {
                howManyPairs += 1
                continue
            }
        }
        return howManyPairs
    }

    fun overlapEachOther(input: List<String>): Int {
        var howManyIntersections = 0
        for (it in input) {
            if (intersection(it)) {
                howManyIntersections += 1
            }
        }
        return howManyIntersections

    }

    fun part1(input: List<String>): Int {
        return containsEachOther(input)
    }

    fun part2(input: List<String>): Int {
        return overlapEachOther(input)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("input/Day04")
    println(part1(input))
    println(part2(input))
}