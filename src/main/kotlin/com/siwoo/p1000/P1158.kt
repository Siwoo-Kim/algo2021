package com.siwoo.p1000

import java.lang.StringBuilder
import java.util.*

fun main(args: Array<String>) {
    val token = StringTokenizer(readLine())
    val N = Integer.parseInt(token.nextToken())
    val K = Integer.parseInt(token.nextToken())
    val queue: Queue<Int> = LinkedList<Int>()
    for (i in 1 .. N)
        queue.offer(i)
    val answer = StringBuilder("<")
    var cnt = 1
    while (!queue.isEmpty()) {
        if (cnt == K) {
            answer.append(queue.poll())
                .append(if (queue.isEmpty()) "" else ", ")
            cnt = 0
        } else {
            queue.add(queue.poll())
        }
        cnt++
    }
    answer.append(">")
    println(answer)
}
