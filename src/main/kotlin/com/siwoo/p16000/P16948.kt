package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) = 
            arrayOf(
                Point(x-2, y-1), Point(x-2, y+1),
                Point(x, y-2), Point(x, y+2),
                Point(x+2, y-1), Point(x+2, y+1))
                .filter(p)
    }
    val (N) = readInts(1)
    val (v, w, pv, pw) = readInts(4)
    val START = Point(v, w)
    val END = Point(pv, pw)
    fun Point.valid() = x in 0 until N && y in 0 until N
    fun bfs(start: Point): Int? {
        val q = mutableListOf(start)
        val distance = mutableMapOf(start to 0)
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                val d = distance[this]!!
                if (this == END) return d
                for (w in adj { it.valid() }) {
                    if (distance[w] == null) {
                        distance[w] = d + 1
                        q.add(w)
                    }
                }
            }
        }
        return null
    }
    println(bfs(START) ?: -1)
}