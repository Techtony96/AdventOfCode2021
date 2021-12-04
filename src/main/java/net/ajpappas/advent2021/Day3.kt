package net.ajpappas.advent2021

import java.io.File

val MASK : Int = 0b111111111111

fun main() {
    val input = File("src/main/resources/day3/input.txt").readLines().map { s -> s.toCharArray().toList() }
    val transposed = Day3.transpose(input)
    val binary = transposed.map { list -> list.groupingBy{it}.eachCount() }.map { r -> r.maxByOrNull { it.value }?.key }.joinToString(separator = "")
    val mostCommon = Integer.parseUnsignedInt(binary, 2)
    val leastCommon = mostCommon.inv() and MASK
    println("Part 1: ${mostCommon * leastCommon}")
}

object Day3 {
    // Source: https://gist.github.com/clementgarbay/49288c006252955c2a3c6139a61ca92a
    fun <E> transpose(xs: List<List<E>>): List<List<E>> {
        // Helpers
        fun <E> List<E>.head(): E = this.first()
        fun <E> List<E>.tail(): List<E> = this.takeLast(this.size - 1)
        fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)

        xs.filter { it.isNotEmpty() }.let { ys ->
            return when (ys.isNotEmpty()) {
                true -> ys.map { it.head() }.append(transpose(ys.map { it.tail() }))
                else -> emptyList()
            }
        }
    }
}

