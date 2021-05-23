package com.siwoo.p10000

import java.util.*

fun main(args: Array<String>) {
    fun StringTokenizer.nextInt() = this.nextToken().toInt()
    val N = readLine()?.toInt() ?: 0
    val (M, A) = Pair (Array(N) {
        val token = StringTokenizer(readLine()?:"")
        IntArray(N) { token.nextInt() } 
    }, (0 until N).toMutableList())
 
    fun nextPerm(from: Int, a: MutableList<Int>): Boolean {
        fun swap(i: Int, j: Int) {
            val tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
        }
        var i = a.size-1
        while (i > from && a[i-1] >= a[i]) i--
        if (i == from) return false
        var j = a.size-1
        while (a[i-1] >= a[j]) j--
        swap(i-1, j)
        j = a.size-1
        while (i < j) swap(i++, j--)
        return true
    }
    
    fun hasPath(from: Int, to: Int): Boolean = M[from][to] != 0
    
    tailrec fun distanceOf(i: Int, distance: Int, a: List<Int>): Int? {
        val next = if (i == N-1) 0 else i + 1
        if (!hasPath(a[i], a[next])) return null
        return if (next == 0) distance + M[a[i]][a[next]]  
        else distanceOf(i+1, distance + M[a[i]][a[next]], a) 
    }
    var min = Int.MAX_VALUE
    do {
        min = Math.min(min, distanceOf(0, 0, A) ?: Int.MAX_VALUE)
    } while (nextPerm(1, A))
    println(min)
}