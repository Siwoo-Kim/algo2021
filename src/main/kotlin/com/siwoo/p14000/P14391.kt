package com.siwoo.p14000

fun main(args: Array<String>) {
    fun <T> List<T>.toPair(): Pair<T, T> = Pair(this[0], this[1])
    val (N, M) = readLine()
        ?.split(" ")
        ?.map{ it.toInt() }
        ?.toPair() ?: Pair(0, 0)
    val a = Array(N) {
        readLine()?.split("")
            ?.filter { it.isNotBlank() }
            ?.map { it.toInt() }
            ?.toIntArray() ?: IntArray(M)
    }
    var max = Int.MIN_VALUE
    for (set in 0 until (1 shl N*M)) {
        var sum = 0
        for (i in 0 until N) {
            var inc = 0
            for (j in 0 until M) {
                if (set and (1 shl (i * M + j)) != 0) {
                    inc = inc * 10 + a[i][j]
                } else {
                    sum += inc
                    inc = 0
                }
            }
            sum += inc
        }
        for (j in 0 until M) {
            var inc = 0
            for (i in 0 until N) {
                if (set and (1 shl (i * M + j)) == 0) {
                    inc = inc * 10 + a[i][j]
                } else {
                    sum += inc
                    inc = 0
                }
            }
            sum += inc
        }
        max = Math.max(max, sum)
    }
    println(max)
}