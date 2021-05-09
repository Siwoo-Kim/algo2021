package com.siwoo.p9000

import java.io.BufferedInputStream
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(BufferedInputStream(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = Integer.parseInt(scanner.nextLine())
    val stack  = java.util.Stack<Char>()
    for (i in 0 until n) {
        val s = scanner.nextLine()
        for (i in s.indices) {
            if (s[i] != ' ')
                stack.push(s[i])
            if (s[i] == ' ' || i == s.length-1) {
                while (!stack.isEmpty())
                    writer.write(stack.pop().toString())
                if (s[i] == ' ')
                    writer.write(s[i].toString())
            }
        }
        writer.write("\n")
    }
    writer.flush()
}