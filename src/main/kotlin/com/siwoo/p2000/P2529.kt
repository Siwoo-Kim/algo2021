package com.siwoo.p2000

fun main(args: Array<String>) {
    fun nextPerm(a: MutableList<Int>, compare: (Int, Int) -> Boolean): Boolean {
        fun swap(i: Int, j: Int) {
            val tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
        }
        var i = a.size-1
        while (i > 0 && compare(a[i-1], a[i])) i--
        if (i == 0) return false
        var j = a.size-1
        while (compare(a[i-1], a[j])) j--
        swap(i-1, j)
        j = a.size-1
        while (i < j) swap(i++, j--)
        return true
    }
    
    fun satisfy(signs: List<String>, nums: List<Int>): Boolean {
        for ((i, sign) in signs.withIndex())
            if (sign == "<" && nums[i] > nums[i+1])
                return false
            else if (sign == ">" && !(nums[i] > nums[i+1]))
                return false
        return true
    }
    
    fun template(signs: List<String>, nums: MutableList<Int>, 
                 compare: (Int, Int) -> Boolean): String {
        val x by lazy {
            do {
                if (satisfy(signs, nums)) break
            } while (nextPerm(nums, compare))
            nums
        }
        return x.joinToString(separator = "")
    }
    
    val N = readLine()?.toInt() ?: 0
    val signs = readLine()?.split("\\s+".toRegex())?.filter { it.isNotEmpty() } ?: arrayListOf()
    
    println(template(signs, (9 downTo 9-N).toMutableList()) { i, j -> i <= j})
    println(template(signs, (0 .. N).toMutableList()) { i, j -> i >= j})
}