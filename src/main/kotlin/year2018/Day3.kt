//package year2018
//import AdventOfCode
//
//object Day3 : AdventOfCode<Day3Data>(
//    2018,
//    3,
//    "No Matter How You Slice It",
//    "2018day3",
//    { Day3Data(it)}
//) {
//}
//
//data class Day3Data private constructor(
//    val index: Int,
//    val point: Point,
//    val wide: Int,
//    val tall: Int
//){
//    companion object {
//        operator fun invoke(input: String) = Day3Data(1, Point(1,2), 1,2)
//    }
//}
//
//data class Point(val x: Int, val y: Int)