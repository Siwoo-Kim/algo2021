package com.siwoo.p11000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int ->
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = readInts(N).apply { sort() }
    var sum = 0
    A.fold(0) { acc, i -> 
        sum += acc + i
        acc + i
    }
    println(sum)
}