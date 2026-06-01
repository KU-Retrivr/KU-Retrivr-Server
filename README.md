# KU-Retrivr-Server

KU-Retrivr-Server는 교내 및 단체 대여 물품 관리를 위한 백엔드 API 서버입니다. 관리자는 기관, 물품, 대여 요청, 승인, 반납, 알림을 관리할 수 있고, 사용자는 공개 API를 통해 기관과 물품을 조회하고 대여를 요청할 수 있습니다.

## 프로젝트 설명

### 문제 정의
학교나 단체에서 공용 물품을 수기로 관리하면 승인 흐름, 반납 상태, 연체 일정, 재고 현황이 일관되지 않게 관리되기 쉽습니다.

### 목표
- 물품과 대여 재고를 체계적으로 관리한다.
- 대여 요청, 승인, 반려, 대여, 반납까지 전체 흐름을 지원한다.
- 연체 알림과 메시지 전송 기능을 제공한다.
- 관리자 인증과 이메일 인증 기능을 제공한다.

## 기술 스택

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Spring Security
- QueryDSL
- JWT
- PostgreSQL
- Spring Mail
- Swagger / springdoc-openapi
- Gradle

## 주요 기능

### 관리자 기능
- 관리자 회원가입, 로그인, 비밀번호 재설정
- 이메일 인증 코드 발송 및 검증
- 관리자 프로필 조회 및 수정
- 물품 등록, 수정, 조회
- 물품 단위 재고 상태 관리
- 대여 요청 조회, 승인, 반려
- 진행 중 대여 조회, 반납 처리, 반납 예정일 변경
- 연체 알림 메시지 발송

### 사용자 기능
- 기관 검색
- 물품 목록 및 상세 조회
- 대여 요청 등록
- 대여 요청 상세 조회

## 프로젝트 구조

```text
src/main/java/retrivr/retrivrspring
|- application      # 애플리케이션 서비스, 스케줄러, 이벤트
|- domain           # 엔티티, 도메인 로직, 리포지토리 인터페이스
|- infrastructure   # 리포지토리 구현체, 이메일, 메시지 전송
|- presentation     # 관리자/사용자 컨트롤러, 요청/응답 DTO
|- global           # 인증, 설정, 예외 처리, Swagger
|- mock             # 목 데이터 및 스모크 테스트 지원
```

## 설치 및 실행 방법

### 1. 사전 요구사항

- JDK 21
- 이 저장소에 포함된 Gradle Wrapper
- PostgreSQL 데이터베이스
- 이메일 인증 및 메시지 전송용 SMTP 계정

### 2. 환경 변수 설정

프로젝트 루트에 `.env.example`을 참고해 `.env` 파일을 생성합니다.

필수 환경 변수:

```env
DB_URL=jdbc:postgresql://{db_host}:{db_port}/{db_name}
DB_USERNAME={your_username_here}
DB_PASSWORD={your_password_here}

JPA_DDL_AUTO=validate

JWT_SECRET={your_jwt_secret_here}
JWT_ACCESS_EXPIRE_MS=86400000
JWT_REFRESH_EXPIRE_MS=259200000

MAIL_HOST={your_smtp_host_here}
MAIL_PORT=587
MAIL_USERNAME={your_smtp_username_here}
MAIL_PASSWORD={your_smtp_password_here}

SERVER_BASE_URL=http://localhost
SERVER_PORT=8080

EMAIL_VERIFICATION_EXPIRES_SECONDS=600
EMAIL_VERIFICATION_RESEND_BLOCK_SECONDS=60
EMAIL_VERIFICATION_MAX_FAILED_ATTEMPTS=5

SWAGGER_ENABLED=true

CORS_ALLOWED_ORIGINS=http://localhost:3000
CORS_ALLOWED_METHODS=GET,PUT,POST,PATCH,DELETE,OPTIONS
```

### 3. 로컬 실행

macOS / Linux:

```bash
./gradlew bootRun
```

Windows:

```powershell
.\gradlew.bat bootRun
```

기본 실행 주소:

- `http://localhost:8080`

### 4. 테스트 실행

macOS / Linux:

```bash
./gradlew test
```

Windows:

```powershell
.\gradlew.bat test
```

## API 문서

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 테스트

구현된 테스트 목록

- 도메인 단위 테스트
- 애플리케이션 서비스 테스트
- 컨트롤러 검증 테스트
- 시나리오 테스트
- Mock API 스모크 테스트

권장 실행 명령:

```bash
./gradlew test
```

## 팀원 소개

| 이름  | 역할    | 담당 업무                    |
| --- | ----- | ------------------------ |
| 박다솔 | 백엔드   | 서버 개발, API 구현, 데이터베이스 연동 |
| 조성호 | 프론트엔드 | 사용자 화면 구현, API 연동        |
