package com.example.demo.article.adapter.`in`.api

import com.example.demo.article.adapter.`in`.api.dto.ErrorResponse
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ArticleControllerAdvice {
    @ExceptionHandler(DomainNotFoundException::class)
    fun handleDomainNotFoundException(e: DomainNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))
    }
}
