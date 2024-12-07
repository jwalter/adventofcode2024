import org.junit.jupiter.api.Test
import kotlin.math.abs

class Day03 {

    @Test
    fun `part 1 example`() {
        assert(mul(getExample()) == 161)
    }

    @Test
    fun `part 1`() {
        assert(mul(getInput()) == 168539636)
    }

    @Test
    fun `part 2 example`() {
        assert(mulTwo(getExample2()) == 48)
    }

    @Test
    fun `part 2`() {
        assert(mulTwo(getInput()) == 97529391)
    }

    fun mul(input: String): Int {
        val parts = input.split("mul(")
        return parts.sumOf { mulFragment(it) }
    }

    fun mulTwo(input: String): Int {
        val doParts = input.split("do()").map { it.split("don't()")[0] }
        return doParts.sumOf { mul(it) }
    }

    private fun mulFragment(fragment: String): Int {
        val a = fragment.takeWhile { it != ','  }.toIntOrNull()
        val b = fragment.dropWhile { it != ','}.drop(1).takeWhile { it != ')' }.toIntOrNull()
        if (a == null || b == null) {
            return 0
        }
        return a * b
    }

    private fun getExample(): String {
        return "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    }

    private fun getExample2(): String {
        return "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    }

    private fun getInput(): String {
        return inputLines("03").joinToString()
    }
}