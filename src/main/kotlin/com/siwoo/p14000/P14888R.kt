package com.siwoo.p14000

import java.util.*

private enum class Op(val index: Int) {
    PLUS(0), MINUS(1), MULT(2), DIV(3);
    
    fun eval(a: Int, b: Int) = 
        when(this) {
            PLUS -> a + b
            MINUS -> a - b
            MULT -> a * b
            DIV -> a / b
        }
    
    companion object {
        fun indexOf(index: Int) = values()
            .find { it.index == index } 
            ?: throw IllegalArgumentException()
    }
}

@ExperimentalStdlibApi
fun main(args: Array<String>) {
    val readInts = {N: Int ->
        val tokens = StringTokenizer(readLine() ?: "")
        Array(N) { tokens.nextToken().toInt() }
    }
    val N = readInts(1)[0]
    val A = readInts(N)
    val ops = mutableMapOf<Op, Int>()
    val K = readInts(4)
    K.withIndex().forEach {
        ops += Op.indexOf(it.index) to it.value
    }
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    fun select(i: Int, selected: MutableList<Op>) {
        if (i == N-1) {
            var sum = A[0]
            (0 until N-1).forEach { sum = selected[it].eval(sum, A[it+1]) }
            min = min.coerceAtMost(sum)
            max = max.coerceAtLeast(sum)
            return
        }
        for ((op, cnt) in ops) {
            if (cnt > 0) {
                selected.add(op)
                ops[op] = ops[op]!! - 1
                select(i+1, selected)
                ops[op] = ops[op]!! + 1
                selected.removeLast()
            }
        }
    }
    select(0, mutableListOf())
    println("$max\n$min")
}