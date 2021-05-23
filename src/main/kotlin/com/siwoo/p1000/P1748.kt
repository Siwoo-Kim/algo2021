package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0

    tailrec fun go(start: Int, times: Int, cnt: Long): Long {
        if (start > N) return cnt
        var end = start * 10 - 1
        if (end > N) end = N
        return go(start*10, times+1, cnt + ((end-start+1) * times))
    }
    println(go(1, 1, 0))
}