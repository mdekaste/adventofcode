package year2019
import AdventOfCode

object Day7 : AdventOfCode<List<Int>>(
    2019,
    7,
    "Amplification Circuit",
    "2019day7",
    {
        it.split(',').map(Integer::parseInt)
    }
) {
    var program = input.first().toMutableList()
    private lateinit var programIterator : ListIterator<Int>
    private lateinit var parameters : List<Int>

    override fun part1(): Any? {
        program = input.first().toMutableList()
        programIterator = program.listIterator()
        return 0
    }

    override fun part2(): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}