import org.junit.jupiter.api.Test
import kotlin.collections.map

class Day07 {

    @Test
    fun `part 1 example`() {
        assert(solveA(getExample()) == 3749L)
    }

    @Test
    fun `part 1`() {
        assert(solveA(getInput()) == 7579994664753L)
    }

    @Test
    fun `part 2 example`() {
        assert(solveB(getExample()) == 11387L)
    }

    @Test
    fun `part 2`() {
        assert(solveB(getInput()) == 438027111276610L)
    }

    fun solveA(input: List<List<Long>>): Long {
        return input.filter { isSolvable(it) }.sumOf { it.first() }
    }

    private fun isSolvable(values: List<Long>): Boolean {
        val wanted = values.first()
        val values = values.drop(1).reversed()
        val combinations = combinations(values)
        return combinations.any { it == wanted }
    }

    private fun combinations(values: List<Long>): List<Long> {
        if (values.size == 1) {
            return values
        }
        val head = values.first()
        val tail = values.drop(1)

        val tailCombos = combinations(tail)
        return tailCombos.map { it + head } + tailCombos.map { it * head }
    }

    fun solveB(input: List<List<Long>>): Long {
        return input.filter { isSolvableB(it) }.sumOf { it.first() }
    }

    private fun isSolvableB(values: List<Long>): Boolean {
        val wanted = values.first()
        val values = values.drop(1).reversed()
        val combinations = combinationsB(values)
        return combinations.any { it == wanted }
    }

    private fun combinationsB(values: List<Long>): List<Long> {
        if (values.size == 1) {
            return values
        }
        val head = values.first()
        val tail = values.drop(1)

        val tailCombos = combinationsB(tail)
        return tailCombos.map { it + head } + tailCombos.map { it * head } + tailCombos.map { "$it$head".toLong() }
        }

    private fun getExample(): List<List<Long>> {
        return """190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20""".trimMargin().lines().map { it.split(":", " ").filter { it.isNotBlank() }.map { it.toLong() } }

    }

    private fun getInput(): List<List<Long>> {
        return inputLines("07").map { it.split(":", " ").filter { it.isNotBlank() }.map { it.toLong() } }
    }
}