package com.siwoo.p13000

import java.util.*

fun main(args: Array<String>) {
    fun readPair(): Pair<Int, Int> {
        val token = StringTokenizer(readLine() ?: "")
        return token.nextToken().toInt() to token.nextToken().toInt()
    }
    val (N, K) = readPair()
    val range = 0 .. 100000
    val q = mutableListOf(N)
    val parent = mutableMapOf(N to N)
    fun next(x: Int) = 
            arrayListOf(x+1, x-1, x*2)
                    .filter { !parent.contains(it) && it in range } 
    while (q.isNotEmpty()) {
        val v = q.removeAt(0)
        if (v == K) {
            var cnt = 0
            fun stack(v: Int?) {
                if (parent[v] == v) {
                    println(cnt)
                    print("$v ")
                } else {
                    cnt++
                    stack(parent[v])
                    print("$v ")
                }
            }
            stack(v)
            return
        }
        next(v).forEach {parent[it] = v; q.add(it) }
    }
}