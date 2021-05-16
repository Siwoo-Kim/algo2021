package com.siwoo.p11000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val dp: Array<Int> = Array(N+1, { -1 })
    
    fun tile(n: Int): Int {
        if (n == 0) return 1
        if (dp[n] != -1)
            return dp[n]
        var sum = tile(n-1)
        if (n - 2 >= 0)
            sum += tile(n - 2)
        dp[n] = sum
        return dp[n]
    }
    println(tile(N))
}

