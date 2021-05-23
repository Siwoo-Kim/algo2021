package com.siwoo.p14000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val pairs = (0 .. N).map { 
        if (it == 0) Pair(0, 0)
        else {
            val s = readLine()?.split(" ") ?: arrayListOf()
            s[0].toInt() to s[1].toInt()
        }   
    }.toList()
    
    fun schedule(schedule: List<Pair<Int, Int>>): Int {
        fun recur(day: Int, paid: Int): Int {
            if (day > N) return paid
            val (t, p) = schedule[day]
            return Math.max(
                if (day + t <= N+1) recur(day+t, paid+p)
                else Int.MIN_VALUE, 
                recur(day+1, paid))
        }
        return recur(1, 0)
    }
    println(schedule(pairs))
}