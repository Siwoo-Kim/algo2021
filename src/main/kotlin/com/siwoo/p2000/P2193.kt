package com.siwoo.p2000

fun main(args: Array<String>) {
    val N = 90
    val dp: Array<Array<Long>> = Array(N+1) { Array(2) { 0L } }
    dp[1][1] = 1
    fun pinaryNum(n: Int) {
        if (n > N) return
        dp[n][0] = dp[n-1][1] + dp[n-1][0]
        dp[n][1] = dp[n-1][0]
        pinaryNum(n+1)
    }
    pinaryNum(2)
    val x = readLine()?.toInt() ?: 0
    println(dp[x][0] + dp[x][1])
}