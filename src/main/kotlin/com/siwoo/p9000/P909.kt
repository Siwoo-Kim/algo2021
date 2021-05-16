package com.siwoo.p9000

fun main(args: Array<String>) {
    val dp: Array<Int> = Array(12) { 0 }
    dp[0] = 1
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4
    for (i in 4 until 12) {
        dp[i] += dp[i-1]
        dp[i] += dp[i-2]
        dp[i] += dp[i-3]
    }
    val N = readLine()?.toInt() ?: 0
    for (i in 0 until N)
        println(dp[readLine()?.toInt() ?: 0])
}