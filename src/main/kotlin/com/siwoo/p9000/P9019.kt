package com.siwoo.p9000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

enum class Regi(val c: Char) {
    D('D'), S('S'), L('L'), R('R');
    fun op(n: Int) = when (this) {
        D ->  (2*n) % 10000
        S -> if (n == 0) 9999 else n - 1
        L -> (n / 1000) + (n % 1000)*10
        R -> (n % 10) * 1000 + (n / 10)
    }
}
private val out = BufferedWriter(OutputStreamWriter(System.out))

fun main(args: Array<String>) { // fail
    val readInts = {N: Int -> 
        val tokens = StringTokenizer(readLine()!!)
        IntArray(N) { tokens.nextToken().toInt() }
    }
    val (N) = readInts(1)
    (1..N).forEach { 
        val (A, B) = readInts(2)
        val q = mutableListOf(A)
        val dist = mutableMapOf<Int, Pair<Int, Char?>>(A to (A to null))
        while (q.isNotEmpty()) {
            with(q.removeAt(0)) {
                if (this == B) {
                    var x: Int? = this
                    val sb = StringBuilder()
                    while (dist[x]?.first != x) {
                        sb.append(dist[x]?.second)
                        x = dist[x]?.first
                    }
                    out.write(sb.reverse().append("\n").toString())
                    return@forEach
                }
                for (r in Regi.values()) {
                    val w = r.op(this)
                    if (!dist.contains(w)) {
                        dist[w] = this to r.c
                        q.add(w)
                    }
                }
            }
        }
    }
    out.flush()
}