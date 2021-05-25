package com.siwoo.p1000

fun main(args: Array<String>) {
    val readIntArray = { readLine()?.split(" ")?.map { it.toInt() }?.toList() ?: arrayListOf()}
    var a = readIntArray()
    var (N, M) = a[0] to a[1]
    a = readIntArray()
    fun sum(set: Int) =
        (0 until N).filter{ set and (1 shl it) != 0}
            .fold(0) { acc, i -> acc + a[i] }
    
    val x = (1 until (1 shl N))
        .map{ if (sum(it) == M) 1 else 0 }
        .sum()
    println(x)
}