package com.example.demo.kotest.style

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ShouldSpecExample : ShouldSpec({
    should("문자열의 길이를 반환해야 한다") {
        "string".length shouldBe 6
    }
})