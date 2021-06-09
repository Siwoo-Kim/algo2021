package com.siwoo.p14000

fun main(args: Array<String>) {
    val (N, M) = readLine()!!.split(" ").map(String::toLong)
    val range = 1L .. 1000000000L
    val q = mutableListOf(N)
    val chars = mutableMapOf(N to '0')
    val parent = mutableMapOf(N to N)
    val ops = mapOf(
        '*' to { it: Long -> it * it },
        '+' to { it: Long -> it + it },
        '-' to { it: Long -> it - it },
        '/' to { it: Long -> if (it == 0L) it else it / it })
    fun go(N: Long, M: Long): String {
        if (N == M) return "0"
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                if (this == M) {
                    fun stack(v: Long): String {
                        if (parent[v] == v) return ""
                        return "${ stack(parent[v]!!)}${chars[v]}"
                    }
                    return stack(this)
                }
                for ((c, op) in ops) {
                    val w = op(this)
                    if (w in range
                        && parent[w] == null) {
                        chars[w] = c
                        parent[w] = this
                        q.add(w)
                    }
                }
            }
        }
        return "-1"
    }
    println(go(N, M))
}