package year2019

import AdventOfCode
import java.lang.Math.abs

fun main() = Day10.solve()

object Day10 : AdventOfCode<String>(
    2019,
    10,
    "Monitoring Station",
    "2019day10",
    { it }
){
    private val asteroids = input.mapIndexed { y: Int, row: String -> row.mapIndexedNotNull { x: Int, c: Char -> if(c == '#') Point(x,y)  else null }}.flatten()

    private lateinit var angleMap: Map<Double, List<Point>>

    override fun preloadData(){
       angleMap = asteroids.map { angleMap(it) }.maxBy { it.size }!!
    }

    override fun part1(): Any? {
        return angleMap.keys.size
    }

    override fun part2(): Any? {
        return angleMap.flatMap{(angle, points) -> points.withIndex().map{ (index, point) -> IndexedAnglePoint(index, angle, point)}}
            .sorted()
            .drop(199)
            .first()
            .point
            .let{ it.x * 100 + it.y}
    }

    private fun angleMap(point: Point) = asteroids.filterNot(point::equals).groupBy { getAngle(point, it) }

    private fun getAngle(origin: Point, other: Point) : Double{
        val xDif = other.x - origin.x
        val yDif = other.y - origin.y

        val absXDif = Math.abs(xDif)
        val absYDif = Math.abs(yDif)

        val gcd = when{
            absXDif == 0 -> absYDif
            absYDif == 0 -> absXDif
            else -> gcd(absXDif, absYDif)
        }.toDouble()

        return -Math.atan2(xDif / gcd, yDif / gcd)
    }

    private tailrec fun gcd(a: Int, b: Int) : Int = when{
            b == 0 -> a
            else -> gcd(b, a.rem(b))
    }

    private data class Point(val x : Int, val y : Int )
    private data class IndexedAnglePoint(val index: Int, val angle: Double, val point: Point) : Comparable<IndexedAnglePoint>{
        companion object {
            private val comparator = compareBy<IndexedAnglePoint>({it.index},{it.angle})
        }
        override fun compareTo(other: IndexedAnglePoint) = comparator.compare(this, other)

    }
}
