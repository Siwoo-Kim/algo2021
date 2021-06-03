package com.siwoo.p1000

import java.util.*

@ExperimentalStdlibApi
fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine())
        Array(N) { tokens.nextToken().toInt() }
    }
    val (N, S) = readInts(2)
    val A = readInts(N)
    fun select(i: Int, selected: MutableList<Int>): Int {
        if (i == N) return if (selected.sum() == S && selected.isNotEmpty()) 1 else 0
        selected.add(A[i])
        var x = select(i+1, selected)
        selected.removeLast()
        x += select(i+1, selected)
        return x
    }
    println(select(0, mutableListOf()))
}