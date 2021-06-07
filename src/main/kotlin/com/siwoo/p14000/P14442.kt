package com.siwoo.p14000

import java.util.*

fun main() {
    val (N, M, K) = readLine()!!.split(" ").map(String::toInt)
    val A = Array(N) { readLine()!!.toCharArray() }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) =
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1))
                .filter(p)  
    }
    data class State(val p: Point, val k: Int)
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun Point.isWall() = A[x][y] == '1'
    val START = Point(0, 0)
    val END = Point(N-1, M-1)
    fun bfs(start: Point): Int {
        val q = LinkedList<State>()
        q.add(State(start, K))
        val distance = Array(N) { Array(M) { IntArray(K+1) } }
        distance[start.x][start.y][K] = 1
        while (q.isNotEmpty()) {
            with(q.poll()) {
                val d = distance[p.x][p.y][k]
                if (p == END) return d
                for (w in p.adj { it.valid() }) {
                    val s = if (w.isWall()) {
                        if (k == 0) null
                        else State(w, k-1)
                    } else State(w, k)
                    if (s != null && distance[w.x][w.y][s.k] == 0) {
                        distance[w.x][w.y][s.k] = d+1
                        q.add(s)
                    }
                }
            }
        }
        return -1
    }
    println(bfs(START))
}