package com.siwoo.p17000

import java.util.*

fun main(args: Array<String>) {
    val N = Integer.parseInt(readLine())
    val MAX = 1000000 + 1
    val cnt = Array(MAX, { 0 })
    val s = readLine() ?: ""
    val a = s.split(" ").map(Integer::parseInt).toIntArray()
    for (v in a)
        cnt[v]++ 
    
    val answer = Array(N, { -1 })
    val stack = Stack<Int>()
    for (i in a.indices) {
        while (!stack.isEmpty() 
            && cnt[a[stack.peek()]] < cnt[a[i]])
            answer[stack.pop()] = a[i]
        stack.push(i)
    }
    println(answer.joinToString(separator = " "))
}