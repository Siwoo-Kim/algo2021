package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val S = Array(N) { readLine() ?: "" }
    var index = 0
    val chars = S.asSequence()
            .flatMap { it.toCharArray().asSequence() }
            .distinct()
            .sorted()
            .map { it to index++ }
            .toMap()
    val scores = (9-chars.size+1 .. 9)
            .toList()
            .toMutableList()

    fun <T : Comparable<T>> nextPerm(a: MutableList<T>): Boolean {
        fun <T> MutableList<T>.swap(i: Int, j: Int) {
            val tmp = this[i]
            this[i] = this[j]
            this[j] = tmp
        }
        var i = a.size-1
        while (i>0 && a[i-1]>=a[i]) i--
        if (i == 0) return false
        var j = a.size-1
        while (a[i-1]>=a[j]) j--
        a.swap(i-1, j)
        j = a.size-1
        while (j>i) a.swap(j--, i++)
        return true
    }
    fun toNum(s: String): Int = s.asSequence()
            .map { scores[chars[it]!!] }
            .fold(0) { acc, i -> acc * 10 + i }
    var max = 0
    do {
        var sum = 0
        for (s in S)
            sum += toNum(s)
        max = max.coerceAtLeast(sum)
    } while (nextPerm(scores))
    println(max)
}
