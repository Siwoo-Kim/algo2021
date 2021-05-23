package com.siwoo.p1000

fun main(args: Array<String>) {
    fun read(): List<String> = readLine()?.split(" ") ?: arrayListOf()
    val a = read()
    val (L, C) = a[0].toInt() to a[1].toInt()
    val s = read().sorted()
    val vowels = mutableListOf<String>()
    (0 until C).forEach {
        val c = s[it]
        when (c) {
           "a", "e", "i", "o", "u" -> vowels.add(c)
        }
    }
    fun password(s: List<String>, vowels: List<String>): List<String> {
        fun valid(password: String): Boolean {
            var (v, o) = 0 to 0
            for (c in password) {
                if (vowels.contains(c.toString())) v++
                else o ++
            }
            return v >= 1 && o >= 2
        }
        var answer = arrayListOf<String>()
        fun recur(index: Int, pw: String): List<String> {
            if (pw.length == L) {
                if (valid(pw))
                    answer.add(pw)
                return answer
            }
            if (index >= s.size) return answer
            recur(index+1, "$pw${s[index]}")
            recur(index+1, pw)
            return answer
        }
        return recur(0, "")
    }
    for (s in password(s, vowels)) println(s)
}