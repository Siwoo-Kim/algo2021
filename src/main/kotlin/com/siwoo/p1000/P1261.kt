package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int, delim: String -> 
        readLine()?.split(delim)?.filter(String::isNotBlank)
                ?.map { it.toInt() }?.toIntArray()
                ?: IntArray(N)
    }
    val A = readInts(2, " ")
    val (M, N) = A[0] to A[1]
    val P = Array(N) { readInts(N, "") }
    val range = { x: Int, y: Int ->
        x in 0 until N && y in 0 until M
    }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean): List<Point> =
                listOf(Point(x+1, y), Point(x-1, y),
                        Point(x, y+1), Point(x, y-1))
                        .filter(p)
    }
    
    val start = Point(0, 0)
    val end = Point(N-1, M-1)
    val q = LinkedList<Point>()
    q.add(start)
    val distance = mutableMapOf(start to 0)
    while (!q.isEmpty()) {
        val v = q.pollFirst()
        if (v == end) {
            println(distance[v])
            return
        }
        v.adj { range(it.x, it.y) && !distance.contains(it) }
            .forEach { 
                if (P[it.x][it.y] == 0) {
                    distance[it] = distance[v]!!
                    q.addFirst(it)
                } else {
                    distance[it] = distance[v]!! + 1
                    q.addLast(it)
                }
            }
    }
}