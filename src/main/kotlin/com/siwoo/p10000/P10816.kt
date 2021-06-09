package com.siwoo.p10000

import java.io.BufferedInputStream
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) { //time exceeds
    val out = BufferedWriter(OutputStreamWriter(System.out))
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    val A = readInts(N)
    A.sort()
    val (M) = readInts(1)
    val scanner = Scanner(BufferedInputStream(System.`in`))
    
    fun lowerBound(a: IntArray, k: Int): Int {
        tailrec fun lowerBound(left: Int, right: Int): Int {
            if (left > right) return left
            val mid = (left + right) ushr 1
            if (a[mid] > k) return lowerBound(left, mid-1)
            else if (a[mid] < k) return lowerBound(mid+1, right)
            else return lowerBound(left, right-1)
        }
        return lowerBound(0, a.size-1)
    }
    fun upperBound(a: IntArray, k: Int): Int {
        tailrec fun upperBound(left: Int, right: Int): Int {
            if (left > right) return left
            val mid = (left + right) ushr 1
            if (a[mid] > k) return upperBound(left, mid-1)
            else if (a[mid] < k) return upperBound(mid+1, right)
            else return upperBound(left+1, right)
        }
        return upperBound(0, a.size-1)
    }
    (1 .. M) .forEach {
        val e = scanner.nextInt()
        out.write("${upperBound(A, e)-lowerBound(A, e)} ")
    }
    out.flush()
}