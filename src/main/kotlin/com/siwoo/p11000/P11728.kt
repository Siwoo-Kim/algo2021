package com.siwoo.p11000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val out = BufferedWriter(OutputStreamWriter(System.out))
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N, M) = readInts(2)
    val A = readInts(N)
    val B = readInts(M)
  
    var (l, r) = arrayOf(0, 0, 0)
    while (l in A.indices || r in B.indices) {
        if (l !in A.indices)
            out.write("${B[r++]} ")
        else if (r !in B.indices)
            out.write("${A[l++]} ")
        else if (A[l] > B[r])
            out.write("${B[r++]} ")
        else
            out.write("${A[l++]} ")
    }
    out.flush()
}