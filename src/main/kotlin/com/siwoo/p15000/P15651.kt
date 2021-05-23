package com.siwoo.p15000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val s = readLine()?.split(" ") ?: arrayListOf()
    val N = s[0].toInt()
    val M = s[1].toInt()
    val out = BufferedWriter(OutputStreamWriter(System.out))
    fun perm(stack: Stack<String>) {
        if (stack.size == M)
            out.write(stack.joinToString(separator = " ", postfix = "\n"))
        else {
            (1 .. N).forEach { 
                stack.push(it.toString()) 
                perm(stack)
                stack.pop()
            }
        }
    }
    perm(Stack())
    out.flush()
}