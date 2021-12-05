import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day5 {

    fun partOne(): Unit {
        val data = getData("day5.txt")
        val positionCountMap = hashMapOf<Position, Int>()
        for (info in data) {
            info?.let {
                if (it.isXInLine()) {
                    var start: Int
                    var end: Int
                    if (info.start.y <= info.end.y) {
                        start = info.start.y
                        end = info.end.y
                    } else {
                        start = info.end.y
                        end = info.start.y
                    }
                    for (i in start..end) {
                        val key = Position(info.start.x, i)
                        var count = positionCountMap[key] ?: 0
                        positionCountMap[key] = ++count
                    }
                }
                if (it.isYInLine()) {
                    var start: Int
                    var end: Int
                    if (info.start.x <= info.end.x) {
                        start = info.start.x
                        end = info.end.x
                    } else {
                        start = info.end.x
                        end = info.start.x
                    }

                    for (i in start..end) {
                        val key = Position(i, info.start.y)
                        var count = positionCountMap[key] ?: 0
                        positionCountMap[key] = ++count
                    }
                }
            }
        }

        println(positionCountMap.values.count { it >= 2 })
    }

    fun partTwo(): Unit {
        val data = getData("day5.txt")
        val positionCountMap = hashMapOf<Position, Int>()
        for (info in data) {
            info?.let {
                if (it.isXInLine()) {
                    var start: Int
                    var end: Int
                    if (info.start.y <= info.end.y) {
                        start = info.start.y
                        end = info.end.y
                    } else {
                        start = info.end.y
                        end = info.start.y
                    }
                    for (i in start..end) {
                        val key = Position(info.start.x, i)
                        var count = positionCountMap[key] ?: 0
                        positionCountMap[key] = ++count
                    }
                    return@let
                }
                if (it.isYInLine()) {
                    var start: Int
                    var end: Int
                    if (info.start.x <= info.end.x) {
                        start = info.start.x
                        end = info.end.x
                    } else {
                        start = info.end.x
                        end = info.start.x
                    }

                    for (i in start..end) {
                        val key = Position(i, info.start.y)
                        var count = positionCountMap[key] ?: 0
                        positionCountMap[key] = ++count
                    }
                    return@let

                }
                if (it.is45Angle()) {
                    if (it.start.x >= it.end.x && it.start.y >= it.end.y) {
                        var x = it.start.x
                        var y = it.start.y
                        while (x >= it.end.x && y >= it.end.y) {
                            val key = Position(x, y)
                            var count = positionCountMap[key] ?: 0
                            positionCountMap[key] = ++count
                            x--
                            y--
                        }
                    } else if (it.start.x > it.end.x && it.start.y < it.end.y) {
                        var x = it.start.x
                        var y = it.start.y
                        while (x >= it.end.x && y <= it.end.y) {
                            val key = Position(x, y)
                            var count = positionCountMap[key] ?: 0
                            positionCountMap[key] = ++count
                            x--
                            y++
                        }
                    } else if (it.start.x < it.end.x && it.start.y > it.end.y) {
                        var x = it.start.x
                        var y = it.start.y
                        while (x <= it.end.x && y >= it.end.y) {
                            val key = Position(x, y)
                            var count = positionCountMap[key] ?: 0
                            positionCountMap[key] = ++count
                            x++
                            y--
                        }
                    } else if (it.start.x < it.end.x && it.start.y < it.end.y) {
                        var x = it.start.x
                        var y = it.start.y
                        while (x <= it.end.x && y <= it.end.x) {
                            val key = Position(x, y)
                            var count = positionCountMap[key] ?: 0
                            positionCountMap[key] = ++count
                            x++
                            y++
                        }
                    }
                    return@let

                }
            }
        }
        println(positionCountMap.values.count { it >= 2 })

//        val array = Array(10) { IntArray(10) }
//        positionCountMap.entries.forEach {
//            array[it.key.y][it.key.x] = it.value
//        }
//        println("""
//            ${array[0][0]},${array[0][1]},${array[0][2]},${array[0][3]},${array[0][4]},${array[0][5]},${array[0][6]},${array[0][7]},${array[0][8]},${array[0][9]}
//            ${array[1][0]},${array[1][1]},${array[1][2]},${array[1][3]},${array[1][4]},${array[1][5]},${array[1][6]},${array[1][7]},${array[1][8]},${array[1][9]}
//            ${array[2][0]},${array[2][1]},${array[2][2]},${array[2][3]},${array[2][4]},${array[2][5]},${array[2][6]},${array[2][7]},${array[2][8]},${array[2][9]}
//            ${array[3][0]},${array[3][1]},${array[3][2]},${array[3][3]},${array[3][4]},${array[3][5]},${array[3][6]},${array[3][7]},${array[3][8]},${array[3][9]}
//            ${array[4][0]},${array[4][1]},${array[4][2]},${array[4][3]},${array[4][4]},${array[4][5]},${array[4][6]},${array[4][7]},${array[4][8]},${array[4][9]}
//            ${array[5][0]},${array[5][1]},${array[5][2]},${array[5][3]},${array[5][4]},${array[5][5]},${array[5][6]},${array[5][7]},${array[5][8]},${array[5][9]}
//            ${array[6][0]},${array[6][1]},${array[6][2]},${array[6][3]},${array[6][4]},${array[6][5]},${array[6][6]},${array[6][7]},${array[6][8]},${array[6][9]}
//            ${array[7][0]},${array[7][1]},${array[7][2]},${array[7][3]},${array[7][4]},${array[7][5]},${array[7][6]},${array[7][7]},${array[7][8]},${array[7][9]}
//            ${array[8][0]},${array[8][1]},${array[8][2]},${array[8][3]},${array[8][4]},${array[8][5]},${array[8][6]},${array[8][7]},${array[8][8]},${array[8][9]}
//            ${array[9][0]},${array[9][1]},${array[9][2]},${array[9][3]},${array[9][4]},${array[9][5]},${array[9][6]},${array[9][7]},${array[9][8]},${array[9][9]}
//
//        """.trimIndent())
    }

    fun getData(fileName: String): Array<Info?> {
        val inputs = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()
        val data = arrayOfNulls<Info>(inputs.size)
        for ((i, cur) in inputs.withIndex()) {
            var start: Position? = null
            var end: Position? = null
            for ((index, value) in cur.trim().split("->").withIndex()) {
                val split = value.trim().split(",")
                if (index == 0) {
                    start = Position(split[0].toInt(), split[1].toInt())
                } else {
                    end = Position(split[0].toInt(), split[1].toInt())
                }
            }
            if (start != null && end != null) {
                data[i] = Info(start, end)
            }
        }
        return data
    }

    data class Info(val start: Position, val end: Position) {
        fun isXInLine(): Boolean {
            return start.x == end.x
        }

        fun isYInLine(): Boolean {
            return start.y == end.y
        }

        fun is45Angle(): Boolean {
            return abs(start.x - end.x) == abs(start.y - end.y)
        }
    }

    data class Position(val x: Int, val y: Int)
}