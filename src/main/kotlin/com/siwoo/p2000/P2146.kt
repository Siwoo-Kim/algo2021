package com.siwoo.p2000

import java.lang.IllegalArgumentException
import java.util.*

fun main(args: Array<String>) {
    fun readInts(n: Int): IntArray {
        val tokens = StringTokenizer(readLine() ?: "")
        return IntArray(n) { tokens.nextToken().toInt() }
    }
    val N = readInts(1)[0]
    val A = Array(N) { readInts(N) }
    val uf = UF()
    
    fun Point.next() = 
            if (x == N-1 && y == N-1) null else {
                if (y+1 == N) Point(x+1, 0)
                else Point(x, y+1)
            }
    
    fun Point.adj(p: (Point) -> Boolean): List<Point> = 
            listOf(Point(x+1, y), Point(x-1, y), 
                    Point(x, y+1), Point(x, y-1))
                    .filter(p)
    fun Point.valid() = x in 0 until N && y in 0 until N
    
    tailrec fun traverse(p: Point?) {
        if (p == null) return
        if (A[p.x][p.y] != 0) {
            uf.put(p)
            p.adj { it.valid() && A[it.x][it.y] == 1 }.forEach {
                uf.put(it)
                uf.union(p, it)
            }
        }
        traverse(p.next())
    }
    traverse(Point(0, 0))
    
    if (uf.size == 1) {
        println(0)
        return
    }
    var min = Int.MAX_VALUE
    val q = uf.components.keys.toMutableList()
    val distance = mutableMapOf<Point, Int>()
    for (p in q) distance[p] = 0
    while (q.isNotEmpty()) {
        val v = q.removeAt(0)
        for (w in v.adj { it.valid() && distance.contains(it) && !uf.connected(v, it) }) {
            min = Math.min(min, distance[v]!! + distance[w]!!)
        }
        for (w in v.adj { it.valid() && !distance.contains(it) }) {
            distance[w] = distance[v]!! + 1
            uf.put(w)
            uf.union(v, w)
            q.add(w)
        }
    }
    println(min)
}

private data class Point(val x: Int, val y: Int)

private class UF {
    val components = mutableMapOf<Point, Point>()
    val sizes = mutableMapOf<Point, Int>()
    var size = 0
    
    fun getComponent(p: Point): Point = components[p] ?: throw IllegalArgumentException()
    fun getSize(p: Point) = sizes[p] ?: throw IllegalArgumentException()
    
    fun put(p: Point) = 
        if (components[p] == null) {
            components[p] = p
            sizes[p] = 1
            size++
            true
        } else false

    fun connected(v: Point, w: Point) = get(v) == get(w)
    
    fun get(p: Point): Point {
        if (components[p] == p) return p
        val root = get(getComponent(p))
        components[p] = root
        return root
    }
    
    fun union(v: Point, w: Point) {
        if (connected(v, w)) return
        var pv = get(v)
        var pw = get(w)
        if (getSize(pv) < getSize(pw)) {
            val t = pv
            pv = pw
            pw = t
        }
        components[pw] = pv
        sizes[pv] = getSize(pv) + getSize(pw)
        size--
    }
}