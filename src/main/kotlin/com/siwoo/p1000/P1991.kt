package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val tokens = StringTokenizer(readLine() ?: "")
        Array(N) { tokens.nextToken()[0] }
    }
    data class Node(val x: Char, var left: Node? = null, var right: Node? = null)
    val N = readLine()?.toInt() ?: 0
    val tree = mutableListOf(Node('0'))
    fun toIndex(c: Char) = (c - 'A') + 1
    fun toChar(index: Int) = 'A' + (index-1)
    (1 .. N).forEach { tree.add(Node(toChar(it))) }
    
    (0 until N).forEach {
        val a = readInts(3)
        val root = toIndex(a[0])
        tree[root].left = if (a[1] == '.') null else tree[toIndex(a[1])]
        tree[root].right = if (a[2] == '.') null else tree[toIndex(a[2])]
    }
    fun pre(root: Node?, s: String): String {
        if (root == null) return s
        var r = "$s${root.x}"
        r = pre(root.left, r)
        r = pre(root.right, r)
        return r
    }

    fun ino(root: Node?, s: String): String {
        if (root == null) return s
        var r = ino(root.left, s)
        r = "$r${root.x}"
        r = ino(root.right, r)
        return r
    }
    
    fun post(root: Node?, s: String): String {
        if (root == null) return s
        var r = post(root.left, s)
        r = post(root.right, r)
        r = "$r${root.x}"
        return r
    }
    println(pre(tree[1], ""))
    println(ino(tree[1], ""))
    println(post(tree[1], ""))
}