package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val dp: Array<Int> = Array(N+4) { -1 }
    dp[1] = 0
    dp[2] = 1
    dp[3] = 1
    for (i in 4 .. N) {
        var min = dp[i-1]
        if (i % 3 == 0)
            min = Math.min(min, dp[i/3])
        if (i % 2 == 0)
            min = Math.min(min, dp[i/2])
        dp[i] = min + 1
    }
    println(dp[N])
}

