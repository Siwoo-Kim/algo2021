package com.siwoo.p1000

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

private data class Edge(val v: Int, val w: Int) {
    constructor(p: Pair<Int, Int>): this(p.first, p.second)
    fun reverse(): Edge = Edge(w, v)
}

fun main(args: Array<String>) {
    fun <T> List<T>.toTriple() = Triple(this[0], this[1], this[2])
    fun <T> List<T>.toPair() = Pair(this[0], this[1])
    val readIntList = { readLine()!!.split(" ").map{ it.toInt() } }
    val (N, M, V) = readIntList().toTriple()
    
    val graph = HashMap<Int, MutableSet<Edge>>()
    (1 .. N).forEach { graph[it] = HashSet() }
    (0 until M).forEach { 
        val e = Edge(readIntList().toPair())
        graph[e.v]?.add(e)
        graph[e.w]?.add(e.reverse())
    }
    val visit = mutableSetOf<Int>()
    fun dfs(v: Int, s: String): String {
        visit.add(v)
        var ss = if (s.isEmpty()) "$v" else "$s $v"
        for (e in graph[v]!!.sortedBy { it.w }) {
            if (!visit.contains(e.w)) {
                ss = dfs(e.w, ss)
            }
        }
        return ss
    }
    println(dfs(V, ""))
    
    fun bfs(k: Int): String {
        val queue = LinkedList<Int>()
        queue.add(k)
        val visit = mutableSetOf<Int>()
        visit.add(k)
        var s = ""
        while (!queue.isEmpty()) {
            val v = queue.poll()
            s = if (s.isEmpty()) "$v" else "$s $v"
            for (e in graph[v]!!.sortedBy { it.w }) {
                if (!visit.contains(e.w)) {
                    queue.add(e.w)
                    visit.add(e.w)
                }
            }
        }
        return s
    }
    println(bfs(V))
}