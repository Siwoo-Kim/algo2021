package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val array = readLine()?.split(" ")?.map { it.toInt() }?.toList() ?: listOf()
    val dp: Array<Int> = Array(N) { -1 }
    
    tailrec fun maxConsecutive(n: Int) {
        when {
            n == N -> return
            n == 0 -> {
                dp[n] = array[n]
                maxConsecutive(n+1)
            }
            else -> {
                val max = Math.max(dp[n-1] + array[n], array[n])
                dp[n] = max
                maxConsecutive(n+1)
            }
        }
    }
    maxConsecutive(0)
    println(dp.max())
}