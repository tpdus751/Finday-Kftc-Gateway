# Finday KFTC Gateway (가상 금융결제원 역할을 하는 서버)

> **Finday** 프로젝트의 **가상 금융결제원 서버**입니다.  
사용자의 계좌 통합 조회, 계좌 이체 요청을 처리하며, Finday 서비스와 각 은행 API 간의 **중계 역할**을 수행합니다.


## 아키텍처

<img width="1320" height="651" alt="image" src="https://github.com/user-attachments/assets/577b5f99-fbdc-4f23-b6f6-770c2b73138c" />

```
사용자
↓
[Finday Backend]
↓
[KFTC Gateway 서버]
↓
[각 은행 서버들 (국민, 신한, 토스 등)]
```

## Tech Stack

| 분류 | 기술 | 설명 |
|------|------|------|
| **Language** | Java 17 | 안정성과 최신 문법을 모두 갖춘 백엔드 개발용 JVM 언어 |ㅃ
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

## 역할

이 서버는 Finday 프로젝트의 MSA 아키텍처에서 **가상의 금융결제원(Gateway)** 역할을 수행합니다.  
Finday 서버로부터의 요청을 각 은행 서버로 중계하여 **계좌, 카드, 거래, 이체, 결제** 등 다양한 금융 기능을 통합 제공합니다.

## 주요 기능

| 구분 | 설명 |
|------|------|
| **계좌 통합 조회** | 사용자 연동 은행 전체 또는 특정 은행의 계좌 정보를 통합 조회 |
| **카드 통합 조회** | 사용자 연동 은행별 카드 목록을 통합 조회 |
| **거래 내역 통합 조회** | 전체 은행의 거래내역을 월별 또는 최근 30일 기준으로 조회 |
| **계좌 이체 처리** | 송신자 은행 출금 → 수신자 은행 입금 요청을 순차 처리 |
| **결제 요청 처리** | Finday Pay 기능으로 계좌/카드를 통한 통합 결제 수행 |

## 제공 중인 API 목록 (Swagger 기준)

<img width="1183" height="97" alt="image" src="https://github.com/user-attachments/assets/eb69043e-ee47-4480-8a51-7a559e0c7d5b" />
- `POST /gateway/transfer`  
  → 이체 요청 처리 (출금 → 입금)<br>
  

<img width="1186" height="95" alt="image" src="https://github.com/user-attachments/assets/3722d053-a67d-4079-910a-a34520e6d4e2" />
- `POST /gateway/pay/intergrated-method`  
  → 통합 결제 요청 (계좌 또는 카드 기반)


<img width="1373" height="1023" alt="image" src="https://github.com/user-attachments/assets/1bea0c47-87d6-4c0c-b32b-973194fdc927" />
- `GET /gateway/cards/list`  
  → 사용자 연동 카드 목록 조회


<img width="1378" height="874" alt="image" src="https://github.com/user-attachments/assets/4fa7f420-33a0-44c4-bbb4-659540bdeecf" />
- `GET /gateway/accounts/all`  
  → 전체 연동 계좌 통합 조회


<img width="1383" height="1027" alt="image" src="https://github.com/user-attachments/assets/3842a761-8aba-475b-b570-a7dde947f603" />
- `GET /gateway/accounts/list`  
  → 특정 은행의 계좌 조회

  
<img width="1233" height="1032" alt="image" src="https://github.com/user-attachments/assets/cf839f7b-6438-4e0a-9cf1-ff9136ba4ab7" />
- `GET /gateway/transaction/month`  
  → 월별 거래 내역 조회


<img width="1220" height="1019" alt="image" src="https://github.com/user-attachments/assets/de8c0e09-3a15-4dad-b321-afd41019ab1c" />
- `GET /gateway/transaction/latest30days`  
  → 최근 30일 거래 내역 조회


## application.yml

```yaml
server:
  port: 8085  # 중계 서버(Gateway)의 실행 포트

bank:
  kookmin:
    url: http://localhost:8090
  shinhan:
    url: http://localhost:8091
  hana:
    url: http://localhost:8092
  woori:
    url: http://localhost:8093
  nh:
    url: http://localhost:8094
  sc:
    url: http://localhost:8095
  kakao:
    url: http://localhost:8096
  k:
    url: http://localhost:8097
  toss:
    url: http://localhost:8098
  # 각 가상 은행 서버의 API URL을 정의합니다.

springdoc:
  swagger-ui:
    path: /swagger-ui.html  # Swagger UI 접속 경로 설정

spring:
  main:
    allow-bean-definition-overriding: true  # 동일한 이름의 빈 등록 허용 (모듈 간 충돌 방지 목적)
```


## 🧑‍💻 개발자
```
박세연
2년제 인공지능소프트웨어 전공 / 핀테크 & AI 백엔드 개발 지향
✉️ 751psy@gmail.com
```


## 📁 관련 레포지토리

| 서비스 구분 | 설명 | 레포지토리 |
|-------------|------|-------------|
| 🌐 Finday 프론트엔드 | React 기반 사용자 웹 서비스 | [`Finday_frontend`](https://github.com/tpdus751/Finday_frontend) |
| 🧠 Finday 백엔드 | Spring Boot 기반 핵심 비즈니스 로직 서버 | [`Finday_backend`](https://github.com/tpdus751/Finday_backend) |
| 🧪 얼굴 인증 Flask 서버 | DeepFace 기반 사용자 얼굴 인증 처리 | [`Finday-Flask-Face-Verification-DeepFace`](https://github.com/tpdus751/Finday-Flask-Face-Verification-DeepFace) |
| 🏦 가상 금융결제원 중계 서버 | 은행 API 통합 게이트웨이 (Spring WebFlux 기반) | [`Finday-Kftc-Gateway`](https://github.com/tpdus751/Finday-Kftc-Gateway) |
| 🏛️ 은행 서버 (예: 국민은행) | 각 은행별 API를 제공하는 독립 서버 | [`Finday-Bank-Server`](https://github.com/tpdus751/Finday-Bank-Server) |


