package com.siwoo.p2000

fun main(args: Array<String>) {
    val strings = readLine()?.split(" ") ?: listOf()
    val a = strings[0].toInt()
    val b = strings[1].toInt()
    val gcd = gcd(a, b)
    val lcd = a * b / gcd
    println(gcd)
    println(lcd)
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}