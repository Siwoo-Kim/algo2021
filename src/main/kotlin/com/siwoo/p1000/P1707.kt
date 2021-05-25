package com.siwoo.p1000

private enum class Color {
    R, B;
    fun other(): Color = when(this) {
        R -> B
        B -> R
    }
}
fun main(args: Array<String>) {
    data class Edge(val v: Int, val w: Int) {
        constructor(p: Pair<Int, Int>): this(p.first, p.second)
        fun reverse() = Edge(w, v)
    }
    fun <T> List<T>.toPair() = this[0] to this[1]
    val readPair = { readLine()!!.split(" ").map { it.toInt() }.toPair() }
    fun createGraph(N: Int, M: Int): Map<Int, Set<Edge>> {
        val graph = mutableMapOf<Int, MutableSet<Edge>>()
        (1 .. N).forEach{ graph[it] = mutableSetOf() }
        (0 until M).forEach{ 
            val e = Edge(readPair())
            graph[e.v]?.add(e)
            graph[e.w]?.add(e.reverse())
        }
        return graph
    }
    fun isBipartite(graph: Map<Int, Set<Edge>>): Boolean {
        val bimap = mutableMapOf<Int, Color>()
        fun dfs(v: Int, color: Color): Boolean {
            bimap[v] = color
            for (e in graph[v]!!) {
                if (!bimap.contains(e.w)) {
                    if (!dfs(e.w, color.other()))
                        return false
                } else if (bimap[e.w] == color)
                    return false
            }
            return true
        }
        for (e in graph)
            if (!bimap.contains(e.key) && !dfs(e.key, Color.R))
                return false
        return true
        
    }
    val T = readLine()!!.toInt()
    (0 until T) .forEach {
        val (N, M) = readPair()
        val answer = if (isBipartite(createGraph(N, M))) "YES" else "NO"
        println(answer)
    }
}