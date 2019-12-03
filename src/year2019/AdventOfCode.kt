package year2019

import java.io.File
import kotlin.system.measureNanoTime

abstract class AdventOfCode<T>(private val fileName: String, private val fileModifier : (Sequence<Sequence<String>>) -> Sequence<T>){
    protected val fileInput = fileModifier(File(fileName).useLines{ it.asSequence() }.map { it.splitToSequence(',') })

    final fun solve() = measureNanoTime {
        println("part1 output: ${part1()}")
        println("part2 output: ${part2()}")
    }.let { println("solving both parts of ${this::class.simpleName} took ${it / 1000000.0} milliseconds") }

    protected abstract fun part1(): Any
    protected abstract fun part2(): Any
}