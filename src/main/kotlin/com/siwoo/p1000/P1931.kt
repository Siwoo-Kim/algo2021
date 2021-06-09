package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    data class Meeting(val start: Int, val end: Int) {
        constructor(vararg ints: Int): this(ints[0], ints[1])
    }
    val (N) = readInts(1)
    val meetings = (1..N).map { Meeting(*readInts(2)) }
        .sortedWith(compareBy(Meeting::end, Meeting::start))
    var min = Meeting(0, 0)
    var cnt = 0
    for (m in meetings) {
        if (min.end <= m.start) {
            min = m
            cnt++
        }
    }
    println(cnt)
}