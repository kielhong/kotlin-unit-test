package com.example.demo.article.adapter.out.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface BoardJpaRepository : JpaRepository<BoardJpaEntity, Long>
