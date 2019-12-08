package year2019
import AdventOfCode

fun main() = Day8.solve()

object Day8 : AdventOfCode<List<Int>>(
    2019,
    8,
    "Space Image Format",
    "2019day8",
    {
        it.map(Character::getNumericValue)
    }
){
    val wide = 25
    val tall = 6
    val actualInput = input.first().chunked(wide * tall)

    override fun part1(): Any? = actualInput
            .map { layer -> layer.groupingBy {it}.eachCount() }
            .minBy { it[0]!! }!!
            .let { it[1]!! * it[2]!!  }

    override fun part2() = actualInput.reduce(::reduce).chunked(wide).joinToString("\n")

    private fun reduce(firstList: List<Int>, secondList: List<Int>) = firstList.mapIndexed {
            index: Int, i: Int -> when(i){
                in 0..1 -> i
                else -> secondList[index]
            }
    }
}