package com.siwoo.p11000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    var (N, K) = readInts(2)
    val stack = Stack<Int>()
    (1..N).map{ readInts(1) }.forEach { stack.push(it[0]) }
    var cnt = 0
    while (K != 0) {
        with (stack.peek()) {
            if (this > K)
                stack.pop()
            else {
                cnt += K / this
                K = K % this
            }
        }
    }
    println(cnt)
}