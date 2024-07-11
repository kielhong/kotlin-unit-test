package com.example.demo.kotest.assertion

import io.kotest.assertions.json.shouldBeJsonArray
import io.kotest.assertions.json.shouldBeJsonObject
import io.kotest.assertions.json.shouldBeValidJson
import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.FunSpec

// io.kotest:kotest-assertions-json 필요
class AssertionJsonExample : FunSpec({
    test("json test") {
        val json = """
            {
                "name": "kotlin",
                "age": 10
            }
        """.trimIndent()

        json.shouldBeValidJson()
        json.shouldBeJsonObject()
        json shouldEqualJson "{ \"age\": 10, \"name\": \"kotlin\"}"
        json shouldContainJsonKey "name"
    }

    test("json array test") {
        val jsonArray = """
            [
                {
                    "name": "kotlin"
                },
                {
                    "name": "java"
                }
            ]
        """.trimIndent()

        jsonArray
            .shouldBeValidJson()
            .shouldBeJsonArray()
    }
})