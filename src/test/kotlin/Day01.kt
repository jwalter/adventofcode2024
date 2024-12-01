import org.junit.jupiter.api.Test
import kotlin.math.abs

class Day01 {

    @Test
    fun `part 1 example`() {
        val left = listOf(3, 4, 2, 1, 3, 3)
        val right = listOf(4, 3, 5, 3, 9, 3)
        assert(sumOfDistances(left, right) == 11)
    }

    @Test
    fun `part 1`() {
        val (left, right) = getInput()
        assert(sumOfDistances(left, right) == 3246517)
    }

    @Test
    fun `part 2 example`() {
        val left = listOf(3, 4, 2, 1, 3, 3)
        val right = listOf(4, 3, 5, 3, 9, 3)
        assert(similarityScore(left, right) == 31)
    }

    @Test
    fun `part 2`() {
        val (left, right) = getInput()
        assert(similarityScore(left, right) == 29379307)
    }

    fun sumOfDistances(left: List<Int>, right: List<Int>): Int {
        return left.sorted().zip(right.sorted()).sumOf { (l, r) -> abs(l - r) }
    }

    private fun similarityScore(left: List<Int>, right: List<Int>): Int {
        return left.mapIndexed { index, leftValue ->
            right.count { it == leftValue } * leftValue
        }.sum()
    }

    private fun getInput(): Pair<List<Int>, List<Int>> {
        val input = inputLines("01")
        val values = input.map { s -> s.split(" ").map { it.trim()}.filter { !it.isEmpty() } }.map { (a, b) -> a.toInt() to b.toInt() }
        return values.unzip()
    }
}