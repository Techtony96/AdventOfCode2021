package net.ajpappas.advent2021

import java.io.File

fun main() {

    val input = File("src/main/resources/day1/input.txt").readLines().map { s -> s.toInt() }
    val api = Day1();

    // Part 1
    println(api.countIncreases(input))

    // Part 2
    val summed = input.runningReduceIndexed{index, acc, value -> acc + value - input.getOrElse(index-3){0} }.slice(2 until input.size)
    println(api.countIncreases(summed))
}


class Day1 {

    fun countIncreases(input : List<Int>) : Int {
        var last : Int = Integer.MAX_VALUE
        return input.map { i ->
            if (i > last) {
                last = i
                return@map 1
            } else {
                last =i
                return@map 0
            }
        }.sum()
    }
}