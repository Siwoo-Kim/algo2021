package com.siwoo.p14000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val a = readLine()?.split(" ")?.map { it.toInt() }?.toList() ?: listOf()

    val dp: Array<Int> = Array(N) { -1 }
    val map = mutableMapOf<Int, Int>()
    
    fun lis(n: Int): Int {
        if (n < 0) return 0
        if (dp[n] != -1) return dp[n]
        dp[n] = 1
        map[n] = n
        for (i in 0 until n) {
            val candidate = lis(i) + 1
            if (a[i] < a[n] && candidate > dp[n]) {
                dp[n] = candidate
                map[n] = i
            }
        }
        
        return dp[n]
    }

    val p = a.indices.map { Pair(it, lis(it)) }.maxBy { it.second } ?: Pair(0, 0)
 
    fun dfs(p: Int): String {
        if (map[p] == p) return "${a[p]}"
        return dfs(map[p] ?: 0) + " ${a[p]}"
    }
    println(dfs(p.first))
}