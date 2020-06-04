# 동적 쿼리 예제
- Hibernate + QueryDSL
- Spring Data JPA + QueryDSL
- Spring Data JPA's Specification

## 결과 테스트
- `/src/test/com/example/dynamic_query/member/memberTest.java`

## 샘플 데이터
- `/src/main/resources/schema-h2.sql`
- `/src/main/resources/data-h2.sql`

## 환경
- JDK 1.8
- Gradle 6.4.1
- Spring Boot Web Starter 2.3
- Spring Data JPA
- QueryDSL 4.3.1
- Junit 5 (Jupiter)
- H2 Embeded Memory DB

## QueryDSL QClass Generate
- execute command: `./gradlew build`
- output path: `/build/generated/querydsl/com/example/dynamic_query/member`
- output path: `/build/generated/querydsl/com/example/dynamic_query/team`

