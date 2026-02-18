package com.tugas2.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform