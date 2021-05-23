package com.siwoo.p9000

fun main(args: Array<String>) {
    val readInt: () -> Int = { readLine()?.toInt() ?: 0 }
    val N = readInt()
    fun cases(n: Int): Int {
        fun cases_(sum: Int): Int {
            if (sum == n) return 1
            if (sum > n) return 0
            return cases_(sum + 1) + cases_(sum + 2) + cases_(sum + 3)
        }
        return cases_(0);
    }
    (0 until N).forEach {println(cases(readInt())) }
}