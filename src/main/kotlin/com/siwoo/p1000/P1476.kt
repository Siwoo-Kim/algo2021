package com.siwoo.p1000

data class ESM(val e: Int, val s: Int, val m: Int)

fun main(args: Array<String>) {
    val a = readLine()?.split(" ")?.map{ it.toInt() } ?: arrayListOf()
    val target = ESM(a[0], a[1], a[2])
    
    val next: (ESM) -> ESM = { 
         ESM(if (it.e == 15) 1 else it.e+1, 
            if (it.s == 28) 1 else it.s+1, 
            if (it.m == 19) 1 else it.m+1)
    } 
    
    tailrec fun nextESM(current: ESM, year: Int): Int {
        if (target == current) return year
        return nextESM(next(current), year+1)
    }
    
    println(nextESM(ESM(1, 1, 1), 1))
}