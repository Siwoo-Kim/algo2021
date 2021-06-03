package com.siwoo.p2000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val MAX = 9
    data class Point(val x: Int, val y: Int): Comparable<Point> {
        constructor(id: Int): this(id/MAX, id%MAX)
        val id: Int
            get() = x * MAX + y
        override fun compareTo(other: Point): Int = id.compareTo(other.id)
    }
    val END = Point(MAX, 0)
    val rows = Array(MAX) { Array(MAX+1) { false } }
    val cols = Array(MAX) { Array(MAX+1) { false } }
    val boxes = Array(MAX) { Array(MAX+1) { false } }
    fun visit(p: Point, v: Int, b: Boolean) {
        rows[p.x][v] = b
        cols[p.y][v] = b
        boxes[(p.x/3)*3+(p.y/3)][v] = b
    }
    fun visited(p: Point, v: Int) = with(p) {
            rows[x][v] || cols[y][v] ||
            boxes[(x / 3) * 3 + (y / 3)][v]
    }
    
    val S = Array(MAX) { r ->
        val token = StringTokenizer(readLine() ?: return)
        IntArray(MAX) { c -> 
            val v = token.nextToken().toInt() 
            if (v != 0)
                visit(Point(r, c), v, true)
            v
        }
    }
    
    fun sudoku(p: Point): Boolean {
        with(p) {
            if (this >= END) return true
            if (S[x][y] != 0) return sudoku(Point(id + 1))
            else {
                for (i in 1..MAX) {
                    if (!visited(this, i)) {
                        visit(this, i, true)
                        S[x][y] = i
                        if (sudoku(Point(id + 1))) return true
                        S[x][y] = 0
                        visit(this, i, false)
                    }
                }
                return false
            }
        }
    }
    sudoku(Point(0, 0))
    BufferedWriter(OutputStreamWriter(System.out)).let { w -> 
        S.forEach { w.write(it.joinToString(" ", postfix = "\n")) }
        w.flush()
    }
}