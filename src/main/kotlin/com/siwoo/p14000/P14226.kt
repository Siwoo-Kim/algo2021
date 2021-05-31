package com.siwoo.p14000

fun main(args: Array<String>) {
    val S = readLine()?.toInt() ?: 0
    data class Clip(val emo: Int, val clip: Int) {
        fun next(S: Int, p: (Clip) -> Boolean): List<Clip> {
            val list = mutableListOf(Clip(emo, emo))
            if (emo-1 >= 0)
                list.add(Clip(emo-1, clip))
            if (emo+clip <= S)
                list.add(Clip(emo + clip, emo))
            return list.filter(p)
        }
    }
    val q = mutableListOf(Clip(1, 0))
    val distance = mutableMapOf(Clip(1, 0) to 0)
    while (q.isNotEmpty()) {
        val p = q.removeAt(0)
        if (p.emo == S) {
            println(distance[p])
            return
        }
        p.next(S){ !distance.contains(it) }
                .forEach { q.add(it); distance[it] = distance[p]!! + 1}
    }
}