import org.junit.jupiter.api.Test

class Day06 {

    @Test
    fun `part 1 example`() {
        assert(solveA(getExample()) == 41)
    }

    @Test
    fun `part 1`() {
        assert(solveA(getInput()) == 5153)
    }

    @Test
    fun `part 2 example`() {
        assert(solveB(getExample()) == 6)
    }

    @Test
    fun `part 2`() {
        assert(solveB(getInput()) == 6)
    }

    private fun solveB(input: List<String>): Int {
        var count = 0
        input.forEachIndexed { rowNo, row ->
            row.forEachIndexed { columnNo, column ->
                if (input[rowNo][columnNo] == '.') {
                    val mutated = input.toMutableList()
                    mutated[rowNo] = mutated[rowNo].take(columnNo) + "O" + mutated[rowNo].drop(columnNo + 1)
                    val res = solveA(mutated)
                    if (res >= 10000) {
                        //println(mutated.joinToString("\n"))
                        //println()
                        count++
                    }
                }
            }
        }
        return count
    }

    private fun solveA(input: List<String>): Int {
        var row = input.indexOfFirst { row -> row.contains("^") }
        var column = input[row].indexOf("^")
        var dir = 0 to -1
        val visited = mutableSetOf<Pair<Int, Int>>()
        var moves = 0
        val turnsTaken = mutableSetOf<Pair<Int, Int>>()
        while (row >= 0 && column >= 0 && row < input.size && column < input[row].length && moves++ <= 10000) {
            val next = input.getOrNull(row + dir.second)?.getOrNull(column + dir.first)
            val prevDir = dir
            if (next == '#' || next == 'O') {
                dir = if (dir == 0 to -1) 1 to 0
                else if (dir == 1 to 0) 0 to 1
                else if (dir == 0 to 1) -1 to 0
                else 0 to -1
//                if (prevDir != dir) {
//                    if (turnsTaken.contains(row to column)) {
//                        moves = 10001
//                    }
//                    turnsTaken.add(row to column)
//                }
            } else {
                visited.add(row to column)
                row += dir.second
                column += dir.first
            }
        }
        return if (moves <= 10000) visited.size else 10000
    }

    private fun getExample(): List<String> {
        return """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".trimMargin().lines()

    }

    private fun getInput(): List<String> {
        return inputLines("06")
    }
}