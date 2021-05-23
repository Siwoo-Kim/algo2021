package com.siwoo.p10000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val a: Array<Int> by lazy {
        readLine()?.split(" ")?.map { it.toInt() }?.toTypedArray() 
            ?: Array(0){ -1 }
    }
    fun nextPermutation(a: Array<Int>): Boolean {
        fun swap(i: Int, j: Int) {
            val tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
        }
        var i = a.size-1
        while (i > 0 && a[i-1] <= a[i]) i--
        if (i == 0) return false
        var j = a.size-1
        while (j > 0 && a[i-1] <= a[j]) j--
        swap(i-1, j)
        j = a.size-1
        while (j > i) swap(i++, j--)
        return true
    }
    val s = if (nextPermutation(a)) a.joinToString(separator = " ") else "-1"
    print(s)
}