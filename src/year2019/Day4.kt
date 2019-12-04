package year2019

import java.util.stream.IntStream
import kotlin.time.measureTimedValue

fun main() = generateSequence {
        measureTimedValue { Day4.part1() to Day4.part2() }
    }.take(1000)
    .minBy { it.duration }
    .let(::println)

object Day4 {
    private val lowerbound = 145852
    private val upperbound = 616942

    fun part1() = IntStream.range(lowerbound, upperbound).parallel().filter(::meetsCriteria).count()
    fun part2() = IntStream.range(lowerbound, upperbound).parallel().filter(::meetsCriteria2).count()

    private fun meetsCriteria(input: Int) : Boolean{
        var hasDouble = false
        var previous = 10
        var toReduce = input

        while(toReduce > 0){
            var current = toReduce.rem(10)
            if(current == previous){
                hasDouble = true
            } else if(current > previous){
                return false
            }
            previous = current
            toReduce /= 10
        }
        return hasDouble
    }

    private fun meetsCriteria2(input: Int) : Boolean{
        var hasDouble = false
        var previousDigit = 10
        var previousDigitCount = 1
        var toReduce = input

        while(toReduce > 0){
            val current = toReduce.rem(10)
            when{
                current == previousDigit -> previousDigitCount++
                current > previousDigit -> return false
                current < previousDigit -> {
                    if(previousDigitCount == 2) {
                        hasDouble = true
                    }
                    previousDigit = current
                    previousDigitCount = 1
                }
            }
            toReduce /= 10
        }
        return hasDouble || previousDigitCount == 2
    }
}