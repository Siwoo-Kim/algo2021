package com.siwoo.p11000

import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.util.*
val out = PrintWriter(OutputStreamWriter(System.out))
fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine() ?: "")
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val N = readInts(1)[0]
    data class Edge(val v: Int, val w: Int) {
        fun reverse() = Edge(w, v)
    }
    val graph by lazy {
        val g = mutableMapOf<Int, MutableSet<Edge>>()
        (1 .. N-1).forEach {
            val (v, w) = readInts(2)
            val edge = Edge(v, w)
            g.putIfAbsent(v, mutableSetOf())
            g.putIfAbsent(w, mutableSetOf())
            g[v]?.add(edge)
            g[w]?.add(edge.reverse())
        }
        g
    }
    val parent = mutableMapOf(1 to 1)
    val q = mutableListOf(1)
    while (q.isNotEmpty()) {
        val v = q.removeAt(0)
        for (e in graph[v]!!) {
            if (!parent.contains(e.w)) {
                parent[e.w] = e.v
                q.add(e.w)
            }
        }
    }
    (2 .. N).forEach { out.write("${parent[it]}\n") }
    out.flush()
}