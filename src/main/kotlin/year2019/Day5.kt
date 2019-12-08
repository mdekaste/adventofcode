package year2019
import AdventOfCode
import java.util.concurrent.atomic.AtomicInteger

fun main() = Day5.solve()

object Day5 : AdventOfCode<List<Int>>(
    2019,
    5,
    "Sunny with a Chance of Asteroids",
    "2019day5",
    {
        it.split(',').map(Integer::parseInt)
    }
){
    val actualInput = input.first()
    private lateinit var program: MutableList<Int>
    private lateinit var pointer: AtomicInteger
    private var output: Int? = null

    override fun part1(): Any? {

        program = actualInput.toMutableList()
        pointer = AtomicInteger(0)
        output = null

        loop@while(pointer.get() <= program.size){
            val (op, mode1, mode2) = getOpAndModes()
            when(op){
                1 -> add(mode1, mode2)
                2 -> mul(mode1, mode2)
                3 -> input()
                4 -> output(mode1)
                5 -> jumpIfTrue(mode1, mode2)
                6 -> jumpIfFalse(mode1, mode2)
                7 -> lessThan(mode1, mode2)
                8 -> equals(mode1, mode2)
                99 -> break@loop
                else -> error("not a valid opcode")
            }
        }
        return output
    }

    private fun add(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        val value3 = get()
        set(value1 + value2, value3)
    }

    private fun mul(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        val value3 = get()
        set(value1 * value2, value3)
    }

    private fun input(){
        val value1 = get()
        program[value1] = 5
    }

    private fun output(mode1: Int){
        val value1 = get(mode1)
        output = value1
    }

    private fun jumpIfTrue(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        if(value1 != 0){
            pointer.set(value2)
        }
    }

    private fun jumpIfFalse(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        if(value1 == 0){
            pointer.set(value2)
        }
    }

    private fun lessThan(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        val value3 = get()
        if(value1 < value2){
            set(1, value3)
        } else {
            set(0, value3)
        }
    }

    private fun equals(mode1: Int, mode2: Int){
        val value1 = get(mode1)
        val value2 = get(mode2)
        val value3 = get()
        if(value1 == value2){
            set(1, value3)
        } else {
            set(0, value3)
        }
    }

    private fun getOpAndModes() : OpAndModes{
        var value = get()
        val op = value.rem(100)
        value /= 100
        val mode1 = value.rem(10)
        value /= 10
        val mode2 = value.rem(10)
        value /= 10
        val mode3 = value.rem(10)
        return OpAndModes(op, mode1, mode2, mode3)
    }

    private data class OpAndModes(val op: Int, val mode1: Int, val mode2: Int, val mode3: Int)

    private fun get(mode: Int = 1) = when(mode){
        0 -> program[program[pointer.getAndIncrement()]]
        1 -> program[pointer.getAndIncrement()]
        else -> error("not a valid mode")
    }

    private fun set(value: Int, position: Int){
        program[position] = value
    }

    override fun part2(): Any? {
        return null
    }

}