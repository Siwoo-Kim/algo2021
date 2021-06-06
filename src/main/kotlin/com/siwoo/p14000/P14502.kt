package com.siwoo.p14000

import java.util.*

@ExperimentalStdlibApi
fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val BLOCKS = 3
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) =
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1)).filter(p)
    }
    val (N, M) = readInts(2)
    val blanks = mutableListOf<Point>()
    val viruses = mutableListOf<Point>()
    var walls = mutableListOf<Point>()
    val A = Array(N) { r -> 
        val a = readInts(M)
        for ((c, v) in a.withIndex()) {
            with (Point(r, c)) {
                when (v) {
                    1 -> walls.add(this)
                    2 -> viruses.add(this)
                    else -> blanks.add(this)
                }
            }
        }
        a
    }
    fun Point.valid() = x in 0 until N && y in 0 until M
    val bfs = { newWalls: List<Point> ->
        for (p in newWalls)
            with(p) { A[x][y] = 1 }
        val q = viruses.toMutableList()
        val dist = viruses.map { it to 0 }.toMap().toMutableMap()
        while (q.isNotEmpty()) {
            with(q.removeFirst()) {
                val d = dist[this]!!
                for (w in adj {
                    it.valid() && dist[it] == null
                            && A[it.x][it.y] == 0}) {
                    dist[w] = d + 1
                    q.add(w)
                }
            }
        }
        for (p in newWalls)
            with(p) { A[x][y] = 0 }
        N * M - dist.size - walls.size - newWalls.size
    }
    
    fun comb(i: Int, select: MutableList<Point>, consumer: (List<Point>) -> Int): Int {
        if (select.size == BLOCKS) return consumer(select)
        var max = 0
        for (j in i until blanks.size) {
            select.add(blanks[j])
            max = max.coerceAtLeast(comb(j+1, select, consumer))
            select.removeLast()
        }
        return max
    }
    println(comb(0, mutableListOf(), bfs))
}