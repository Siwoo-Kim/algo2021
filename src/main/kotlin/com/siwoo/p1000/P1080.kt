package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int, split: String ->
        if (split == "") readLine()!!.toCharArray().map { it - '0' }.toIntArray()
        else {
            val tokens = StringTokenizer(readLine()!!)
            IntArray(N) { tokens.nextToken().toInt() }
        }
    }
    data class Point(val x: Int, val y: Int)
    val (N, M) = readInts(2, " ")
    val A = Array(N) { readInts(M, "") }
    val B = Array(N) { readInts(M, "") }
    fun rangeBounded(small: IntRange, big: IntRange) 
        = small.last <= big.last && small.first >= big.first
    val xrange = 0 until N
    val yrange = 0 until M
    fun conv(p: Point, A: Array<IntArray>): Boolean {
        val xr = p.x .. p.x+2
        val yr = p.y .. p.y+2
        if (!rangeBounded(xr, xrange) 
            || !rangeBounded(yr, yrange)) return false
        for (x in xr)
            for (y in yr)
                A[x][y] = if (A[x][y] == 0) 1 else 0
        return true
    }
    fun go(): Int {
        var cnt = 0
        for (x in xrange)
            for (y in yrange) {
                if (A[x][y] != B[x][y]) {
                    conv(Point(x, y), A)
                    cnt++
                }
            }
        return if (A.contentDeepEquals(B)) cnt else -1
    }
    print(go())
}
