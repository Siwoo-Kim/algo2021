package com.siwoo.p11000

import java.util.*

data class Card(val amount: Int, val price: Int)

fun main(args: Array<String>) {
    fun StringTokenizer.nextInt(): Int = Integer.parseInt(this.nextToken())
    val N = readLine()?.toInt() ?: 0
    val dp = Array(N+1) { -1 }
    val tokens = StringTokenizer(readLine())
    var cards: List<Card> = mutableListOf()
    for (i in 1 .. N)
        cards = cards + Card(i, tokens.nextInt())
    fun purchase(cards: List<Card>, remains: Int): Int {
        if (remains == 0) return 0
        if (dp[remains] != -1) return dp[remains]
        var max = 0
        for (card in cards) {
            if (remains >= card.amount)
                max = Math.max(max, 
                    purchase(cards, remains-card.amount) + card.price)
        }
        dp[remains] = max
        return dp[remains]
    }
    println(purchase(cards, N))
}