package com.siwoo.p2000

// 0 0 0 0 0 0 0
// + + - exclude
// + + +
// k + + +
//   k + + +
//     k + + +
fun main(args: Array<String>) {
    fun readInts(N: Int) =
        readLine()!!.toCharArray().map { it - '0' }.toIntArray()
    val N = readLine()!!.toInt()
    val A = readInts(N)
    val B = readInts(N)
    fun conv(range: IntRange, source: IntArray) =
       range.filter { it in source.indices }
           .forEach { source[it] = if (source[it]==1) 0 else 1  }
    fun tryMatch(source: IntArray, target: IntArray): Int {
        var cnt = 0
        for (x in 1 until N) {
            if (source[x-1] != target[x-1]) {
                conv(x-1 .. x+1, source)
                cnt++
            }
        }   
        return if (source.contentEquals(target)) cnt else Int.MAX_VALUE
    }
    val left = tryMatch(A.copyOf(), B)
    val right = with(A.copyOf()) {
        conv(0..1, this)
        val x = tryMatch(this, B) 
        if (x == Int.MAX_VALUE) x else x + 1
    }
    val min = left.coerceAtMost(right)
    println(if (min == Int.MAX_VALUE) -1 else min)
}