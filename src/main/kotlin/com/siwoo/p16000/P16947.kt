package com.siwoo.p16000

import java.util.*

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    data class Edge(val v: Int, val w: Int) {
        fun reverse(): Edge = Edge(w, v)
    }
    fun readEdge(): Edge {
        val s = readLine()!!.split(" ").map(String::toInt)
        return Edge(s[0], s[1])
    }
    val graph = mutableMapOf<Int, MutableSet<Edge>>()
    (1 .. N).forEach { graph[it] = mutableSetOf() }
    (1 .. N).forEach { 
        val e = readEdge()
        graph[e.v]!!.add(e)
        graph[e.w]!!.add(e.reverse())
    }
    val set = mutableSetOf<Int>()
    fun cycle(p: Int, k: Int, stack: Stack<Int>): Set<Int>? {
        if (stack.contains(p)) {
            val cycle = mutableSetOf<Int>()
            while (stack.peek() != p)
                cycle.add(stack.pop())
            cycle.add(p)
            return cycle
        }
        set.add(p)
        stack.add(p)
        for (e in graph[p]!!) {
            if (e.w == k) continue
            val cycle = cycle(e.w, e.v, stack)
            if (cycle != null)
                return cycle
        }
        stack.pop()
        return null
    }
    
    fun getCycle(): Set<Int> {
        (1 .. N).forEach {
            if (!set.contains(it)) {
                val c = cycle(it, it, Stack())
                if (c != null) return c
            }
        }
        return emptySet()
    }
    
    val cycle by lazy { getCycle() }
    
    fun bfs(q: MutableList<Int>): String {
        val distance = mutableMapOf<Int, Int>()
        for (v in q)
            distance.put(v, 0)
        while (q.isNotEmpty()) {
            val v = q.removeAt(0)
            for (e in graph[v]!!) {
                if (!distance.contains(e.w)) {
                    distance[e.w] = distance[e.v]!! + 1
                    q.add(e.w)
                }
            }
        }
        return (1 .. N).map { distance[it] }.joinToString(separator = " ")
    }
    println(bfs(cycle.toMutableList()))
}