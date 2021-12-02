import java.io.File

/**
 * @author joe
 */
class Day1 {
    fun partOne(): Int {
        val inputs = File(ClassLoader.getSystemResource("day1.txt").toURI()).readLines()
        var count = 0
        var cur = 1
        while (true) {
            val i = inputs[cur].toInt()
            val p = inputs[cur - 1].toInt()
            if (i > p) {
                count++
            }
            cur++
            if (cur > inputs.size - 1) break
        }
        return count
    }

    fun partTwo(): Int {
        val inputs = File(ClassLoader.getSystemResource("day1.txt").toURI()).readLines()
        var count = 0
        var cur = 1
        while (true) {
            val curVal = inputs[cur].toInt() + inputs[cur + 1].toInt() + inputs[cur + 2].toInt()
            val preVal = inputs[cur - 1].toInt() + inputs[cur].toInt() + inputs[cur + 1].toInt()
            if (curVal > preVal) {
                count++
            }
            cur++
            if (inputs.size - cur < 3) break
        }
        return count
    }
}