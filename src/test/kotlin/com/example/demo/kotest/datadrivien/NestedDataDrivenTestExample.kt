package com.example.demo.kotest.datadrivien

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

class NestedDataDrivenTestExample : FunSpec({
    val apis = listOf(
        "http://internal.foo",
        "http://internal.bar",
        "http://public.baz",
    )
    val methods = listOf("GET", "POST", "PUT")

    context("각 API 는 HTTP Method를 지원해야 함") {
        withData(apis) { api ->
            withData(methods) { method ->
                println("API: $api, Method: $method")
            }
        }
    }

    context("각 API 는 HTTP Method를 지원해야 함 2") {
        withData(apis) { api ->
            withData<String>({"HTTP Method : $it" }, methods) { method ->
                println("API: $api, Method: $method")
            }
        }
    }
})