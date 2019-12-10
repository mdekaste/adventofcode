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
        preloadData()
        println(part1())
        println(part2())
        generateSequence {
            measureTimeMillis {
                preloadData()
                part1()
                part2()
            }
        }.take(100)
            .average()
            .let{println("average time to calculate: $it ms")}
    }
    protected open fun preloadData(){

    }
    abstract fun part1() : Any?
    abstract fun part2() : Any?
}