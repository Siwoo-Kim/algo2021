package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine()!!)
        Array(N) { tokens.nextToken().toInt() }
    }
    val (N, M) = readInts(2)
    val map = (1 .. N+M).map { val a = readInts(2); Pair(a[0], a[1])  }.toMap()
    var q = mutableListOf(1)
    var distance = mutableMapOf(1 to 0)
    while (q.isNotEmpty()) {
        val v = q.removeAt(0)
        if (v == 100) {
            println(distance[v])
            return
        }
        (1..6).map{ it + v }.forEach { 
            val w = if (map[it] != null) map[it]!! else it
            if (distance[w] == null) {
                distance[w] = distance[v]!! + 1
                q.add(w)
            }
        }
    }
}