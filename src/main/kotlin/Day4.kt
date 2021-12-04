import java.io.File

class Day4 {
    val numbers = arrayListOf(
        15,
        61,
        32,
        33,
        87,
        17,
        56,
        73,
        27,
        83,
        0,
        18,
        43,
        8,
        86,
        37,
        40,
        6,
        93,
        25,
        14,
        68,
        64,
        57,
        39,
        46,
        55,
        13,
        21,
        72,
        51,
        81,
        78,
        79,
        52,
        65,
        36,
        92,
        97,
        28,
        9,
        24,
        22,
        69,
        70,
        42,
        3,
        4,
        63,
        50,
        91,
        16,
        41,
        94,
        77,
        85,
        49,
        12,
        76,
        67,
        11,
        62,
        99,
        54,
        95,
        1,
        74,
        34,
        88,
        89,
        82,
        48,
        84,
        98,
        58,
        44,
        5,
        53,
        7,
        19,
        29,
        30,
        35,
        26,
        31,
        10,
        60,
        59,
        80,
        71,
        45,
        38,
        20,
        66,
        47,
        2,
        23,
        96,
        90,
        75

    )

    val testNumbers =
        arrayOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)

    fun partOne(): Unit {
        val map = convertToArray("day4.txt")
        var pair: Pair<Int, Array<Array<Info?>>>? = null
        for ((index, testNumber) in numbers.withIndex()) {
            map.values.forEach { value ->
                for (i in 0..4) {
                    for (j in 0..4) {
                        value[i][j]?.let {
                            if (it.data == testNumber) {
                                it.marked = true
                            }
                        }
                    }
                }

            }
            if (index >= 4) {
                map.entries.forEach {
                    val countMap = hashMapOf<String, Int>()
                    for (x in 0..4) {
                        for (y in 0..4) {
                            it.value[x][y]?.let { info ->
                                if (info.marked) {
                                    var xValue = countMap["x:$x"] ?: 0
                                    var yValue = countMap["y:$y"] ?: 0
                                    countMap["x:$x"] = ++xValue
                                    countMap["y:$y"] = ++yValue
                                }
                            }
                        }
                    }
                    val any = countMap.values.any { value ->
                        value >= 5
                    }
                    if (any) {
                        pair = Pair(testNumber, it.value)
                    }
                }
            }
        }
        var res = 0
        pair?.let {
            for (x in 0..4) {
                for (y in 0..4) {
                    it.second[x][y]?.let { info ->
                        if (!info.marked) {
                            res += info.data
                        }
                    }
                }
            }
            println(it.first * res)
        }
    }

    fun partTwo(): Unit {
        val map = convertToArray("day4.txt")
        var pair: Pair<Int, Array<Array<Info?>>>? = null
        for ((index, testNumber) in numbers.withIndex()) {
            map.values.forEach { value ->
                for (i in 0..4) {
                    for (j in 0..4) {
                        value[i][j]?.let {
                            if (it.data == testNumber) {
                                it.marked = true
                            }
                        }
                    }
                }

            }
            if (index >= 4) {
                val shouldDeletedKey = arrayListOf<Int>()
                map.entries.forEach {
                    val countMap = hashMapOf<String, Int>()
                    for (x in 0..4) {
                        for (y in 0..4) {
                            it.value[x][y]?.let { info ->
                                if (info.marked) {
                                    var xValue = countMap["x:$x"] ?: 0
                                    var yValue = countMap["y:$y"] ?: 0
                                    countMap["x:$x"] = ++xValue
                                    countMap["y:$y"] = ++yValue
                                }
                            }
                        }
                    }
                    val any = countMap.values.any { value ->
                        value >= 5
                    }
                    if (any) {
                        shouldDeletedKey.add(it.key)
                    }
                }
                shouldDeletedKey.forEach {
                    if (map.size == 1) {
                        pair = Pair(testNumber, map.values.first())
                    }
                    map.remove(it)
                }

            }
        }
        var res = 0
        pair?.let {
            for (x in 0..4) {
                for (y in 0..4) {
                    it.second[x][y]?.let { info ->
                        if (!info.marked) {
                            res += info.data
                        }
                    }
                }
            }
            println(it.first * res)
        }
    }

    fun convertToArray(fileName: String): HashMap<Int, Array<Array<Info?>>> {
        val inputs = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()
        val boards = hashMapOf<Int, Array<Array<Info?>>>()
        var boardNumber = 1
        var x = 0
        for (cur in inputs) {
            if (cur == "") {
                boardNumber++
                x = 0
                continue
            }
            val datas = boards[boardNumber] ?: Array(5) { arrayOfNulls(5) }
            for ((y, v) in cur.trim().split(Regex("\\s+")).withIndex()) {
                if (v == "") continue
                datas[x][y] = Info(v.toInt())
            }
            boards[boardNumber] = datas
            x++
        }
        return boards
    }

    data class Info(val data: Int, var marked: Boolean = false)
}