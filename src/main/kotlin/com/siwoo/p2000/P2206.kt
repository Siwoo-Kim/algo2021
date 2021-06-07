package com.siwoo.p2000

import java.util.*

fun main(args: Array<String>) {
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) = 
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1)).filter(p)
    }
    data class State(val p: Point, val use: Boolean = false)
    val (N, M) = readLine()!!.split(" ").map(String::toInt)
    val A = Array(N) { readLine()!!.toCharArray() }
    val START = Point(0, 0)
    val END = Point(N-1, M-1)
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun bfs(): Int {
        val q = LinkedList<State>()
        q.add(State(START))
        val distance = Array(N) { Array(M) { IntArray(2) } }
        fun distanceOf(s: State): Int = with(s) { distance[p.x][p.y][if (use) 0 else 1] }
        fun visit(s: State, d: Int) = with(s) { distance[p.x][p.y][if (use) 0 else 1] = d }
        visit(q.first, 1)
        while (q.isNotEmpty()) {
            with(q.poll()) {
                val d = distanceOf(this)
                if (p == END) return d
                for (w in p.adj { it.valid() }) {
                    var s: State? = State(w, use)
                    if (A[w.x][w.y] == '1')
                        s = if (!use) State(w, true) else null
                    if (s != null && distanceOf(s) == 0) {
                        visit(s, d+1)
                        q.add(s)
                    }
                }
            }
        }
        return -1
    }
    println(bfs())
}