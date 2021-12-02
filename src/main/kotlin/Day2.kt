import java.io.File

/**
 * @author joe
 */
class Day2 {
    fun partOne(): Int {
        val inputs = File(ClassLoader.getSystemResource("day2.txt").toURI()).readLines()
        var forwardVal = 0
        var downVal = 0
        var upVal = 0
        for (cur in inputs) {
            if (cur.contains("forward")) {
                forwardVal += cur.split(" ")[1].toInt()
            } else if (cur.contains("down")) {
                downVal += cur.split(" ")[1].toInt()
            } else if (cur.contains("up")) {
                upVal += cur.split(" ")[1].toInt()
            }
        }
        return forwardVal * (downVal - upVal)
    }

    fun partTwo(): Int {
        val inputs = File(ClassLoader.getSystemResource("day2.txt").toURI()).readLines()
        var forwardVal = 0
        var aimVal = 0
        var depthVal = 0
        for (cur in inputs) {
            if (cur.contains("forward")) {
                val res = cur.split(" ")[1].toInt()
                forwardVal += res
                if (aimVal != 0) {
                    depthVal += res * aimVal
                }
            } else if (cur.contains("down")) {
                aimVal += cur.split(" ")[1].toInt()
            } else if (cur.contains("up")) {
                aimVal -= cur.split(" ")[1].toInt()
            }
        }

        return forwardVal * depthVal
    }
}