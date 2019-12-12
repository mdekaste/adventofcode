package year2019

import AdventOfCode
import kotlin.math.abs

fun main(){
    println(Day12.solve())
}

object Day12 : AdventOfCode<Moon>(
    2019,
    12,
    "The N-Body Problem",
    "2019day12",
    { """<x=(-?\d+), y=(-?\d+), z=(-?\d+)>""".toRegex().matchEntire(it)!!.groupValues.drop(1).map(Integer::parseInt).let{ (x,y,z)-> Moon(Point(x,y,z)) }}
) {

    override fun part1(): Any? {
        return generateSequence(input, ::step).take(1001).last().sumBy(Moon::energy)
    }

    override fun part2(): Any? {
        var xIndex : Int? = null
        var yIndex : Int? = null
        var zIndex : Int? = null

        var index = 0
        var setup = input

        while(xIndex == null || yIndex == null || zIndex == null){
            setup = step(setup)
            index++
            if(setup.all { it.velocity.x == 0 })
                xIndex = index
            if(setup.all { it.velocity.y == 0 })
                yIndex = index
            if(setup.all { it.velocity.z == 0 })
                zIndex = index
        }

        return lcm(xIndex.toLong(), lcm(yIndex.toLong(), zIndex.toLong())) * 2

    }

    private fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

    private fun gcd(a: Long, b: Long): Long = when(b){
        0L -> a
        else -> gcd(b, a % b)
    }



    private fun step(moons: List<Moon>) = moons.map{ stepMoon(it, moons) }

    private fun stepMoon(curMoon: Moon, oMoons: List<Moon>) : Moon {
        val gravity = oMoons.map(Moon::position).map { it.compare(curMoon.position) }.reduce{ acc, point -> acc.add(point)}
        val nextVelocity = gravity.add(curMoon.velocity)
        val nextPosition = curMoon.position.add(nextVelocity)
        return Moon(nextPosition, nextVelocity)
    }

}
data class Moon(
    val position: Point,
    val velocity: Point = Point()
){
    val energy = position.sumOfInts * velocity.sumOfInts
}

data class Point(
    val x: Int = 0, val y: Int = 0, val z: Int = 0
){
    val sumOfInts = abs(x) + abs(y) + abs(z)
    fun compare(oPoint : Point) = Point(x.compareTo(oPoint.x), y.compareTo(oPoint.y), z.compareTo(oPoint.z))
    fun add(oPoint : Point) = Point(x + oPoint.x, y + oPoint.y, z + oPoint.z)
}