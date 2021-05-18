package com.siwoo.p11000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val list = readLine()?.split(" ")
            ?.map { e -> e.toInt() }
            ?.toList() 
            ?: listOf()
    val dp: Array<Int> = Array(N) { -1 }
    
    fun lis(n: Int): Int {
        if (n < 0) return 0
        if (dp[n] != -1) return dp[n]
        var max = 1
        for (i in 0 until n) {
            if (list[n] > list[i])
                max = Math.max(dp[i]+1, max)
        }
        dp[n] = max
        return dp[n]
    }
 
    println(list.indices.map { i -> lis(i) }.max())
}