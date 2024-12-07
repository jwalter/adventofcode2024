import org.junit.jupiter.api.Test
import kotlin.math.abs

class Day02 {

    @Test
    fun `part 1 example`() {
        assert(getExample().map { isSafe(it)}.count { it } == 2)
    }

    fun isSafe(line: List<Int>): Boolean {
        return (line.sorted() == line || line.sortedDescending() == line) && levelChangesOk(line.sorted())
    }

    private fun levelChangesOk(line: List<Int>): Boolean {
        for (i in 0 until line.size - 1) {
            if (abs(line[i] - line[i + 1]) > 3 || abs(line[i] - line[i + 1]) < 1) {
                return false
            }
        }
        return true
    }

    @Test
    fun `part 1`() {
        assert(getInput().map { isSafe(it)}.count { it } == 236)
    }

    @Test
    fun `part 2 example`() {
        assert(getExample().map { isAnyMutationSafe(it)}.count { it } == 4)
    }

    private fun isAnyMutationSafe(line: List<Int>): Boolean {
        if (isSafe(line)) {
            return true
        }
        for (i in 0 until line.size) {
            val mutated = line.toMutableList()
            mutated.removeAt(i)
            if (isSafe(mutated)) {
                return true
            }
        }
        return false
    }

    @Test
    fun `part 2`() {
        assert(getInput().map { isAnyMutationSafe(it)}.count { it } == 308)
    }

    private fun getExample(): List<List<Int>> {
        val example = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
        return example.lines().map { it.trim().split(" ").map { it.toInt() } }
    }
    private fun getInput(): List<List<Int>> {
        val input = inputLines("02")
        val values = input.map { it.trim().split(" ").map { it.toInt() } }
        return values
    }
}