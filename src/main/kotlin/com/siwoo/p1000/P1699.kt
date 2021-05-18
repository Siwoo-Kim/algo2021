package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val dp: Array<Long> = Array(N+1) { -1L }
    fun squareSum(n: Int): Long = when {
        n == 0 -> 0
        dp[n] != -1L -> dp[n]
        else -> {
            var min = Integer.MAX_VALUE.toLong()
            var i = 1
            while (i * i <= n) {
                min = Math.min(min, squareSum(n - i*i) + 1)
                i++
            }
            dp[n] = min
            min
        }
    }
    println(squareSum(N))
}