package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val MAX = 8
    val A = Array(MAX) { readLine()!!.toCharArray() }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) = 
            arrayOf(Point(x, y), Point(x+1, y), Point(x-1, y),
                Point(x-1, y-1), Point(x-1, y+1),
                Point(x, y+1), Point(x, y-1),
                Point(x+1, y+1), Point(x+1, y-1)).filter(p)
    }
    data class State(val p: Point, val time: Int = 0)
    val start = State(Point(MAX-1, 0))
    fun Point.valid() = x in 0 until MAX && y in 0 until MAX
    fun wallAt(p: Point, time: Int) = if (p.x-time >= 0) A[p.x-time][p.y] == '#' else false
    fun bfs(): Boolean {
        val q = LinkedList<State>()
        q.add(start)
        val dist = mutableSetOf(start)
        while (q.isNotEmpty()) {
            with(q.poll()) {
                if (p.x == 0)
                    return true
                for (w in p.adj { it.valid() }) {
                    val s = State(w, time+1)
                    if (wallAt(w, time)) continue
                    if (wallAt(w, time+1)) continue
                    if (s !in dist) {
                        dist += s
                        q.add(s)
                    }
                }
            }
        }
        return false
    }
    println(if (bfs()) 1 else 0)
}