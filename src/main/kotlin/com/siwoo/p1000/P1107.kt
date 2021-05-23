package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val K = readLine()?.toInt() ?: 0
    val MAX = 500000
    val brokens = readLine()?.split(" ")?.map { it.toInt() }?.toSet() ?: setOf()

    fun canGo(channel: Int): Boolean {
        for (c in channel.toString().toCharArray())
            if (brokens.contains(c - '0'))
                return false
        return true
    }

    fun stepsFrom(from: Int): Int = Math.abs(N - from) + from.toString().chars().map { 1 }.sum()
    
    val min = Math.abs(N-100)
    val candidate: Int? = (0..MAX*2).filter { canGo(it) }.map { stepsFrom(it) }.min()
    println(Math.min(min, candidate ?: Int.MAX_VALUE))
}