package com.example.demo.article.adapter.`in`.api

import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ArticleControllerAdvice {
    @ExceptionHandler(DomainNotFoundException::class)
    fun handleDomainNotFoundException(e: DomainNotFoundException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse(e.message))
    }
}

data class ExceptionResponse(
    val message: String,
)
