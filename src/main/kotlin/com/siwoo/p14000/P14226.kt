package com.siwoo.p14000

fun main(args: Array<String>) {
    data class Clip(val emo: Int, val clip: Int) {
        fun next(): List<Clip> {
            var list = listOf(Clip(emo, emo))
            if (clip != 0)
                list = list + Clip(emo+clip, clip)
            if (emo > 0)
                list = list + Clip(emo-1, clip)
            return list
        }
    }
    val K = readLine()?.toInt() ?: 0
    val q = mutableListOf(Clip(1, 0))
    val distance = mutableMapOf(q.first() to 0)
    while (q.isNotEmpty()) {
        val v = q.removeAt(0)
        if (v.emo == K) {
            println(distance[v])
            return
        }
        v.next().filter { !distance.contains(it) }
                .forEach { 
            distance[it] = distance[v]!! + 1
            q.add(it)
        }
    }
}