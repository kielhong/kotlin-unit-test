package com.example.demo.spring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleApi(
    private val simpleUseCase: SimpleUseCase
) {
    @GetMapping("/api/simple")
    fun getInfo(): String {
        if (simpleUseCase.exist()) {
            return simpleUseCase.execute()
        }
        return "NOT FOUND"
    }

    @PostMapping("/api/simple")
    fun postInfo(@RequestBody request: Request): Unit {
        simpleUseCase.create(request)
    }
}

data class Request(
    val name: String
)