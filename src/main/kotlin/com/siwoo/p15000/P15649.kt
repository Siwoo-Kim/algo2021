package com.siwoo.p15000

fun main(args: Array<String>) {
    val s = readLine()?.split(" ")?.map { it.toInt() } ?: emptyList()
    val N = s[0]
    val M = s[1]
    val visit = Array(N+1) { false }
    fun permutation(cnt: Int, s: String) {
        if (cnt == M) {
            println(s.trim())
            return
        }
        (1 .. N).filter { !visit[it] }.forEach {
            visit[it] = true
            permutation(cnt+1, "$s $it")
            visit[it] = false
        }
    }
    permutation(0, "")
}