package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    fun StringTokenizer.nextInt(): Int = this.nextToken().toInt()
    val MAX = 1000
    val primes = Array(MAX+1){ true }
    primes[0] = false
    primes[1] = false
    for (i in 2 .. MAX) {
        if (primes[i])
            for (j in i+i .. MAX step +i)
                primes[j] = false
    }
    readLine()
    val tokens = StringTokenizer(readLine())
    var cnt = 0;
    while (tokens.hasMoreTokens())
        if (primes[tokens.nextInt()])
            cnt++
    println(cnt)
}

