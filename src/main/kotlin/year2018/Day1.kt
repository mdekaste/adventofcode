package year2018
import AdventOfCode

fun main() = Day1.solve()

object Day1 : AdventOfCode<Int>(
    2018,
    1,
    "Chronal Calibration",
    "2018day1",
    Integer::parseInt
){
    override fun part1(): Int {
        return input.sum()
    }

    override fun part2(): Int {
        return recursiveCalc()
    }

    private tailrec fun recursiveCalc(frequency: Int = 0, frequencies: MutableSet<Int> = mutableSetOf(0)): Int{
        var mutableFreq = frequency
        for(i in input){
            mutableFreq += i
            if(!frequencies.add(mutableFreq)){
                return mutableFreq
            }
        }
        return recursiveCalc(mutableFreq, frequencies)
    }
}