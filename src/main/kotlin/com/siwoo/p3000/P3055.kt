package com.siwoo.p3000

fun main(args: Array<String>) {
    val (N, M) = readLine()!!.split(" ").map(String::toInt)
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) = 
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1)).filter(p)
    }
    data class State(val p: Point, val time: Int = 0)
    fun Point.valid() = x in 0 until N && y in 0 until M
    val A = Array(N) { readLine()!! }
    var start: Point? = null
    var end: Point? = null
    val waters = mutableListOf<Point>()
    val walls = mutableListOf<Point>()
    A.indices.forEach { r ->
        A[r].indices.forEach { c ->
            val p = Point(r, c)
            when (A[r][c]) {
                'D' -> end = p
                'S' -> start = p
                '*' -> waters.add(p)
                'X' -> walls.add(p)
            }
        }
    }
    val moveable = run {
        val W = Array(N) { IntArray(M) { Int.MAX_VALUE } }
        val q = waters
        for (p in walls)
            W[p.x][p.y] = 0
        for (p in waters)
            W[p.x][p.y] = 0
        W[end!!.x][end!!.y] = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                val d = W[x][y]
                for (w in adj { it.valid() }) {
                    if (end == w) continue
                    if (W[w.x][w.y] == Int.MAX_VALUE) {
                        W[w.x][w.y] = d + 1
                        q.add(w)
                    }
                }
            }
        }
        W
    }
    fun bfs(start: Point): Int? {
        val q = mutableListOf(State(start))
        val dist = mutableMapOf(start to 0)
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                val d = dist[p]!!
                if (p == end) return d
                for (w in p.adj { it.valid() }) {
                    if (dist[w] == null 
                        && time+1 < moveable[w.x][w.y]) {
                        dist[w] = d+1
                        q.add(State(w, time+1))
                    }
                }
            }
        }
        return null
    }
    println(bfs(start!!) ?: "KAKTUS")
}