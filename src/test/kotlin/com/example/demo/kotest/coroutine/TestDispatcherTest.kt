package com.example.demo.kotest.coroutine

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.testCoroutineScheduler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalStdlibApi::class)
class TestDispatcherTest : FunSpec() {
    init {
        test("advance time") {
            val duration = 5.seconds
            launch {
                delay(duration.inWholeMilliseconds)
                println(LocalDateTime.now())
            }
            println(LocalDateTime.now())
        }

        test("advance time 코루틴 테스트 스코프").config(coroutineTestScope = true) {
            val duration = 5.seconds
            launch {
                delay(duration.inWholeMilliseconds)
                println(LocalDateTime.now())
            }
            println(LocalDateTime.now())

            // move the clock on and the delay in the above coroutine will finish immediately.
            println(testCoroutineScheduler.currentTime)
            testCoroutineScheduler.advanceTimeBy(duration.inWholeMilliseconds)
            println(testCoroutineScheduler.currentTime)
        }
    }
}