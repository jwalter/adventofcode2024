import org.junit.jupiter.api.Test

class Day04 {

    @Test
    fun `part 1 example`() {
        assert(xmas(getExample()) == 18)
    }

    @Test
    fun `part 1`() {
        assert(xmas(getInput()) == 2464)
    }

    @Test
    fun `part 2 example`() {
        assert(xmas2(getExample()) == 9)
    }

    @Test
    fun `part 2`() {
        assert(xmas2(getInput()) == 1982)
    }

    fun xmas(input: List<String>): Int {
        var count = 0
        input.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, column ->
                if (column == 'X') {
                    if (nextThree(columnIndex, rowIndex, 0, 1, input) == "MAS") {
                        println("Found D at $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, 0, -1, input) == "MAS") {
                        println("Found U at $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, 1, 0, input) == "MAS") {
                        println("Found R at $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, -1, 0, input) == "MAS") {
                        println("Found L at $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, 1, 1, input) == "MAS") {
                        println("Found RD $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, 1, -1, input) == "MAS") {
                        println("Found RU $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, -1, 1, input) == "MAS") {
                        println("Found LD $columnIndex, $rowIndex")
                        count++
                    }
                    if (nextThree(columnIndex, rowIndex, -1, -1, input) == "MAS") {
                        println("Found LU $columnIndex, $rowIndex")
                        count++
                    }
                }
            }
        }
        return count
    }

    fun xmas2(input: List<String>): Int {
        var count = 0
        input.forEachIndexed { rowIndex, row ->
            if (rowIndex > 0 && rowIndex < input.size - 1) {
                row.forEachIndexed { columnIndex, column ->
                    if (columnIndex > 0 && columnIndex < row.length - 1) {

                        if (column == 'A') {
                            val first = nextThree(columnIndex - 2, rowIndex - 2, 1, 1, input)
                            val second = nextThree(columnIndex - 2, rowIndex + 2, 1, -1, input)
                            if (first.isMas() &&  second.isMas()) {
                                println("Found XMAS at $columnIndex, $rowIndex")
                                count++
                            }
                        }
                    }
                }
            }
        }
        return count
    }

    private fun nextThree(c: Int, r: Int, x: Int, y: Int, input: List<String>): String {
        if (c + x * 3 >= input.first().length || c + x * 3 < 0) return ""
        if (r + y * 3 >= input.size || r + y * 3 < 0) return ""
        return (1..3).joinToString("") {
            input[r + y * it][c + x * it].toString()
        }

    }

    private fun getExample(): List<String> {
        return """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent().lines()
    }

    private fun getExample2(): List<String> {
        return listOf(")")
    }

    private fun getInput(): List<String> {
        return inputLines("04")
    }
}

private fun String.isMas() = this == "MAS" || this == "SAM"
