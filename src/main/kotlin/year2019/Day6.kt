package year2019
import AdventOfCode

fun main() = Day6.solve()

object Day6 : AdventOfCode<Pair<String, String>>(
    2019,
    6,
    "Universal Orbit Map",
    "2019day6",
    {
        it.split(')').let{(first, second) -> first to second}
    }
) {
    private val orbitalCount = mutableMapOf<String, Int>()
    private val orbitalMap = input.associateBy({ it.second }, { it.first })

    override fun part1(): Any? {
        return orbitalMap
            .keys
            .map(::calculateOrbitalCount)
            .sum()
    }

    private fun calculateOrbitalCount(planet: String) : Int =
        orbitalCount.getOrPut(planet) {
            orbitalMap[planet]
                ?.let { 1 + calculateOrbitalCount(it) }
                ?: 0
        }

    override fun part2(): Any? {
        val youRoad = generateSequence("YOU", orbitalMap::get).withIndex().map {(index, value) -> value to index }.toMap()
        val sanRoad = generateSequence("SAN", orbitalMap::get).withIndex().map {(index, value) -> value to index }.toMap()
        return orbitalMap.values
            .filter { youRoad.containsKey(it) && sanRoad.containsKey(it)}
            .map { youRoad[it]!! + sanRoad[it]!!}
            .min()!! - 2
    }
}