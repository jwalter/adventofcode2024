import org.junit.jupiter.api.Test
import kotlin.text.contains
import kotlin.text.indexOf
import kotlin.text.split

class Day05 {

    @Test
    fun `part 1 example`() {
        assert(solveA(getExample()) == 143)
    }

    @Test
    fun `part 1`() {
        assert(solveA(getInput()) == 6051)
    }

    @Test
    fun `part 2 example`() {
        assert(solveB(getExample()) == 123)
    }

    @Test
    fun `part 2`() {
        assert(solveB(getInput()) == 5093)
    }

    private fun solveA(input: Pair<List<Pair<String, String>>, List<String>>): Int {
        return input.second.filter { update ->
            val rules = input.first
            isSorted(rules, update)
        }.map { it.split(",").middle() }.sumOf { it.toInt() }
    }

    private fun solveB(input: Pair<List<Pair<String, String>>, List<String>>): Int {
        val rules = input.first
        val unsorted = input.second.filter { update ->
            !isSorted(rules, update)
        }
        val sorted = unsorted.map {
            it.split(",").sortedWith { o1, o2 ->
                rules.firstOrNull { (a, b) -> a == o1 && b == o2 || a == o2 && b == o1 }
                    ?.let { (a, _) -> if (a == o1) -1 else 1 } ?: 0
            }
        }
        return sorted.map { it.middle() }.sumOf { it.toInt() }
    }

    private fun isSorted(
        rules: List<Pair<String, String>>,
        update: String,
    ): Boolean = rules.all { rule ->
        val (a, b) = rule
        !update.contains(a) || !update.contains(b) || update.indexOf(a) < update.indexOf(b)
    }

    private fun getExample(): Pair<List<Pair<String, String>>, List<String>> {
        return parseInput(
            """
            47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
        """
        )
    }

    private fun parseInput(raw: String): Pair<List<Pair<String, String>>, List<String>> {
        val rules = raw.lines().filter { it.contains("|") }.map {
            val (a, b) = it.split("|")
            a to b
        }
        val updates = raw.lines().filter { it.contains(",") }
        return rules to updates
    }

    private fun getInput(): Pair<List<Pair<String, String>>, List<String>> {
        return parseInput(input("05"))
    }
}

private fun List<String>.middle(): String {
    return this[this.size / 2]
}
