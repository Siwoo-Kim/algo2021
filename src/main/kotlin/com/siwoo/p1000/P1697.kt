package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    fun readPair(): Pair<Int, Int> {
        val token = StringTokenizer(readLine() ?: "")
        return Pair(token.nextToken().toInt(), token.nextToken().toInt())
    }
    val range = 0 .. 100000
    val (N, K) = readPair()
    val q = mutableListOf(N)
    val distance = mutableMapOf(N to 0)
    fun next(x: Int) = mutableListOf(x+1, x-1, 2*x).filter { it in range }
    while (!q.isEmpty()) {
        val v = q.removeAt(0)
        if (v == K) {
            println(distance[v])
            return
        }
        next(v).filter { !distance.contains(it) }.forEach {
            distance[it] = distance[v]!! + 1
            q.add(it)
        }
    }
}