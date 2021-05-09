package com.siwoo.p1000

import java.io.BufferedInputStream
import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(BufferedInputStream(System.`in`))
    val answer = StringBuilder()
    val N = scanner.nextInt()
    val stack: Stack<Int> = Stack()
    var cur = 1
    var ok = true
    for (i in 0 until N) {
        val v = scanner.nextInt()
        while (stack.isEmpty() || cur <= v) {
            stack.push(cur++)
            answer.append("+\n")
        }
        if (stack.peek() != v) {
            ok = false
            break
        }
        stack.pop()
        answer.append("-\n")
    }
    if (ok)
        println(answer)
    else
        println("NO")
}