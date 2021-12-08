import java.io.File
import kotlin.math.abs

/**
 * @author joe
 */
class Day7 {
    fun partOne(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day7.txt").toURI()).readText()
        val data = inputs.split(",").map { it.trim().toInt() }.sorted()
        var min = -1
        for (i in data.indices) {
            var temp = 0
            for (j in data.indices) {
                if (i == j) continue
                temp += abs(data[i] - data[j])
            }
            if (min == -1) {
                min = temp
                continue
            }
            min = if (temp < min) temp else min
        }
        println(min)
    }

    fun partTwo(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day7.txt").toURI()).readText()
        val data = inputs.split(",").map { it.trim().toInt() }
        val max = data.maxOf { it }
        var min = -1
        for (i in 0..max) {
            var temp = 0
            for (j in data.indices) {
                val abs = abs(i - data[j])
                val res = ((abs * (abs + 1)) / 2)
                temp += res
            }
            if (min == -1) {
                min = temp
                continue
            }
            min = if (temp < min) temp else min
        }
        println(min)
    }

}