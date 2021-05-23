package com.siwoo.p14000

import java.util.*

fun main(args: Array<String>) {
    fun readIntArray() = readLine()?.split(" ")
        ?.map { it.toInt() }
        ?.toIntArray() ?: IntArray(0)
    val N = readLine()?.toInt() ?: 0
    val ss = Array(N) { readIntArray() }
    fun minimalDiff(ss: Array<IntArray>): Int {
        fun getOther(team: Collection<Int>)=
            (0 until N).filter { !team.contains(it) }.toList()
        fun sum(team: Collection<Int>): Int {
            return team.fold(0) { sum, e ->
                sum + team.filter { it != e }
                    .fold(0) { acc, i -> acc + ss[e][i] }
            }
        }
        fun recur(i: Int, teamA: Stack<Int>): Int {
            if (i == N && teamA.size == N/2) {
                val teamB = getOther(teamA)
                val (a, b) = sum(teamA) to sum(teamB)
                return Math.abs(a - b)
            }
            if (i == N) return Int.MAX_VALUE
            if (teamA.size > N/2) return Int.MAX_VALUE
            teamA.push(i)
            val a = recur(i+1, teamA)
            teamA.pop()
            val b = recur(i+1, teamA)
            return a.coerceAtLeast(b)
        }
        return recur(0, Stack())
    }
    println(minimalDiff(ss))
}