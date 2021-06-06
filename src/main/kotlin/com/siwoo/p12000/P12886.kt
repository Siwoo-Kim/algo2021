package com.siwoo.p12000

fun main(args: Array<String>) {
    val (A, B, C) = readLine()!!
        .split(" ")
        .map(String::toInt)
        .toList()
    val sum = A + B + C
    if (sum % 3 != 0) {
        println(0)
        return
    }
    val q = mutableListOf(A to B)
    val check = Array(1501) { BooleanArray(1501) }
    fun <T> Triple<T, T, T>.toPairs() = arrayOf(
        third to (first to second),
        second to (first to third),
        first to (second to third))
    fun Pair<Int, Int>.merge(): Pair<Int, Int> {
        val min = first.coerceAtMost(second)
        val max = first.coerceAtLeast(second)
        return (min + min) to (max - min)
    }
    while (q.isNotEmpty()) {
        with (q.removeAt(0)) {
            val third = sum-first-second
            if (first == second
                && second == third) {
                println(1)
                return
            }
            for (w in Triple(first, second, third).toPairs()) {
                if (w.second.first == w.second.second) 
                    continue
                val p = w.second.merge()
                if (!check[p.first][p.second]) {
                    check[p.first][p.second] = true
                    q.add(p)
                }
            }
        }
    }
    println(0)
}