import java.io.File

/**
 * @author joe
 */
fun main() {
    val file = File(ClassLoader.getSystemResource("day1.txt").toURI())
    file.forEachLine { println(it) }
}