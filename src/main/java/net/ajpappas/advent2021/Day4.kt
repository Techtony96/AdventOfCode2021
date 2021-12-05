package net.ajpappas.advent2021

import java.io.File
import kotlin.system.exitProcess

fun main() {
    val input = File("src/main/resources/day4/input.txt").readLines()
    val balls = input[0].split(",").map(Integer::valueOf)
    val boards = input.subList(2, input.size).windowed(5, 6).map{ s -> Board(s)}

    var lastBoard : Board? = null

    balls.forEach { ballValue ->
        boards.forEach { b ->
            // Mark the tile with the ball that was just called
            b.markTile(ballValue)

            // If we are the last board
            if (lastBoard == b && b.isWinState()) {
                println(b.board.flatMap { it.asIterable() }.filter { p -> !p.marked }.sumOf(Board.Position::num) * ballValue)
                return
            }

            if (boards.count(Board::isWinState) == boards.size - 1) {
                lastBoard = boards.first() { b -> !b.isWinState() }
            }
        }
    }
}

class Board(input: List<String>) {

    val board : List<List<Position>>

    init {
        board = input.map { s -> s.trim().split(Regex("\\s+")) }.map { s -> s.map { i -> Integer.valueOf(i) }.map{ i -> Position(i) } }
    }

    fun markTile(num : Int) {
        board.flatMap { it.asIterable() }.filter { p -> p.num == num }.forEach { p -> p.marked = true }
    }

    fun isWinState() : Boolean {
        // Check for wins in rows
        for (row in board) {
            if (!row.any { p -> !p.marked })
                return true
        }

        // Check for wins in each column
        for (column in board.indices) {
            var allMarked = true
            for (row in board) {
                if (!row[column].marked)
                    allMarked = false
            }
            if (allMarked)
                return true
        }
        return false
    }

    class Position(val num : Int) {
        var marked : Boolean = false
    }
}

class Day4 {


}