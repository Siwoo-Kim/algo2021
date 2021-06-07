package com.siwoo.p16000

import java.io.BufferedWriter
import java.io.OutputStreamWriter

val out = BufferedWriter(OutputStreamWriter(System.out))
fun main(args: Array<String>) {
    val (N, M) = readLine()!!.split(" ").map(String::toInt)
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) =
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1)).filter(p)
    }
    val A = Array(N) { readLine()!!.toCharArray() }
    val walls = mutableListOf<Point>()
    val blanks = mutableListOf<Point>()
    fun Point.isBlank() = A[x][y] == '0'
    fun Point.isWall() = !isBlank()
    fun Point.valid() = x in 0 until N && y in 0 until M
    val groups = Array(N) { IntArray(M) { -1 } }
    val cc = mutableMapOf<Int, Int>()
    A.withIndex().forEach { outer ->
        outer.value.withIndex().forEach { inner ->
            val p = Point(outer.index, inner.index)
            if (p.isWall())
                walls.add(Point(outer.index, inner.index))
            else 
                blanks.add(Point(outer.index, inner.index))
        }
    }
    fun dfs(index: Int, v: Point): Int { 
        with(v) {
            groups[x][y] = index
            var cnt = 1
            for (w in adj { it.valid() && it.isBlank() }) {
                if (groups[w.x][w.y] == -1)
                    cnt += dfs(index, w)    
            }
            return cnt
        }
    }
    var index = 0
    for (v in blanks) {
        if (groups[v.x][v.y] == -1) {
            cc[index] = dfs(index, v)
            index++
        }
    }
    for (v in walls) {
        val set = mutableSetOf<Int>()
        for (w in v.adj { it.valid() && it.isBlank() 
                && groups[it.x][it.y] != -1 }) {
            set += groups[w.x][w.y]
        }
        A[v.x][v.y] = ((set.map { cc[it] ?: 0 }.fold(1) { acc, i -> acc + i } % 10) + '0'.toInt()).toChar()
    }
    A.forEach { out.write(it.joinToString("", postfix = "\n")) }
    out.flush()
}