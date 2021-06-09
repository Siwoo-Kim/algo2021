package com.siwoo.p10000

class UF<V> {
    private val components = mutableMapOf<V, V>()
    private val sizes = mutableMapOf<V, Int>()
    public var size = 0

    operator fun plusAssign(v: V) = run {
        if (components[v] == null) {
            components[v] = v
            sizes[v] = 1
            size++
        }
    }

    private fun connected(v: V, w: V) = get(v) == get(w)

    fun union(v: V, w: V) {
        if (connected(v, w)) return
        var v = get(v)
        var w = get(w)
        if (sizes[v]!! < sizes[w]!!) {
            val tmp = v
            v = w
            w = tmp
        }
        components[w] = v
        sizes[v] = sizes[v]!! + sizes[w]!!
        size--
    }

    private fun get(v: V): V = components[v]?.let{
        if (components[v] == v)
            return v
        components[v] = get(components[v]!!)
        components[v]
    } ?: throw IllegalStateException()

    override fun toString(): String =
        components.map { get(it.key) to it.key }
            .groupBy { it.first }
            .toList().joinToString("\n")
    
}

private data class Point(val x: Int, val y: Int) {
    fun adj(p: (Point) -> Boolean) = arrayOf(
        Point(x, y-1), Point(x, y+1),
        Point(x-1, y), Point(x+1, y)).filter(p)
}

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    val A = Array(N) { readLine()!! }
    fun Point.valid() = x in 0 until N && y in 0 until N
    val cc = mutableMapOf(
        'R' to mutableSetOf('R'),
        'G' to mutableSetOf('G'),
        'B' to mutableSetOf('B'))
    
    fun countComponents(cc: Map<Char, Set<Char>>): Int {
        val uf = UF<Point>()
        for (r in A.indices)
            for (c in A[r].indices) {
                with(Point(r, c)) {
                    uf += this
                    for (w in adj {
                        it.valid() && cc[A[x][y]]?.contains(A[it.x][it.y]) ?: false
                    }) {
                        uf += w
                        uf.union(this, w)
                    }
                }
            }
        return uf.size
    }
    println(countComponents(cc))
    cc['R']?.add('G')
    cc['G']?.add('R')
    println(countComponents(cc))
}