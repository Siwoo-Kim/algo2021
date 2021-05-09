package com.siwoo.p17000

import java.util.*

fun main(args: Array<String>) {
    fun StringTokenizer.nextInt():Int = Integer.parseInt(this.nextToken())
    
    val N = Integer.parseInt(readLine())
    val tokens = StringTokenizer(readLine())
    val stack = Stack<Pair<Int, Int>>()
    val answer = Array(N, { -1 })
    for (i in 0 until  N) {
        val v = tokens.nextInt()
        while (!stack.isEmpty() && stack.peek().second < v)
            answer[stack.pop().first] = v
        stack.push(Pair(i, v))
    }
    println(answer.joinToString(separator = " "))
}