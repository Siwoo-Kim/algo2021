package com.siwoo.p6000

fun main(args: Array<String>) {
    val MAX = 1000000
    val primes = Array(MAX+1){ true }
    primes[0] = false
    primes[1] = false
    for (i in 2 .. MAX)
        if (primes[i])
            for (j in i+i .. MAX step +i)
                primes[j] = false
    while (true) {
        val k = readLine()?.toInt() ?: 0
        if (k == 0) return
        for (i in primes.indices)
            if (k-i >= 0 && primes[i] && primes[k-i]) {
                println("$k = $i + ${k-i}")
                break
            }
    }
}