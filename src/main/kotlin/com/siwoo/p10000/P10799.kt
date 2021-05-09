package com.siwoo.p10000

import java.util.Stack

fun main(args: Array<String>) {
    val s = readLine() ?: ""
    val stack = Stack<Pair<Char, Int>>()
    var cnt = 0
    for (i in s.indices) {
        if (s[i] == ')') {
            if (stack.peek().second+1 == i)
                cnt += stack.size-1
            else
                cnt++
            stack.pop()
        } else {
            stack.push(Pair(s[i], i))
        }
    }
    println(cnt)
}