package com.siwoo.p1000

import java.io.*
import java.util.*

fun main(args: Array<String>) {
    val scanner = BufferedReader(InputStreamReader(BufferedInputStream(System.`in`)))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val s = scanner.readLine()
    val N = Integer.parseInt(scanner.readLine())
    val left = Stack<Char>()
    val right = Stack<Char>()
    for (c in s)
        left.push(c)
    for (i in 0 until N) {
        val token = StringTokenizer(scanner.readLine())
        val command = token.nextToken()
        if (command == "L" && !left.isEmpty())
            right.push(left.pop())
        if (command == "D" && !right.isEmpty())
            left.push(right.pop())
        if (command == "B" && !left.isEmpty())
            left.pop()
        if (command == "P")
            left.push(token.nextToken()[0])
    }

    while (!left.isEmpty())
        right.push(left.pop())
    while (!right.isEmpty())
        writer.write(right.pop().toString())
    writer.flush()
}

