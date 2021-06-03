package com.siwoo.p16000

private data class Point(val x: Int, val y: Int) {
    operator fun plus(p: Point) = Point(x+p.x, y+p.y)
}

private enum class Direction(val p: Point) {
    LEFT(Point(0, -1)), RIGHT(Point(0, 1)), 
    UP(Point(-1, 0)), DOWN((Point(1, 0)))
}

fun main(args: Array<String>) {
    val (N, M) = readLine()?.split(" ")
        ?.map(String::toInt)?.toIntArray()
        ?: IntArray(0)
    val A = Array(N) {
        readLine() ?: ""
    }
    val (c1, c2) = (0 until N*M).filter { A[it/M][it%M] == 'o' }
        .map { Point(it/M, it%M)  }
    fun Point.isDropped() = !(x in 0 until N && y in 0 until M)
    fun Point.isWall() = !isDropped() && A[x][y] == '#'
    var min = Int.MAX_VALUE
    fun select(tried: Int, c1: Point, c2: Point) {
        if (tried > 10 || (c1.isDropped() || c2.isDropped())) 
            return
        for (d in Direction.values()) {
            val n1 by lazy {
                val x = c1.plus(d.p)
                if (x.isWall()) c1 else x
            }
            val n2 by lazy {
                val x = c2.plus(d.p)
                if (x.isWall()) c2 else x
            }
            if ((n1.isDropped() && !n2.isDropped()) || 
                (!n1.isDropped() && n2.isDropped())) {
                min = min.coerceAtMost(tried)
            }
            select(tried+1, n1, n2)
        }
    }
    select(1, c1, c2)
    println(if (min == Int.MAX_VALUE) -1 else min)
}