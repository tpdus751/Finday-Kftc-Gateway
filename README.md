# Finday KFTC Gateway (가상 금융결제원 역할을 하는 서버)

> **Finday** 프로젝트의 **가상 금융결제원 서버**입니다.  
사용자의 계좌 통합 조회, 계좌 이체 요청을 처리하며, Finday 서비스와 각 은행 API 간의 **중계 역할**을 수행합니다.


## 🛠️ Tech Stack

| 분류 | 기술 | 설명 |
|------|------|------|
| **Language** | Java 17 | 안정성과 최신 문법을 모두 갖춘 백엔드 개발용 JVM 언어 |
| **Framework** | Spring Boot 3.x | 빠른 설정과 강력한 생태계를 가진 자바 백엔드 프레임워크 |
| **Build Tool** | Gradle | 의존성 관리 및 빌드를 자동화하는 빌드 도구 |
| **REST API** | Spring Web | RESTful API 개발을 위한 HTTP 요청/응답 처리 모듈 |
| **Validation** | Jakarta Bean Validation | 요청값 검증을 위한 표준 어노테이션 기반 유효성 검사 |
| **API 문서화** | SpringDoc OpenAPI 3 (Swagger UI) | API 명세 자동 생성 및 테스트 UI 제공 도구 |
| **Database** | MySQL 8.x | 널리 사용되는 관계형 데이터베이스 시스템 |
| **ORM** | Spring Data JPA | 객체와 테이블 간 매핑을 자동화하는 ORM 라이브러리 |
| **Dependency Management** | Spring Boot Dependency Management | 버전 충돌 없이 안정적인 라이브러리 관리 |
| **Lombok** | 코드 자동화 | Getter, Setter, Constructor 등을 자동 생성하여 코드 간결화 |
| **Test** | Spring Boot Starter Test | 단위 및 통합 테스트를 위한 테스트 프레임워크 (JUnit 기반) |
