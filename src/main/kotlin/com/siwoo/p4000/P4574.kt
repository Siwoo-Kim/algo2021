package com.siwoo.p4000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

private val out = BufferedWriter(OutputStreamWriter(System.out))

fun main(args: Array<String>) {
    var cnt = 0
    while (true) {
        val MAX = 9
        data class Point(val x: Int, val y: Int) : Comparable<Point> {
            constructor(s: String) : this(s[0] - 'A', s[1] - '1')
            constructor(id: Int) : this(id / MAX, id % MAX)
            val id: Int get() = x * MAX + y
            fun next(): Point = Point(id + 1)
            fun valid(): Boolean = x in 0 until MAX && y in 0 until MAX
            fun adj() = arrayOf(Point(x, y + 1), Point(x + 1, y))
                    .filter { it.valid() }
            override fun compareTo(other: Point): Int = id.compareTo(other.id)
        }
        val N = readLine()?.toInt()?.let { if (it == 0) return else it } ?: return
        out.write("Puzzle ${++cnt}\n")
        val S = Array(MAX) { IntArray(MAX) { 0 } }
        val rows = Array(MAX) { Array(MAX + 1) { false } }
        val cols = Array(MAX) { Array(MAX + 1) { false } }
        val boxes = Array(MAX) { Array(MAX + 1) { false } }
        val domino = mutableSetOf<Pair<Int, Int>>()
        fun set(v: Int, p: Point) = with(p) { S[x][y] = v }
        fun visit(p: Point, v: Int, b: Boolean) = with(p) {
            rows[x][v] = b
            cols[y][v] = b
            boxes[(x / 3) * 3 + y / 3][v] = b
        }
        (1..N).forEach {
            val (U, LU, V, LV) = readLine()?.split(" ") ?: return
            with(Point(LU)) {
                visit(this, U.toInt(), true)
                set(U.toInt(), this)
            }
            with(Point(LV)) {
                visit(this, V.toInt(), true)
                set(V.toInt(), this)
            }
            domino.add(V.toInt() to U.toInt())
            domino.add(U.toInt() to V.toInt())
        }
        val token = StringTokenizer(readLine() ?: return)
        (1..9).forEach {
            with(Point(token.nextToken())) {
                visit(this, it, true)
                set(it, this)
            }
        }
        fun check(p: Point, v: Int) = with(p) {
            !rows[x][v] && !cols[y][v] && !boxes[(x / 3) * 3 + y / 3][v]
        }
        fun check(i: Int, j: Int) = !domino.contains(i to j) && !domino.contains(j to i)
        val START = Point(0, 0)
        val END = Point(MAX, 0)
        fun sudoku(p: Point): Boolean = with(p) {
            if (this >= END) return true
            if (S[x][y] != 0) sudoku(next())
            else {
                (1..MAX)
                    .filter{ check(this, it) }
                    .forEach { i ->
                        adj().forEach { w ->
                            for (j in 1..MAX) {
                                if (i != j 
                                    && check(w, j)
                                    && check(i, j)
                                    && S[w.x][w.y] == 0) {
                                    visit(p, i, true)
                                    visit(w, j, true)
                                    domino.add(i to j)
                                    domino.add(j to i)
                                    S[x][y] = i
                                    S[w.x][w.y] = j
                                    if (sudoku(p.next())) return true
                                    S[x][y] = 0
                                    S[w.x][w.y] = 0
                                    visit(p, i, false)
                                    visit(w, j, false)
                                    domino.remove(i to j)
                                    domino.remove(j to i)
                                    }
                                }
                            }
                }
                false
            }
        }
        sudoku(START)
        S.forEach { out.write(it.joinToString(separator = "", postfix = "\n")) }
        out.flush()
    }
}