package com.siwoo.p14000

import java.util.*

fun main(args: Array<String>) {
    val range = 1..20*100000+1
    val readInts = { N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = readInts(N)
    val sums = (0 until (1 shl N)).map { set ->
        (0 until N).filter { (set and (1 shl it)) != 0 }
            .map { A[it] }
            .sum()
    }.toSet()
    println(range.find { !sums.contains(it) })
}