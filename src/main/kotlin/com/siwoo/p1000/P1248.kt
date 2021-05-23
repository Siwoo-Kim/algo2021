package com.siwoo.p1000

import java.util.*

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val s = readLine() ?: ""
    var k = 0
    var i = 0
    val table = Array(N) {
        val j = i++
        Array(N) { if (it < j) '*' else s[k++] } 
    }
    
    fun find(table: Array<Array<Char>>): String {
        fun check(sign: Char, value: Int): Boolean = 
            when (sign) {
                '-' -> 0 > value
                '+' -> 0 < value
                else -> value == 0
            }
        
        fun check(index: Int, select: List<Int>): Boolean {
            var sum = 0
            for (i in index downTo 0) {
                sum += select[i]
                if (!check(table[i][index], sum))
                    return false
            }
            return true
        }
        
        fun recur(index: Int, select: Stack<Int>): String? {
            if (select.size == N) return select.joinToString(separator = " ")
            (-10 .. 10).forEach {
                select.push(it)
                if (check(index, select)) {
                    val answer = recur(index+1, select)
                    if (answer != null) return answer
                }
                select.pop()
            }
            return null
        }
        return recur(0, Stack()) ?: ""
    }
    println(find(table))
}