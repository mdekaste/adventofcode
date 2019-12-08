import java.io.File
import kotlin.streams.asStream
import kotlin.system.measureTimeMillis

abstract class AdventOfCode<T>(
    val year: Int,
    val day: Int,
    val description: String,
    fileName: String,
    inputTransform : (String) -> T
) {
    protected var input = File(fileName).useLines { it.map(inputTransform).toList() }

    fun solve(){
        println("--- Year $year Day $day: $description ---")
        println(part1())
        println(part2())
        generateSequence {
            measureTimeMillis {
                part1()
                part2()
            }
        }.take(1000)
            .average()
            .let{println("average time to calculate: $it ms")}
    }

    abstract fun part1() : Any?
    abstract fun part2() : Any?
}