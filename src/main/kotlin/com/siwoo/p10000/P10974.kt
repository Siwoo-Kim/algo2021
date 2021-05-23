package com.siwoo.p10000

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun nextPerm(a: MutableList<Int>): Boolean {
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
    while (i < j) swap(i++, j--)
    return true
}
val out = BufferedWriter(OutputStreamWriter(System.out))
fun main(args: Array<String>) {
    val a by lazy {
        val N = readLine()?.toInt() ?: 0
        (1 .. N).toMutableList()
    }
    do {
        out.write(a.joinToString(" ", postfix = "\n"))
    } while (nextPerm(a))
    out.flush()
}