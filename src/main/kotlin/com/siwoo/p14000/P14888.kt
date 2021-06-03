package com.siwoo.p14000

import java.util.*

fun main(args: Array<String>) {
    val readInts = { N: Int ->
        val token = StringTokenizer(readLine() ?: "")
        IntArray(N) { token.nextToken().toInt() }
    }
    data class Op(val index: Int): Comparable<Op> {
        fun eval(a: Int, b: Int) = when(index) {
            0 -> a + b
            1 -> a - b
            2 -> a * b
            3 -> a / b
            else -> throw UnsupportedOperationException()
        }
        override fun compareTo(other: Op): Int {
            return this.index.compareTo(other.index)
        }
    }
    val N = readInts(1)[0]
    val A = readInts(N)
    val ops = mutableListOf<Op>()
    for (e in readInts(4).withIndex()) {
        for (j in 0 until e.value)
            ops.add(Op(e.index))
    }
    ops.sort()
    fun <T: Comparable<T>> nextPerm(a: MutableList<T>): Boolean {
        fun <T> MutableList<T>.swap(i: Int, j: Int) {
            val tmp = this[i]
            this[i] = this[j]
            this[j] = tmp
        }
        var i = a.size-1
        while (i>0 && a[i-1]>=a[i]) i--
        if (i == 0) return false
        var j = a.size-1
        while(a[i-1]>=a[j]) j--
        a.swap(i-1, j)
        j = a.size-1
        while (j>i) a.swap(i++, j--)
        return true
    }
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    do {
        var sum = A[0]
        for (i in 0 until N-1)
            sum = ops[i].eval(sum, A[i+1])
        min = min.coerceAtMost(sum)
        max = max.coerceAtLeast(sum)
    } while (nextPerm(ops))
    println(max)
    println(min)
}