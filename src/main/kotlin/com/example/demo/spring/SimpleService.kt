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

    override fun create(request: Request): String {
        if (request.name.isEmpty()) {
            throw IllegalArgumentException("name 이 공백")
        }

        return request.name
    }
}