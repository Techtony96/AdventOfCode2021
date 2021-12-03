package net.ajpappas.advent2021

import java.io.File

fun main() {
    val input = File("src/main/resources/day2/input.txt").readLines()
    val instructions = input.map { s -> s.split(" ") }.map { s -> Instruction(Movement.valueOf(s[0].uppercase()), s[1].toInt()) }

    val destination = Day2.destination(instructions)
    println("Part 2: ${destination.pos * destination.depth}")
}


object Day2 {
    fun destination(instructions: List<Instruction>) : GPS {
        val gps = GPS()
        instructions.forEach { i ->
            when(i.movement) {
                Movement.FORWARD -> gps.forward(i.amount)
                Movement.DOWN -> gps.down(i.amount)
                Movement.UP -> gps.up(i.amount)
            }
        }
        return gps
    }
}

enum class Movement {
    FORWARD, DOWN, UP
}

class Instruction(val movement: Movement, val amount: Int)

class GPS {
    var pos = 0
    var depth = 0
    var aim = 0

    fun forward(amount : Int) {
        pos += amount;
        depth += aim * amount;
    }

    fun down(amount : Int) {
        aim += amount;
    }

    fun up(amount : Int) {
        aim -= amount
    }
}