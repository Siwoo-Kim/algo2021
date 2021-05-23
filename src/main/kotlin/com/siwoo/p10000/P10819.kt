package com.siwoo.p10000

import java.util.*


fun main(args: Array<String>) {
    fun StringTokenizer.nextInt() = this.nextToken().toInt()
    val N = readLine()?.toInt() ?: 0
    val a by lazy {
        val token = StringTokenizer(readLine())
        (0 until N).map { token.nextInt() }.sorted().toIntArray()
    }
    
    fun nextPerm(a: IntArray): Boolean {
        fun swap(i: Int, j: Int) {
            val tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
        }
        var i = a.size-1
        while (i > 0 && a[i-1] >= a[i]) i--
        if (i == 0) return false
        var j = a.size-1
        while (a[i-1] >= a[j]) j--
        swap(i-1, j)
        j = a.size-1
        while (j > i) swap(i++, j--)
        return true
    }
    
    fun calc(a: IntArray): Int = a.indices
            .filter { it != a.size-1 }
            .fold(0) {acc, i -> acc + Math.abs(a[i] - a[i+1]) }
    
    var max = 0
    do {
        max = Math.max(calc(a), max)
    } while (nextPerm(a))
    println(max)
}