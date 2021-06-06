package com.siwoo.p1000

fun main(args: Array<String>) {
    val (N, M) = readLine()!!.split(" ")
        .map(String::toInt)
        .toIntArray()
    val A = Array(N) { readLine()!!.toCharArray() }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) = 
            arrayOf(Point(x+1, y), Point(x-1, y),
                    Point(x, y+1), Point(x, y-1))
                    .filter(p)
    }
    val start = Point(0, 0)
    val visit = mutableSetOf(A[start.x][start.y])
    var max = 0
    fun traverse(p: Point, cnt: Int): Unit =
        with(p) {
            max = max.coerceAtLeast(cnt)
            for (w in adj { 
                    it.x in 0 until N
                    && it.y in 0 until M
                    && !visit.contains(A[it.x][it.y]) }) {
                    visit.add(A[w.x][w.y])
                    traverse(w, cnt+1)
                    visit.remove(A[w.x][w.y])
            }
        }
    traverse(start, 1)
    println(max)
}