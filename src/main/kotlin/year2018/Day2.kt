package year2018
import AdventOfCode

fun main() = Day2.solve()

object Day2 : AdventOfCode<String>(
    2018,
    2,
    "Inventory Management System",
    "2018day2",
    { it }
) {
    override fun part1(): Any{
        return input.map { line ->
            line.groupingBy { it }
                .eachCount()
                .values
                .fold(false to false) { (double, triple), value -> (value == 2 || double) to (value == 3 || triple) }
        }.unzip().let{(twice, triple) -> twice.count { it } * triple.count { it }}
    }

    override fun part2(): Any{
        return input.flatMap { i1 -> input.map{i2 -> i1 to i2} }
            .mapNotNull {(i1, i2) -> singularOverlap(i1,i2) }
            .first()
    }

    private fun singularOverlap(i1: String, i2: String) : String?{
        var nonOverlapCount = 0
        var nonOverlapIndex = 0
        for(i in i1.indices){
            if(i1[i] != i2[i]){
                nonOverlapCount++
                if(nonOverlapCount == 2){
                    return null
                } else if(nonOverlapCount == 1){
                    nonOverlapIndex = i
                }
            }
        }
        if(nonOverlapCount == 1){
            return i1.substring(0, nonOverlapIndex) + i2.substring(nonOverlapIndex + 1)
        }
        return null
    }

}