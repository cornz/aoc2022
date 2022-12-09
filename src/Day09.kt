data class Point(var x: Int, var y: Int)


fun main() {

    fun move(
        instruction: String,
        headAndTail: Triple<Point, Point, MutableList<Point>>
    ): Triple<Point, Point, MutableList<Point>> {
        val parts = instruction.split(" ")
        val guidance = parts[0]
        val run = parts[1].toInt()
        val head = headAndTail.first
        val tail = headAndTail.second
        val points = headAndTail.third
        for (i in 0 until run) {
            when (guidance) {
                "R" -> {
                    head.x++
                }

                "L" -> {
                    head.x--
                }

                "U" -> {
                    head.y++
                }

                else -> {
                    head.y--
                }
            }
            if (kotlin.math.abs(head.x - tail.x) < 2 && kotlin.math.abs(head.y - tail.y) < 2) {
                continue
            }
            if (head.x > tail.x && head.y == tail.y) {
                tail.x++
                points.add(tail.copy())
            } else if (tail.x > head.x && head.y == tail.y) {
                tail.x--
                points.add(tail.copy())
            } else if (tail.x == head.x && head.y > tail.y) {
                tail.y++
                points.add(tail.copy())
            } else if (tail.x == head.x && head.y < tail.y) {
                tail.y--
                points.add(tail.copy())
            } else if (head.x > tail.x && head.y > tail.y) {
                tail.x++
                tail.y++
                points.add(tail.copy())
            } else if (head.x < tail.x && head.y > tail.y) {
                tail.x--
                tail.y++
                points.add(tail.copy())
            } else if (head.x > tail.x && head.y < tail.y) {
                tail.x++
                tail.y--
                points.add(tail.copy())
            } else if (head.x < tail.x && head.y < tail.y) {
                tail.x--
                tail.y--
                points.add(tail.copy())
            }
        }
        return Triple(head, tail, points)
    }


    fun part1(input: List<String>): Int {
        val head = Point(0, 0)
        val tail = Point(0, 0)
        val points = mutableListOf(Point(0, 0))
        var res = Triple(head, tail, points)
        for (line in input) {
            res = move(line, res)
        }
        return res.third.toSet().size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day09_test")
    check(part1(testInput) == 13)
    //check(part2(testInput) == 1)

    val input = readInput("input/Day09")
    println(part1(input))
    println(part2(input))
}
