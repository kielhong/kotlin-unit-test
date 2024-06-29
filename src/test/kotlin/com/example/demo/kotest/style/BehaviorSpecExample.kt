package com.example.demo.kotest.style

import io.kotest.core.spec.style.BehaviorSpec

class BehaviorSpecExample : BehaviorSpec({
    context("빗자루는 하늘을 날 수 있고, 주인에게 되돌아 와야 한다") {
        given("빗자루가 주어지면") {
            `when`("빗자루 위에 앉았을 때") {
                then("날 수 있어야 한다") {
                    // test code
                }
            }
            `when`("빗자루를 멀리 던지면") {
                then("원래 위치로 돌아온다") {
                    // test code
                }
            }
        }
    }

    Given("a broomstick") {
        When("I sit on it") {
            Then("I should be able to fly") {
                // test code
            }
        }
        When("I throw it away") {
            Then("it should come back") {
                // test code
            }
        }
    }
})