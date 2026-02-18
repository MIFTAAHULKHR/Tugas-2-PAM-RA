package com.tugas2.project

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlin.native.concurrent.ThreadLocal

enum class NewsCategory { TECH, BUSINESS, SPORTS, GENERAL }

data class NewsArticle(
    val id: Int,
    val title: String,
    val category: NewsCategory,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()
)

private var globalNewsId = 1

fun newsFeedSimulator(): Flow<NewsArticle> = flow {
    val newsMap = mapOf(
        NewsCategory.TECH to listOf("Gadget Baru Rilis", "Inovasi AI", "Update Sistem Operasi", "Chipset Tercepat"),
        NewsCategory.BUSINESS to listOf("Pasar Saham Naik", "Akuisisi Perusahaan", "Inflasi Menurun", "Startup IPO"),
        NewsCategory.SPORTS to listOf("Kemenangan Tim Lokal", "Rekor Dunia Baru", "Transfer Pemain", "Final Kejuaraan"),
        NewsCategory.GENERAL to listOf("Cuaca Ekstrem", "Update Lalu Lintas", "Festival Budaya", "Kebijakan Baru")
    )

    while (true) {
        val category = NewsCategory.entries.random()
        val title = newsMap[category]?.random() ?: "Berita Terkini"
        
        val news = NewsArticle(
            id = globalNewsId,
            title = "$title #$globalNewsId",
            category = category
        )
        
        emit(news)
        globalNewsId++
        delay(2000)
    }
}
