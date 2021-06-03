package com.siwoo.p2000

import java.util.*

fun main(args: Array<String>) {
    fun nextPerm(a: Array<Int>, p: (Int, Int) -> Boolean): Boolean {
        fun <T> Array<T>.swap(i: Int, j: Int) {
            val tmp = this[i]
            this[i] = this[j]
            this[j] = tmp
        }
        var i = a.size-1
        while (i >= 0 && p(a[i-1], a[i])) i--
        if (i == 0) return false
        var j = a.size-1
        while (p(a[i-1], a[j])) j--
        a.swap(i-1, j)
        j = a.size-1
        while (j > i) a.swap(i++, j--)
        return true
    }
    val readStrings = {N: Int -> 
        val tokens = StringTokenizer(readLine() ?: "")
        Array(N) { tokens.nextToken() }
    }
    val N = readStrings(1)[0].toInt()
    val signs = readStrings(N)
    fun check(sign: String, a: Int, b: Int): Boolean = 
            when (sign) {
                "<" -> a < b
                ">" -> a > b
                else -> throw IllegalArgumentException()
            }
    fun ok(a: Array<Int>): Boolean = (0 until N).all { check(signs[it], a[it], a[it+1]) }
    
    fun go(a: Array<Int>, p: (Int, Int) -> Boolean): String {
        do {
            if (ok(a)) {
                return a.joinToString(separator = "")
            }
        } while (nextPerm(a, p))
        return ""
    }
    val desc = (9 downTo 9-N).toList().toTypedArray()
    println(go(desc) { prev, next -> prev <= next })
    val asc = (0..N).toList().toTypedArray()
    println(go(asc) { prev, next -> prev >= next })
}