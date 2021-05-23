package com.siwoo.p2000

import java.util.*

fun main(args: Array<String>) {
    var list: List<Int> = listOf()
    var answer: Iterable<Int>? = null
    val MAX = 7
    (0 until 9).forEach{ list = list + (readLine()?.toInt() ?: 0) }
    list = list.sorted()
    fun select(stack: Stack<Int>, i: Int): Boolean {
        when {
            stack.size == MAX -> {
                if (stack.sum() == 100) {
                    answer = stack
                    return true
                } else return false
            }
            i == list.size -> return false
            else -> {
                stack.push(list[i])
                if (select(stack, i + 1)) return true
                stack.pop()
                if (select(stack, i + 1)) return true
                return false
            }
        }
    }
        
    select(Stack(), 0)
    answer?.forEach { println(it) }
}