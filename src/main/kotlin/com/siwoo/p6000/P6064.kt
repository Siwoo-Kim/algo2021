package com.siwoo.p6000

fun main(args: Array<String>) {
    fun go(m: Int, n: Int, x: Int, y: Int): Int {
        fun equal(k: Int) =  (k % n) == y
        tailrec fun go(k: Int): Int? {
            if (k > (n * m)) return null
            if (equal(k)) return k
            return go(k + m)
        }
        return go(x)?.plus(1) ?: -1
    }
    val N = readLine()?.toInt() ?: 0
    for (i in 0 until N) {
        val s = readLine()?.split(" ")?.map { it.toInt() } ?: arrayListOf()
        println(go(s[0], s[1], s[2]-1, s[3]-1))
    }
}