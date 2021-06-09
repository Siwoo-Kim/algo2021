package com.siwoo.p1000

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    val A = Array(N) { readLine()!!.toCharArray() }
    
    fun flipVal(r: Int, c: Int) = if (A[r][c] == 'H') 'T' else 'H'
    
    fun go(bitset: Int): Int {
        var cnt = 0
        for (r in A.indices) {
            var tails = 0
            for (c in A[r].indices) {
                val v = if ((bitset and (1 shl c)) != 0)  
                    flipVal(r, c)
                else
                    A[r][c]
                if (v == 'T')
                    tails++
            }
            cnt += tails.coerceAtMost(A[r].size - tails)
        }
        return cnt
    }
    var max = Int.MAX_VALUE
    for (bitset in 0 until (1 shl N)) {
        max = max.coerceAtMost(go(bitset))
    }
    println(max)
}