//package year2018
//
//import java.io.File
//import kotlin.math.abs
//
//fun main(){
//    val x = Day23.part2().let(::println)
//}
//
//
//
//object Day23{
//    private val regex = """pos=<(-?\d+),(-?\d+),(-?\d+)>, r=(\d+)""".toRegex()
//
//    private val input = File("day23input")
//        .useLines { it.toList() }
//        .map {
//            regex.matchEntire(it)!!.groupValues.drop(1).map(Integer::parseInt).let{(x,y,z, radius) -> PointRadius(TriplePoint(x,y,z),radius)}
//        }
//
//    private data class TriplePoint(
//        val x: Int,
//        val y: Int,
//        val z: Int
//    )
//
//    private data class PointRadius(
//        val triplePoint: TriplePoint,
//        val radius: Int
//    )
//
//    fun part1() : Int {
//        val largest = input.maxBy { it.radius }!!
//        return input.map(PointRadius::triplePoint).count { withinRadius(largest, it) }
//    }
//
//    private fun withinRadius(source: PointRadius, other: TriplePoint) : Boolean{
//        val (sourceX, sourceY, sourceZ) = source.triplePoint
//        val (otherX, otherY, otherZ) = other
//        val distance = abs(sourceX - otherX) + abs(sourceY - otherY) + abs(sourceZ - otherZ)
//        return distance <= source.radius
//    }
//
//    fun part2() : Int{
//        val underTest = input.first()
//        println(underTest.radius)
//        for (triplePoint in allPointsInRange(underTest)) {
//            println(triplePoint)
//        }
//        //input.flatMap(::allPointsInRange).groupingBy { it }.eachCount().maxBy { it.value }.let(::println)
//        return 0
//    }
//
//
//}