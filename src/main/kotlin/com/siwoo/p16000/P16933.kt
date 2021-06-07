package com.siwoo.p16000

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
    data class State(val p: Point, val k: Int, val daylight: Boolean = true) {
        fun indexes() = k to (if (daylight) 0 else 1)
    }
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun Point.isWall() = A[x][y] == '1'
    val START = Point(0, 0)
    val END = Point(N-1, M-1)
    fun bfs(start: Point): Int {
        val q = LinkedList<State>()
        q.add(State(start, K))
        val distance = Array(N) { Array(M) { Array(K+1) { IntArray(2) } } }
        distance[start.x][start.y][K][0] = 1
        while (q.isNotEmpty()) {
            with(q.poll()) {
                val (k, b) = indexes()
                val d = distance[p.x][p.y][k][b]
                if (p == END) return d
                for (w in p.adj { it.valid() }) {
                    val s = when {
                        w.isWall() && k == 0 -> null
                        w.isWall() && daylight -> State(w, k-1, !daylight)
                        w.isWall() && !daylight -> State(p, k, !daylight)
                        else -> State(w, k, !daylight)
                    }
                    val (kk, bb) = s?.indexes() ?: -1 to -1
                    if (s != null && distance[s.p.x][s.p.y][kk][bb] == 0) {
                        distance[s.p.x][s.p.y][kk][bb] = d+1
                        q.add(s)
                    }
                }
            }
        }
        return -1
    }
    println(bfs(START))
}