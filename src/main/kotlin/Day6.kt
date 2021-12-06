import java.io.File

/**
 * @author joe
 */
class Day6 {

    fun partOne(): Unit {
        val inputs = File(ClassLoader.getSystemResource("test.txt").toURI()).readText()
        val deque = ArrayDeque<Int>()
        inputs.split(",").forEach { deque.addLast(it.trim().toInt()) }
        var day = 1
        while (day <= 80) {
            var size = deque.size
            var count = 0
            for (i in 1..size) {
                var value = deque.removeFirst()
                if (--value < 0) {
                    value = 6
                    ++count
                }
                deque.addLast(value)
            }
            for (i in 1..count) {
                deque.addLast(8)
            }

            println("${day++}:${deque.size}")
        }
        println(deque.size)
    }

    fun partTwo(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day6.txt").toURI()).readText()
        val array = Array(9) { 0L }
        inputs.split(",").forEach {
            array[it.trim().toInt()] += 1L
        }
        var count = 0L
        for (day in 1..256) {
            for ((index, value) in array.withIndex()) {
                var temp = index
                if (--temp < 0) {
                    count = value
                } else {
                    array[temp] += value
                }
                array[index] = 0
            }
            array[6] += count
            array[8] += count
            count = 0
        }
        println(array.sum())
    }

}