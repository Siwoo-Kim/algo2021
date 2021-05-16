package com.siwoo.p10000

fun main(args: Array<String>) {
    val N = 100
    val MOD = 1000000000L
    val dp: Array<Array<Long>> = Array(N+1) { Array(10) { 0L } }
    
    for (i in 1..9)
        dp[1][i] = 1
    
    tailrec fun ladderNum(n: Int) {
        if (n > N) return
        for (i in 0..9) {
            if (i - 1 >= 0)
                dp[n][i] += dp[n-1][i-1]
            if (i + 1 <= 9)
                dp[n][i] += dp[n-1][i+1]
            dp[n][i] %= MOD
        }
        ladderNum(n+1)
    }
    ladderNum(2)
    println(dp[readLine()?.toInt() ?: 0].fold(0L){ a, b -> a + b % MOD })
}