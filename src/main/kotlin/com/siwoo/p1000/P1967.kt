package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine() ?: "")
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    data class Edge(val v: Int, val w: Int, val weight: Int) {
        constructor(vararg args: Int): this(args[0], args[1], args[2])
        fun reverse() = Edge(w, v, weight)
    }
    val graph by lazy {
        val g = mutableMapOf<Int, MutableSet<Edge>>()
        (1 .. N-1).forEach { 
            val edge = Edge(*readInts(3))
            g.putIfAbsent(edge.v, mutableSetOf())
            g.putIfAbsent(edge.w, mutableSetOf())
            g[edge.v]?.add(edge)
            g[edge.w]?.add(edge.reverse())
        }
        g
    }
    fun farthest(root: Int): Pair<Int, Int> {
        val q = mutableListOf(root)
        val distance = mutableMapOf(root to 0)
        while (q.isNotEmpty()) {
            val v = q.removeAt(0)
            val d = distance[v] ?: 0
            graph[v]?.filter { !distance.contains(it.w) }
                    ?.forEach { 
                        distance[it.w] = d + it.weight
                        q.add(it.w)
                    }
        }
        return distance.entries.reduce {acc, e -> if (acc.value < e.value) e else acc }.toPair()
    }
    println(farthest(farthest(1).first).second)
}