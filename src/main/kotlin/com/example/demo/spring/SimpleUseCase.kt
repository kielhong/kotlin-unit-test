package com.example.demo.spring

interface SimpleUseCase {
    fun execute(): String

    fun exist(): Boolean

    fun create(request: Request): String
}