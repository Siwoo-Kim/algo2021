package com.siwoo.p13000

import java.util.*

fun main(args: Array<String>) {
    fun readInts(n: Int): IntArray {
        val tokens = StringTokenizer(readLine() ?: "")
        return IntArray(n) { tokens.nextToken().toInt() }
    }
    data class Position(val x: Int) {
        fun adj(p: (Position) -> Boolean) = 
                arrayOf(Position(x+1), 
                        Position(x-1), 
                        Position(2*x))
                        .filter(p)
    }
    val A = readInts(2)
    val (N, M) = A[0] to A[1]
    val deque = LinkedList<Position>()
    deque.add(Position(N))
    val range = 0 .. 100000
    val distance = mutableMapOf(deque.first to 0)
    while (deque.isNotEmpty()) {
        val v = deque.poll()
        if (v.x == M) {
            println(distance[v])
            return
        }
        v.adj { it.x in range && distance[it] == null }
                .forEach { 
                    if (it.x == 2*v.x) {
                        deque.addFirst(it)
                        distance[it] = distance[v]!!
                    } else {
                        deque.addLast(it)
                        distance[it] = distance[v]!! + 1
                    }
                }
    }
}