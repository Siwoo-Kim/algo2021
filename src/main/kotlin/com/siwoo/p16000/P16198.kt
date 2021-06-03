package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int -> 
        val tokens = StringTokenizer(readLine() ?: "")
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = readInts(N)
    
    fun go(cnt: Int, score: Long): Long {
        if (cnt == 2) return score
        var max = 0L
        for (i in 1 until N-1) {
            if (A[i] == -1) continue
            val tmp = A[i]
            val left = A.withIndex().findLast{ it.index < i && it.value != -1 } 
                ?: throw IllegalArgumentException()
            val right = A.withIndex().find { it.index > i && it.value != -1 } 
                ?: throw IllegalArgumentException()
            A[i] = -1
            max = max.coerceAtLeast(go(cnt - 1, score + left.value * right.value))
            A[i] = tmp
        }
        return max
    }
    println(go(N, 0))
}