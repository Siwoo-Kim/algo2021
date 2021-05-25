package com.siwoo.p2000

import java.util.*

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    data class Point(val x: Int, val y: Int, val k: Char?) {
        val house: Boolean
            get() = k == '1'
        fun valid(): Boolean = x in 0 until N && y in 0 until N
        fun adj(): List<Point> =
            mutableListOf(
                Point(x-1, y, null),
                Point(x+1, y, null),
                Point(x, y-1, null),
                Point(x, y+1, null))
                .filter { it.valid() }
    }

    val B = List(N) { row -> 
        val s = readLine() ?: ""
        List(N) { Point(row, it, s[it]) }
    }
    val visit = mutableSetOf<Point>()
    val pq = PriorityQueue<Int>()

  
    
    fun dfs(p: Point): Int {
        visit.add(p)
        var cnt = 0
        for (q in p.adj().map{ B[it.x][it.y] }) {
            if (!visit.contains(q) && q.house)
                cnt += dfs(q)
        }
        return cnt + 1
    }
    for (i in 0 until N)
        for (j in 0 until N) {
            if (!visit.contains(B[i][j]) && B[i][j].house) {
                pq.add(dfs(B[i][j]))
            }
        }
    println(pq.size)
    while (!pq.isEmpty())
        println(pq.poll())
}