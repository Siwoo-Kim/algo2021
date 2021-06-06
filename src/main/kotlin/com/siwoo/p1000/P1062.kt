package com.siwoo.p1000

fun main(args: Array<String>) {
    val antatica = "antatica".toSet()
    val (N, K) = readLine()!!.split(" ")
        .map(String::toInt).toIntArray()
    fun Int.add(x: Int) = this or (1 shl x)
    fun Int.print() = run {
        for (i in 0 .. 26)
            if ((this and (1 shl i)) != 0)
                print((i + 'a'.toInt()).toChar() + " ")
    }
    val W = Array(N) {
        var bitset = 0
        for (c in readLine()!!)
            bitset = bitset.add(c.toInt() - 'a'.toInt())
        bitset
    }
    fun calc(set: Int): Int {
        var cnt = 0
        for (w in W)
            if ((w and ((1 shl 26)-1-set)) == 0)
                cnt++
        return cnt
    }
    fun select(i: Int, k: Int, set: Int): Int {
        if (k < 0) return 0
        if (i == 26) return calc(set)
        val c = (i + 'a'.toInt()).toChar()
        return select(i+1, k-1, set.add(i))
            .coerceAtLeast(if (antatica.contains(c)) 0 else select(i+1, k, set))
    }
    val x = select(0, K, 0)
    println(x)
}