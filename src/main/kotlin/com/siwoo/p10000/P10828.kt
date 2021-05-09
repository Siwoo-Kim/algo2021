package com.siwoo.p10000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(BufferedReader(InputStreamReader(System.`in`)))
    val writer = BufferedWriter(OutputStreamWriter(System.out, StandardCharsets.UTF_8))
    val n = Integer.parseInt(scanner.nextLine())

    val stack = Stack<Any>()
    for (i in 0 until n) {
        val s = scanner.nextLine()
        val command = s.split(" ")[0]
        if ("push" == command)
            stack.push(s.split(" ")[1].toInt())
        else if ("pop" == command)
            writer.write(stack.pop().toString())
        else if ("size" == command)
            writer.write(stack.size.toString())
        else if ("empty" == command)
            writer.write(if (stack.isEmpty()) "1" else "0")
        else
            writer.write(stack.top().toString())
        if ("push" != command)
            writer.write("\n");
    }
    writer.flush()
}

class Stack<T> {

    class Node(var value: Int, var next: Node? = null)

    private var head: Node? = null
    var size = 0

    fun push(v: Int) {
        val node = Node(v)
        node.next = head
        head = node
        size++
    }

    fun pop(): Int {
        if (isEmpty()) return -1
        val v = head?.value ?: -1
        head = head?.next
        size--
        return v
    }

    fun isEmpty(): Boolean {
        return head == null
    }

    fun top(): Int {
        return head?.value ?: -1
    }
}