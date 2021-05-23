package com.siwoo.p3000

data class Pair(val row: Int, val col: Int)

fun main(args: Array<String>) {
    val N = readLine()?.toInt() ?: 0
    val M = Array(N){ CharArray(N) }
    for (i in 0 until N)
        M[i] = readLine()?.toCharArray() ?: CharArray(N)
    
    var max = 1
    val nextPos: (Pair) -> Pair = {
        if (it.row + 1 == N) Pair(0, it.col+1) 
        else Pair(it.row+1, it.col)
    }

    fun valid(p: Pair): Boolean = p.row in 0 until N && p.col in 0 until N
    
    val adj: (Pair) -> List<Pair> = {
        listOf(Pair(it.row+1, it.col),
            Pair(it.row, it.col+1))
            .filter(::valid)
    }

    fun swap(p1: Pair, p2: Pair, m: Array<CharArray>) {
        val tmp = m[p1.row][p1.col]
        m[p1.row][p1.col] = m[p2.row][p2.col]
        m[p2.row][p2.col] = tmp
    }

    fun count(m: Array<CharArray>): Int {
        var max = 1
        for (i in 0 until N) {
            var cnt = 1
            for (j in 1 until N) {
                if (m[i][j-1] == m[i][j])
                    cnt++
                else
                    cnt = 1
                max = Math.max(max, cnt)
            }
            cnt = 1
            for (j in 1 until N) {
                if (m[j-1][i] == m[j][i])
                    cnt++
                else
                    cnt = 1
                max = Math.max(max, cnt)
            }
        }
        return max
    }

    tailrec fun traverse(pos: Pair) {
        if (pos.row+1 == N && pos.col+1 == N) return
        for (p in adj(pos)) {
            swap(pos, p, M)
            max = Math.max(max, count(M))
            swap(pos, p, M)
        }
        traverse(nextPos(pos))
    }
    traverse(Pair(0, 0))
    println(max)
}