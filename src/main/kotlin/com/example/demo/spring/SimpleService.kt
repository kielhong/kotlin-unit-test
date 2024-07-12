package com.example.demo.spring

import org.springframework.stereotype.Service

@Service
class SimpleService : SimpleUseCase {
    override fun execute(): String {
        return "Hello, World!"
    }

    override fun exist(): Boolean {
        return true
    }
}