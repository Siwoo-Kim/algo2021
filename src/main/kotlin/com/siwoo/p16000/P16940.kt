package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    data class Edge(val v: Int, val w: Int) {
        fun reverse(): Edge = Edge(w, v)
    }
    fun readInts(n: Int): IntArray {
        val s = StringTokenizer(readLine() ?: "")
        return IntArray(n) { s.nextToken().toInt() }
    }
    
    val g = mutableMapOf<Int, MutableSet<Edge>>()
    (1 .. N-1).forEach {
        val a = readInts(2)
        val e = Edge(a[0], a[1])
        g.putIfAbsent(e.v, mutableSetOf())
        g.putIfAbsent(e.w, mutableSetOf())
        g[e.v]?.add(e)
        g[e.w]?.add(e.reverse())
    }
    val orders = readInts(N)
    
    fun checkOrder(orders: IntArray): Boolean {
        if (orders[0] != 1) return false
        var next = 1
        val q = mutableListOf<Int>()
        q.add(orders[0])
        val visit = mutableSetOf<Int>()
        visit.add(orders[0])
        val parent = mutableMapOf<Int, Int>()
        for (i in 0 until N) {
            if (q.isEmpty()) return false
            val v = q.removeAt(0)
            if (orders[i] != v) return false
            var cnt = 0
            for (e in g[v]!!) {
                if (!visit.contains(e.w)) {
                    parent[e.w] = v
                    cnt++
                }
            }
            for (j in 0 until cnt) {
                if (j+next >= N || parent[orders[next+j]] != v)
                    return false
                q.add(orders[next+j])
                visit.add(orders[next+j])
            }
            next+=cnt
        }
        return true
    }
    println(if (checkOrder(orders)) 1 else 0)
}