package com.siwoo.p12000

import java.util.*

data class Point(val x: Int, val y: Int)

enum class Direction {
    LEFT, RIGHT, UP, DOWN
}

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = Array(N) { readInts(N) }
    fun Array<IntArray>.deepCopy() = Array(size) { this[it].copyOf() }
    fun Array<IntArray>.merge(to: Point, from: Point) {
        this[to.x][to.y] += this[from.x][from.y]
        this[from.x][from.y] = 0
    }
    fun Array<IntArray>.swap(i: Point, j: Point) {
        val tmp = this[i.x][i.y]
        this[i.x][i.y] = this[j.x][j.y]
        this[j.x][j.y] = tmp
    }
    fun go(cnt: Int, A: Array<IntArray>): Int {
        if (cnt == 5) 
            return A.asSequence().flatMap { it.asSequence() }
                .fold(0) { acc, i -> acc.coerceAtLeast(i) }
        var max = 0
        for (d in Direction.values()) {
            val copy = A.deepCopy()
            fun Point.moveable() = x in 0 until N && y in 0 until N
            fun Point.isBlank() = copy[x][y] == 0
            fun Point.value() = copy[x][y]
            val exclude = mutableSetOf<Point>()
            fun merge(rowRange: IntProgression, colRange: IntProgression,
                      fold: (Int, Int) -> IntProgression, current: (Int, Int, Int) -> Point) {
                for (i in rowRange) {
                    for (j in colRange) {
                        val p = Point(i, j)
                        if (p.value() == 0) continue
                        var w = Point(i, j)
                        for (k in fold(i, j)) {
                            val ww = current(i, j, k)
                            if (!ww.moveable()) break
                            if (!ww.isBlank()
                                && ww.value() != p.value()) break
                            if (exclude.contains(ww)) break
                            w = ww
                        }
                        if (w != p && w.value() == p.value()) {
                            exclude.add(w)
                            copy.merge(w, p)
                        } else if (w.isBlank()) {
                            copy.swap(w, p)
                        }
                    }
                }
            }
            if (d == Direction.LEFT) {
                val rowRange = 0 until N
                val colRange = 0 until N
                val foldRange = { i: Int, j: Int -> j-1 downTo 0 }
                val point = { i: Int, j: Int, k: Int -> Point(i, k) }
                merge(rowRange, colRange, foldRange, point)
            }
            if (d == Direction.RIGHT) {
                val rowRange = 0 until N
                val colRange = N-1 downTo 0
                val foldRange = { i: Int, j: Int -> j+1 until N }
                val point = { i: Int, j: Int, k: Int -> Point(i, k) }
                merge(rowRange, colRange, foldRange, point)
            }
            if (d == Direction.UP) {
                val rowRange = 0 until N
                val colRange = 0 until N
                val foldRange = { i: Int, j: Int -> i-1 downTo 0}
                val point = { i: Int, j: Int, k: Int -> Point(k, j) }
                merge(rowRange, colRange, foldRange, point)
            }
            if (d == Direction.DOWN) {
                val rowRange = N-1 downTo 0
                val colRange =N-1 downTo 0
                val foldRange = { i: Int, j: Int -> i+1 until N}
                val point = { i: Int, j: Int, k: Int -> Point(k, j) }
                merge(rowRange, colRange, foldRange, point)
            }
            max = max.coerceAtLeast(go(cnt+1, copy))
        }
        return max
    }
    println(go(0, A))
}