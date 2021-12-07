import java.io.File
import kotlin.math.abs

/**
 * @author joe
 */
class Day7 {
    fun partOne(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day7.txt").toURI()).readText()
        val data = inputs.split(",").map { it.trim().toInt() }.sorted()
        var total = -1
        var target = -1
        for (i in data.indices) {
            if (i == 0) continue
            var tempVal = 0
            for (j in 0 until i) {
                tempVal += abs(data[i] - data[j])
            }
            if (total == -1) {
                total = tempVal
                target = data[i]
                continue
            }
            total += abs(target - data[i])
            if (tempVal <= total) {
                total = tempVal
                target = data[i]
            }
        }
        println(total)
    }

    fun partTwo(): Unit {

    }
}