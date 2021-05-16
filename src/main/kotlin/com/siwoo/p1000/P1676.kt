package com.siwoo.p1000

fun main(args: Array<String>) {
    println(factorial(1, readLine()?.toInt() ?: 0))
}

fun factorial(i: Int, N: Int): Int {
    if (i > N) return 0
    var cnt = 0
    var j = i
    while (j % 5 == 0) {
        cnt++
        j /= 5
    }
    return factorial(i+1, N) + cnt
}
