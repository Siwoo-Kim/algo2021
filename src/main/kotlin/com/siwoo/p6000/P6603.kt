package com.siwoo.p6000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

private val out = BufferedWriter(OutputStreamWriter(System.out))

fun main(args: Array<String>) {
    fun play(k: IntArray) {
        val select = IntArray(k.size) { if (k.size - 6 <= it) 1 else  0 }
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
        val stack = Stack<String>()
        do {
            val s = k.withIndex().filter { e -> select[e.index] == 1 }
                .map { e -> e.value }
                .fold("") { acc, i -> if (acc.isEmpty()) "$i" else "$acc $i" }
            stack.push(s)
        } while (nextPerm(select))
        while (!stack.isEmpty())
            out.write(stack.pop() + "\n")
        out.write("\n")
        out.flush()
    }
    
    fun StringTokenizer.nextInt() = this.nextToken().toInt()
    while (true) {
        val tokens = StringTokenizer(readLine())
        val N = tokens.nextInt()
        if (N == 0) return
        val k = IntArray(N) { tokens.nextInt() }
        play(k)
    }
}