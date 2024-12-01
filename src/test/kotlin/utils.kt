import java.io.File

fun inputLines(day: String): List<String> {

    return File("src/test/resources/input$day.txt").readLines()
}
