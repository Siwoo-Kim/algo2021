package com.siwoo.p9000

import java.io.BufferedInputStream
import java.util.*
import java.util.Stack

fun main(args: Array<String>) {
    val scanner = Scanner(BufferedInputStream(System.`in`))
    val n = Integer.parseInt(scanner.nextLine())
    for (i in 0 until n)
        println( if (validParenthesis(scanner.nextLine())) "YES" else "NO")
}

fun validParenthesis(s: String): Boolean {
    val stack: Stack<Char> = Stack()
    for (i in s.indices) {
        if (s[i] == '(')
            stack.push(s[i])
        else {
            if (stack.isEmpty())
                return false
            stack.pop()
        }
    }
    return stack.isEmpty()
}