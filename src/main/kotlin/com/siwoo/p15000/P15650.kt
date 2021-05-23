package com.siwoo.p15000

import java.util.*
import java.util.stream.Collectors

fun main(args: Array<String>) {
    val s = readLine()?.split(" ") ?: arrayListOf()
    val N = s[0].toInt()
    val M = s[1].toInt()
    fun select(i: Int, stack: Stack<Int>) {
        when {
            (stack.size == M) -> {
                val s = stack.stream().map { it.toString() }.collect(Collectors.joining(" "))
                println(s)
            }
            (i > N) -> return
            else -> {
                stack.push(i)
                select(i+1, stack)
                stack.pop()
                select(i+1, stack)
            }
        }
    }
    select(1, Stack())
}