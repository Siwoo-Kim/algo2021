package com.siwoo.p17000

import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    val answer = StringBuilder()
    val s = readLine() ?: ""
    val stack = Stack<Char>()
    var i = 0
    while (i < s.length) {
        if (s[i] == '<') {
            while (!stack.isEmpty())
                answer.append(stack.pop())
            answer.append(s[i])
            while (s[++i] != '>')
                answer.append(s[i])
            answer.append(s[i++])
        } else if (s[i] == ' '){
            while (!stack.isEmpty())
                answer.append(stack.pop())
            answer.append(s[i++])
        } else
            stack.push(s[i++])
    }
    while (!stack.isEmpty())
        answer.append(stack.pop())
    println(answer)
}