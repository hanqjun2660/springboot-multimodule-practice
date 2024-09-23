# ALLBIZ

`변경사항 발생시 기재`

| 버전   | 날짜         | 변경 사항 |
|--------|------------|-------|
| 1.0    | 2024-09-23 | 최초작성  |

---

## Structure
```text
root
 ┣ application
 ┣ core
 ┣ externalAPI
 ┗ internalAPI
   ┗ user
```
---

## Relationship between modules
| 모듈명              | 설명                 | 의존성                            |
|------------------|--------------------|--------------------------------|
| application      | 애플리케이션 실행 모듈       | core, internalAPI, externalAPI |
| core             | 보안, 인증, 인가, 유틸 모듈  |                                |
| externalAPI      | 외부 API와의 통신 모듈     | externalAPI:                   |
| internalAPI      | 내부 서비스 간 통신 모듈     | internalAPI:user               |
| internalAPI:user | 사용자 관련 기능 모듈       | core                     |

---

## Development environment and dependencies
* JDK 17
* Spring Boot 3.3.3
* spring Security 6.3.3
* Spring Boot Starter
* Spring Boot Starter Web
* Spring Boot Starter validation
* spring-boot-starter-data-jpa
* Gradle 8.10
* H2 Database
* Lombok
* jsonwebtoken:jjwt-api:0.12.3
* jsonwebtoken:jjwt-impl:0.12.3
* jsonwebtoken:jjwt-jackson:0.12.3

