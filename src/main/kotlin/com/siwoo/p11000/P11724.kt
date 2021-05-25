package com.siwoo.p11000

fun main(args: Array<String>) {
    data class Edge(val v: Int, val w: Int) {
        constructor(p: Pair<Int, Int>): this(p.first, p.second)
        fun reverse(): Edge = Edge(w, v)
    }

    fun <T> List<T>.toPair() = Pair(this[0], this[1])
    val readPair = { readLine()!!.split(" ").map { it.toInt() }.toPair() }
    val (N, M) = readPair()
    val graph = mutableMapOf<Int, MutableSet<Edge>>()
    (1 .. N).forEach { graph[it] = mutableSetOf() }
    (0 until M).forEach { 
        val e = Edge(readPair())
        graph[e.v]?.add(e)
        graph[e.w]?.add(e.reverse())
    }
    fun cc(graph: Map<Int, MutableSet<Edge>>): Int {
        val visit = mutableSetOf<Int>()
        fun dfs(v: Int) {
            visit.add(v)
            for (e in graph[v]!!)
                if (!visit.contains(e.w))
                    dfs(e.w)
        }
        var cnt = 0
        for (e in graph) {
            if (!visit.contains(e.key)) {
                dfs(e.key)
                cnt++
            }
        }
        return cnt
    }
    println(cc(graph))
}