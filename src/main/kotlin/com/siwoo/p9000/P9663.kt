package com.siwoo.p9000

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val C = Array(N) { Array(N) { false } }
    val rows = Array(N) { false }
    val cols = Array(N) { false }
    val diag = Array(N*2+1) { false }
    val rdiag = Array(N*2+1) { false }
    
    fun check(row: Int, col: Int): Boolean {
        return !rows[row] && !cols[col] && !diag[row+col] && !rdiag[row-col+(N-1)]
    }

    fun visit(row: Int, col: Int, b: Boolean) {
        rows[row] = b
        cols[col] = b
        diag[row+col] = b
        rdiag[row-col+(N-1)] = b
    }

    fun queen(row: Int): Int {
        if (row == N) return 1
        var cnt = 0
        for (col in 0 until N) {
            if (check(row, col)) {
                visit(row, col, true)
                cnt += queen(row+1)
                visit(row, col, false)
            }
        }
        return cnt
    }
    println(queen(0))
}