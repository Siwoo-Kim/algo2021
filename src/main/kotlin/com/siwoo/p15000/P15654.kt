package com.siwoo.p15000

import java.util.*

fun main(args: Array<String>) {
    fun StringTokenizer.nextInt() = this.nextToken().toInt()
    val s = readLine()?.split(" ") ?: arrayListOf()
    val N = s[0].toInt()
    val M = s[1].toInt()
    val tokens = StringTokenizer(readLine() ?: "")
    val list = mutableListOf<Int>()
    val visit: Array<Boolean> = Array(N) { false }
    (0 until N).forEach{ list.add(tokens.nextInt()) }
    list.sort()
    fun perm(stack: Stack<Int>) {
        if (stack.size == M) {
            println(stack.joinToString(separator = " "))
            return
        }
        (0 until N).filter { !visit[it] }
            .forEach{ 
                visit[it] = true
                stack.push(list[it])
                perm(stack)
                stack.pop()
                visit[it] = false
            }
    }
    perm(Stack())
}