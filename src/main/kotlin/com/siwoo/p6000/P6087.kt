package com.siwoo.p6000

import java.lang.IllegalStateException

private data class Point(val x: Int, val y: Int) {
    operator fun plus(p: Point) = Point(x+p.x, y+p.y)
}

private enum class Direction {
    HORIZONTAL, VERTICAL;
    fun reverse() = when (this) {
        HORIZONTAL -> VERTICAL
        VERTICAL -> HORIZONTAL
    }
}
private data class State(val point: Point, val direction: Direction) {
    fun adj(predicate: (Point) -> Boolean) = when(direction) {
        Direction.HORIZONTAL -> arrayOf(Point(1, 0), Point(-1, 0))
        Direction.VERTICAL -> arrayOf(Point(0, 1), Point(0, -1))
    }.filter(predicate)
}

fun main(args: Array<String>) {
    val (M, N) = readLine()!!.split(" ").map(String::toInt)
    val A = Array(N) { readLine()!! }
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun Point.moveable() = this.valid() && (A[x][y] == '.' || A[x][y] == 'C')
    val (START, END) = A.indices
        .flatMap { r -> A[r].indices.map { c -> Point(r, c)  } }
        .filter { A[it.x][it.y] == 'C' }
        .toList()
    fun bfs(start: Point): Int {
        val q = mutableListOf(
            State(start, Direction.HORIZONTAL),
            State(start, Direction.VERTICAL))
        val dist = q.map { it to 0 }.toMap().toMutableMap()
        while (q.isNotEmpty()) {
            with (q.removeAt(0)) {
                val d = dist[this]!!
                if (this.point == END) return d-1
                for (w in adj { true }) {
                    val seq = generateSequence(point) { it + w }
                        .takeWhile { it.moveable() }
                    for (k in seq) {
                        with(State(k, this.direction.reverse())) {
                            if (dist[this] == null) {
                                dist[this] = d + 1
                                q.add(this)
                            }
                        }
                    }
                }
            }
        }
        throw IllegalStateException()
    }
    println(bfs(START))
}