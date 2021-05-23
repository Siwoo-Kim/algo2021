package com.siwoo.p15000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val s = readLine()?.split(" ") ?: arrayListOf()
    val out = BufferedWriter(OutputStreamWriter(System.out))
    val N = s[0].toInt()
    val M = s[1].toInt()
    
    fun comb(n: Int, stack: Stack<String>) {
        if (stack.size == M)
            out.write(stack.joinToString(separator = " ", postfix = "\n"))
        else {
            (n .. N).forEach {
                stack.push(it.toString())
                comb(it, stack)
                stack.pop()
            }
        }
    }
    comb(1, Stack())
    out.flush()
}