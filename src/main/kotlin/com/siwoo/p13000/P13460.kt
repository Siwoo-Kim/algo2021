fun main(args: Array<String>) {
    data class Point(val x: Int, val y: Int) {
        fun distance(p: Point): Int = Math.abs((p.x * p.x) - (x * x) + (p.y * p.y) - (y * y))
    }
    val (N, M) = readLine()!!.split(" ").map(String::toInt)
    val G = Array(N) {
        readLine()!!.toCharArray()
    }
    val map = mutableMapOf<Char, Point>()
    G.withIndex().forEach { g ->
        g.value.withIndex().forEach { k ->
            when(k.value) {
                'R', 'B', 'O' -> map[k.value] = Point(g.index, k.index)
            }
        }
    }
    fun Point.valid() = x in 0 until N && y in 0 until M
    fun Point.moveable() = this.valid() && G[x][y] != '#'
    val direction = arrayOf( 
        { p: Point -> generateSequence(p) { Point(it.x+1, it.y) }.takeWhile { it.moveable() } },
            { p: Point -> generateSequence(p) { Point(it.x-1, it.y) }.takeWhile { it.moveable() } },
            { p: Point -> generateSequence(p) { Point(it.x, it.y+1) }.takeWhile { it.moveable() } },
            { p: Point -> generateSequence(p) { Point(it.x, it.y-1) }.takeWhile { it.moveable() } })

    fun go(cnt: Int, r: Point, b: Point): Int {
        if (cnt >= 10) return Int.MAX_VALUE
        return direction.map { d ->
            var re = d(r).last()
            var rb = d(b).last()
            if (d(r).contains(map['O']) && d(b).contains(map['O'])) // blue wins
                return@map Int.MAX_VALUE
            if (d(b).contains(map['O']))  // blue wins
                return@map Int.MAX_VALUE
            if (re == r && rb == b) // no move
                return@map Int.MAX_VALUE
            if (d(r).contains(map['O']))  // red wins
                return@map cnt + 1
            if (re == rb) {
                val d1 = r.distance(re)
                val d2 = b.distance(re)
                if (d2 > d1)
                    rb = d(b).last { it != re }
                else
                    re = d(r).last { it != rb }
            }
            return@map go(cnt+1, re, rb)
        }.fold(Int.MAX_VALUE) { acc, i -> acc.coerceAtMost(i) }
    }
    val min = go(0, map['R']!!, map['B']!!)
    println(if (min == Int.MAX_VALUE) -1 else min)
}