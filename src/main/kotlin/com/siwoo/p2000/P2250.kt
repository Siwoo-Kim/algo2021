package com.siwoo.p2000

import java.util.*
import kotlin.Comparator

fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine() ?: "")
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val N = readInts(1)[0]
    data class Node(val x: Int, var left: Node?, var right: Node?)
    val nodes = Array<Node?>(N+1) { null }
    val roots = mutableSetOf<Int>()
    (1 .. N).forEach { 
        nodes[it] = Node(it, null, null) 
        roots.add(it)
    }
    (1 .. N).forEach {
        val a = readInts(3)
        nodes[a[0]]?.left = if (a[1] == -1) null else nodes[a[1]]
        nodes[a[0]]?.right = if (a[2] == -1) null else nodes[a[2]]
        roots.remove(a[1])
        roots.remove(a[2])
    }
    val root = nodes[roots.toList()[0]]!!
    fun areas(root: Node): Map<Int, Pair<Int, Int>> {
        var rows = 0
        val areas = mutableMapOf<Int, Pair<Int, Int>>()
        fun traverse(root: Node?, depth: Int) {
            if (root == null) return
            traverse(root.left, depth+1)
            ++rows
            if (areas[depth] == null)
                areas[depth] = rows to rows
            else {
                val (min, max) = areas[depth]!!
                areas[depth] = Math.min(min, rows) to Math.max(max, rows)
            }
            traverse(root.right, depth+1)
        }
        traverse(root, 1)
        return areas
    }
    val cmp: Comparator<Pair<Int, Int>> = compareBy({ -it.second }, { it.first })
    val (depth, area) = areas(root).map { k -> k.key to k.value.second - k.value.first + 1}
            .reduce { acc, p -> if (cmp.compare(p, acc) < 1) p else acc }
    println("$depth $area")
}