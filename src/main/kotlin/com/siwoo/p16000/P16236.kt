package com.siwoo.p16000

import java.util.*
import kotlin.Comparator

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    data class Point(val x: Int, val y: Int) {
        fun adj(p: (Point) -> Boolean) =
            arrayOf(Point(x+1, y), Point(x-1, y),
                Point(x, y+1), Point(x, y-1))
                .filter(p)
    }
    data class Shark(val p: Point, val consume: Int = 0, val size: Int = 2) {
        fun grow(p: Point) = 
            if (consume+1 == size) Shark(p, 0, size+1) 
            else Shark(p, consume+1, size)
    }
    val (N) = readInts(1)
    val A = Array(N) { readInts(N) }
    val start = Shark(A.indices.flatMap { r -> A[r].indices.map{ c -> Point(r, c) } }
            .find { (r, c) -> A[r][c] == 9 }!!)
    fun Point.valid() = x in 0 until N && y in 0 until N
    fun Point.isBlack() = A[x][y] == 0
    fun Shark.edible(p: Point) = !p.isBlack() && A[p.x][p.y] < size
    fun Shark.moveable(p: Point) = p.isBlack() || A[p.x][p.y] <= size
    A[start.p.x][start.p.y] = 0
    tailrec fun bfs(shark: Shark, answer: Int): Int {
        val q = mutableListOf(shark.p)
        val dist = mutableMapOf(shark.p to 0)
        val pray = mutableListOf<Point>()
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                val d = dist[this]!!
                if (shark.edible(this))
                    pray.add(this)
                for (w in adj { it.valid() }) {
                    if (shark.moveable(w) 
                        && dist[w] == null) {
                        dist[w] = d + 1
                        q.add(w)
                    }
                }
            }
        }
        pray.sortWith(Comparator { p1, p2 -> compareValuesBy(p1, p2, dist::get, Point::x, Point::y) })
        return if (pray.isEmpty()) answer else {
            val p = pray[0]
            A[p.x][p.y] = 0
            bfs(shark.grow(p), answer + dist[p]!!)
        }
    }
    println(bfs(start, 0))
}