package com.siwoo.p10000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = readInts(N)
    A.sort()
    val (M) = readInts(1)
    val B = readInts(M)
    fun binarySearch(k: Int): Int {
        tailrec fun binarySearch(left: Int, right: Int):Int? {
            if (right<left) return null
            val mid = (left + right) ushr 1
            if (A[mid] > k) return binarySearch(left, mid-1)
            if (A[mid] < k) return binarySearch(mid+1, right)
            return mid
        }
        with(binarySearch(0, N-1)) {
            return if (this == null) 0 else 1
        }
    }
    println(B.map { binarySearch(it) }.joinToString(" "))
}