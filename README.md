# kotlin-unit-test
패스트 캠퍼스 Kotlin 단위 테스트 프로젝트


## 1. 패키지 구조

패키지 구조는 [헥사고날 아키텍처][hexagonal-architecture]를 따르고 다음과 같습니다.
 
```text
article
    ├── domain
    │    ├── Article.kt 
    │    └── Board.kt   
    ├── application
    │    ├── service
    │    │   ├── ArticleCommandService.kt
    │    │   └── ArticleQueryService.kt
    │    └── port
    │        ├── in
    │        │   ├── CreateArticleUseCase.kt
    │        │   ├── DeleteArticleUseCase.kt
    │        │   ├── GetArticleUseCase.kt
    │        │   └── UpdateArticleUseCase.kt
    │        └── out
    │            ├── DeleteArticlePort.kt
    │            ├── LoadArticlePort.kt
    │            ├── SaveArticle.kt
    │            └── LoadBoardPort.kt
    └── adapter
             ├── in
             │    ├── dto
             │    │    ├── ArticleResponse.kt
             │    │    └── ArticleRequest.kt
             │    ├── ArticleController.kt
             │    └── ArticleControllerAdvice.kt
             └── out
                 └── persistence
                       ├── ArticlePersistenceAdapter.kt
                       ├── BoardPersistenceAdapter.kt
                       └── jpa
                            ├── ArticleJpaEntity.kt
                            ├── ArticleJpaRepository.kt
                            ├── BoardJpaEntity.kt
                            └── BoardJpaRepository.kt

```

- domain
   - 도메인 객체(Article, Board)
- application
   - service
      - 인커밍 포트의 인터페이스들을 구현하는 서비스 클래스
    - port
      - in
         - 인커밍 어댑터(이 프로젝트에서는 Controller)가 의존하는 인커밍 포트가 위치
         - 서비스가 구현해야 하는 인터페이스 
      - out
         - 아웃고잉 어댑터 인터페이스
- adapter
   - in
      - 애플리케이션 계층의 인커밍 포트를 호출하는 인커밍 어댑터가 위치
         - 외부의 API 요청을 받아 처리하는 컨트롤러(이 프로젝트에서는 ArticleController)
   - out
      - 아웃고잉 포트에 대한 구현을 제공하는 아웃고잉 어댑터가 위치
         - JPA 관련 영속성을 처리하는 JpaEntity, JpaRepostory가 위치
       
## 2. 테스트

테스트를 작성하는 일반적인 규칙에 대해 설명합니다.

### 2.1. 네이밍

단위 테스트 클래스는 `[SUT]Test` 혹은 `[SUT]Spec`으로 명명합니다.

`SUT`는 테스트 대상 클래스명입니다.

e.g. `ArticleServiceTest`, `ArticleControllerTest`, `BoardServiceSpec` etc.

### 2.2. 테스트 클래스 위치

단위 테스트 클래스는 main 코드에 대응되는 package에 생성한다

e.g. `com.example.demo.article.application.service.ArticleService.java` -> `com.example.demo.article.application.service.ArticleServiceTest.java`

### 2.3 테스트 픽스쳐

테스트 수행 전에 사용하는 환경 설정 혹은 데이터

Java Test Fixture 설정
```
plugins {
    `java-test-fixtures`
}
```

## 3. 예제 안내
### Kotest 테스트 프레임웍
- [Kotest Test Writing Style](https://github.com/kielhong/kotlin-unit-test/tree/main/src/test/kotlin/com/example/demo/kotest/style)
- [Assertion](https://github.com/kielhong/kotlin-unit-test/tree/main/src/test/kotlin/com/example/demo/kotest/assertion)
- [Data Driven Testing](https://github.com/kielhong/kotlin-unit-test/tree/main/src/test/kotlin/com/example/demo/kotest/datadrivien)
- [Coroutine Test](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/kotest/coroutine/TestDispatcherTest.kt)

### Mockk 테스트
- [Mock 생성](https://github.com/kielhong/kotlin-unit-test/tree/main/src/test/kotlin/com/example/demo/mockk/mock)
  - [Relaxed Mock](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/mock/RelaxedMock.kt)
  - [Spy](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/mock/Spyk.kt)
  - [Object, Enum](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/mock/ObjectEnumMock.kt)
- [Stub](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/stub/StubTest.kt)
- [Verification](https://github.com/kielhong/kotlin-unit-test/tree/main/src/test/kotlin/com/example/demo/mockk/verify)
  - [confirmedVerify](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/verify/ConfirmedVerifyTest.kt)
  - [withArg](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/verify/VerifyWithArgTest.kt)
- [Argument capture](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/capture/CaptureTest.kt)
- [Coroutine](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/mockk/coroutine/CoroutineTest.kt)

### Spring Boot Test와 연동
- [Spring Boot @Autowired 를 이용한 통합 테스트](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/spring/SimpleServiceIntTest.kt)
- [MockkBean](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/spring/SimpleApiMockkBeanTest.kt)
- [@SpringBootTest 를 이용한 통합 테스트](https://github.com/kielhong/kotlin-unit-test/blob/main/src/test/kotlin/com/example/demo/spring/SimpleApiIntTest.kt)

## 참고 사이트
- Kotest: https://kotest.io/
- Mockk : [https://assertj.github.io/doc/](https://mockk.io/)
  - Mockk 문서 한국어 번역본 : https://www.devkuma.com/docs/kotlin/mockk/
- springmockk: [https://github.com/mockito/mockito](https://github.com/Ninja-Squad/springmockk)
- java-test-fixture : https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures

[hexagonal-architecture]: https://alistair.cockburn.us/hexagonal-architecture/
