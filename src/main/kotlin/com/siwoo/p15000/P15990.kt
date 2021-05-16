package com.siwoo.p15000

fun main(args: Array<String>) {
    val N = 100000
    val MOD: Long = 1000000009
    val dp: Array<Array<Long>> = Array(N+1) { Array(4) { 0L } }
    for (i in 1 .. N) {
        if (i - 1 >= 0) {
            dp[i][1] = dp[i-1][2] + dp[i-1][3]
            if (i == 1)
                dp[i][1] = 1
        }
        if (i - 2 >= 0) {
            dp[i][2] = dp[i-2][1] + dp[i-2][3]
            if (i == 2)
                dp[i][2] = 1
        }
        if (i - 3 >= 0) {
            dp[i][3] = dp[i-3][1] + dp[i-3][2]
            if (i == 3)
                dp[i][3] = 1
        }
        for (j in 1 .. 3)
            dp[i][j] %= MOD
    }
    val T = readLine()?.toInt() ?: 0
    for (i in 1 .. T) {
        val x = readLine()?.toInt() ?: 0
        println((dp[x][1] + dp[x][2] + dp[x][3]) % MOD)
    }
}