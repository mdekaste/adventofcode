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
) {
    private const val wide = 25
    private const val tall = 6
    private val actualInput = input.first().chunked(wide * tall)

    override fun part1(): Any? = actualInput
        .map(::groupCounter)
        .minBy{ it[0]!!}!!
        .let { it.getValue(1) * it.getValue(2) }

    private fun groupCounter(layer: List<Int>) = layer.groupingBy{ it }.eachCount()

    override fun part2() = actualInput.reduce(::reduce).chunked(wide).joinToString("\n") { it.map { if(it == 0){ '▓' } else { '░' }}.joinToString("") }

    private fun reduce(firstList: List<Int>, secondList: List<Int>) = firstList.mapIndexed { index: Int, i: Int ->
        when (i) {
            2 -> secondList[index]
            else -> i
        }
    }
}