package com.siwoo.p14000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    data class Task(val time: Int, val pay: Int) {
        constructor(vararg ints: Int): this(ints[0], ints[1])
    }
    val (N) = readInts(1)
    val tasks = Array(N) { Task(*readInts(2)) }
    val cache = mutableMapOf<Int, Int>()
    fun select(i: Int): Int {
        if (i == N) return 0
        if (i > N) return 0
        if (cache[i] != null) return cache[i]!!
        with(tasks[i]) {
            cache[i] = (if (i+time <= N) select(i + time) + pay else 0)
                .coerceAtLeast(select(i + 1))
            return cache[i]!!
        }
    }
    println(select(0))
}