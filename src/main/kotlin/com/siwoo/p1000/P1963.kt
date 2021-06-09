package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val MAX = 9999
    val primes = run {
        val array = BooleanArray(MAX+1) { true }
        array[0] = false
        array[1] = false
        for (i in 2..MAX) {
            if (array[i])
                for (j in (i+i..MAX).step(i))
                    array[j] = false
        }
        array
    }
    fun nextNum(n: Int, p: (Int) -> Boolean): List<Int> {
        val result = mutableListOf<Int>()
        var k = n
        var pos = 1
        while (k != 0) {
            val v = (k % 10) * pos
            for (i in 0..9) {
                val x = n - v + (i * pos)
                if (p(x)) result.add(x)
            }
            k /= 10
            pos *= 10
        }
        return result
    }
    val range = 1000 .. MAX
    fun bfs(start: Int, end: Int): Int? {
        val q = mutableListOf(start)
        val dist = mutableMapOf(start to 0)
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                val d = dist[this]!!
                if (this == end) return d
                for (v in nextNum(this) {
                    dist[it] == null &&
                    it != this &&
                    it in range &&
                    primes[it]
                }) {
                    dist[v] = d+1
                    q.add(v)
                }
            }
        }
        return null
    }
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    (1..N).forEach { 
        val (start, end) = readInts(2)
        val k = bfs(start, end)
        println("${if (k == null) "Impossible" else k}")
    }
}