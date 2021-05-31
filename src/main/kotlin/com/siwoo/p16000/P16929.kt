package com.siwoo.p16000


fun main(args: Array<String>) {
    val s = readLine()!!.split(" ").map{ it.toInt() }
    val (N, M) = s[0] to s[1]
    val A = Array(N) { readLine()!!.toCharArray() }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) =
            listOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1))
                .filter(p)
    }
    
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun Point.value() = A[x][y]
    
    fun dfs(p: Point, k: Point, visit: MutableSet<Point>): Boolean {
        if (visit.contains(p)) return true
        visit.add(p)
        for (w in p.adj { it.valid()
                && it.value() == p.value() 
                && it != k }) {
            if (dfs(w, p, visit))
                return true
        }
        return false
    }
    
    val visit = mutableSetOf<Point>()
    (0 until N).map { row ->
        (0 until M).map { col ->
            val p = Point(row, col)
            if (!visit.contains(p) && dfs(p, p, visit)) {
                println("Yes")
                return
            }
        }
    }
    println("No")
}