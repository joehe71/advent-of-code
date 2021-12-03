import java.io.File

/**
 * @author joe
 */
class Day3 {
    fun partOne(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day3.txt").toURI()).readLines()
        val array = arrayOfNulls<Int>(inputs[0].length)
        for (cur in inputs) {
            for ((i, v) in cur.split("").withIndex()) {
                if (v == "") continue
                if (v == "1") {
                    val v = array[i - 1] ?: 0
                    array[i - 1] = v + 1
                }
            }
        }
        var gamma = ""
        var epsilon = ""
        for (cur in array) {
            val v = cur ?: 0
            if (v > inputs.size - v) {
                gamma += 1
                epsilon += 0
            } else {
                gamma += 0
                epsilon += 1
            }
        }
        println(gamma)//189
        println(epsilon)//3906
    }

    fun partTwo(): Unit {
        val inputs = File(ClassLoader.getSystemResource("day3.txt").toURI()).readLines()
        val oxygen = oxygenHelper(inputs, 0)
        val co2 = co2Helper(inputs, 0)
        println(oxygen[0])//1071
        println(co2[0])
    }

    fun oxygenHelper(array: List<String>, index: Int): List<String> {
        if (array.size == 1) return array
        val groupBy = array.groupBy { it[index] }
        val _1bitList = groupBy['1'] ?: listOf()
        val _0bitList = groupBy['0'] ?: listOf()
        val newIndex = index + 1
        if (_1bitList.size > _0bitList.size) {
            return oxygenHelper(_1bitList, newIndex)
        } else if (_1bitList.size < _0bitList.size) {
            return oxygenHelper(_0bitList, newIndex)
        } else {
            return oxygenHelper(_1bitList, newIndex)
        }
    }

    fun co2Helper(array: List<String>, index: Int): List<String> {
        if (array.size == 1) return array
        val groupBy = array.groupBy { it[index] }
        val _1bitList = groupBy['1'] ?: listOf()
        val _0bitList = groupBy['0'] ?: listOf()
        val newIndex = index + 1
        if (_1bitList.size > _0bitList.size) {
            return co2Helper(_0bitList, newIndex)
        } else if (_1bitList.size < _0bitList.size) {
            return co2Helper(_1bitList, newIndex)
        } else {
            return co2Helper(_0bitList, newIndex)
        }
    }
}