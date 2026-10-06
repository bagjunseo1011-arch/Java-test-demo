# 자바웹프로그래밍(2) 실습

미디어소프트웨어학과 20230990 박준서

## 5주차 - 로그인/로그아웃, 암호화 완료

Spring Security 7.1로 세션 기반 로그인/로그아웃을 구현하고, 비밀번호를 BCrypt로 해시해서 저장합니다.

- [SecurityConfig.java : 접근 규칙, 폼 로그인, 로그아웃, 로그인 상태 유지](src/main/java/com/example/demo/config/SecurityConfig.java)
- [MemberController.java : 로그인·회원가입 요청](src/main/java/com/example/demo/controller/MemberController.java)
- [Member.java : 회원 엔티티](src/main/java/com/example/demo/model/domain/Member.java)
- [MemberForm.java : 회원가입 DTO](src/main/java/com/example/demo/model/dto/MemberForm.java)
- [MemberRepository.java : 회원 조회·중복 확인](src/main/java/com/example/demo/model/repository/MemberRepository.java)
- [MemberService.java : 가입(BCrypt 암호화), 로그인 조회(UserDetailsService)](src/main/java/com/example/demo/model/service/MemberService.java)
- [login.html](src/main/resources/templates/login.html) / [signup.html](src/main/resources/templates/signup.html) / [index.html : 네비게이션 로그인 버튼](src/main/resources/templates/index.html)

### 5주차 작업 내용
- 의존성 추가 : `spring-boot-starter-security`, `thymeleaf-extras-springsecurity6`
- 메인·상세·로그인·가입 페이지와 정적 리소스는 누구나 접근, `/testdb`(회원목록)는 로그인 필요
- 회원가입 : 아이디 중복 확인 → 비밀번호 BCrypt 해시(`$2a$10$...`, 60자) → `role=USER`로 저장
- 로그인 : `loadUserByUsername()`만 구현하고 비밀번호 비교는 시큐리티가 처리, 성공 시 원래 가려던 페이지로 복귀
- 로그아웃 : POST `/logout` (CSRF 보호), 세션·쿠키 삭제
- 네비게이션 : 비로그인 시 로그인/회원가입, 로그인 시 `아이디님 + 로그아웃` (`sec:authorize`)
- DB 계정 정보를 `application-secret.properties`로 분리 (`.gitignore` 처리)
- 연습문제 ① 로그인 상태 유지 : `rememberMe()` 7일, 브라우저를 닫아도 로그인 유지
- 연습문제 ② 비밀번호 확인 : 불일치 시 "비밀번호가 일치하지 않습니다." 출력, DB에 저장 안 함

### 비밀 설정 파일 (clone 후 실행 시)
DB 계정과 remember-me 키는 공개 저장소에 올라가지 않도록 `.gitignore` 처리된 파일에 둡니다.
`src/main/resources/application-secret.properties` 파일을 만들고 아래 내용을 넣으세요.

```
spring.datasource.username=root
spring.datasource.password=본인_MySQL_root_비밀번호
app.remember-me-key=임의의_긴_문자열
```

## 4주차 - 데이터베이스 연동 및 테스트 완료

MySQL 8.0 + Spring Data JPA를 연동하고, 프로젝트를 계층별 패키지 구조로 바꿨습니다.

- [TestDB.java : 엔티티 (domain)](src/main/java/com/example/demo/model/domain/TestDB.java)
- [TestRepository.java : 리포지토리 (repository)](src/main/java/com/example/demo/model/repository/TestRepository.java)
- [TestService.java : 서비스 (service)](src/main/java/com/example/demo/model/service/TestService.java)
- [DemoController.java : 컨트롤러 (controller, /testdb 추가)](src/main/java/com/example/demo/controller/DemoController.java)
- [testdb.html : th:each로 사용자 목록 출력](src/main/resources/templates/testdb.html)

### 4주차 작업 내용
- 의존성 추가 : Spring Data JPA, MySQL 커넥터(mysql-connector-j), Lombok
- `application.properties`에 MySQL 접속 정보 추가 (DB : `spring`, 포트 3306, `ddl-auto=update`)
- 패키지 구조 변경 : `controller` / `model/domain` / `model/repository` / `model/service`
- 서버 실행 시 JPA가 `testdb` 테이블 자동 생성 확인 (`HikariPool-1 - Start completed`)
- `findAll()`로 전체 사용자 조회 후 `th:each`로 표 출력
- 연습문제 : 엔티티에 나이(`age`), 성별(`gender`) 컬럼 추가 → INSERT로 사용자 4명 입력 → 표 출력

DB 비밀번호는 4주차부터 별도 파일로 분리했습니다. 5주차부터 파일 이름이 `application-secret.properties`로 바뀌었습니다 (위 5주차 설명 참고).

## 3주차 - 포트폴리오 작성하기(프론트) 완료

부트스트랩 5 기반 TemplateMo 578 First Portfolio 템플릿을 Spring Boot 프로젝트에 적용했습니다.

- [index.html : 포트폴리오 메인 / Thymeleaf 경로 변환 완료](src/main/resources/templates/index.html)
- [detailed_web.html : 웹 상세 페이지](src/main/resources/public/detailed_web.html)
- [detailed_ai.html : AI 상세 페이지 (연습문제)](src/main/resources/public/detailed_ai.html)
- [detailed_security.html : 보안 상세 페이지 (연습문제)](src/main/resources/public/detailed_security.html)
- [detailed_game.html : 게임 상세 페이지 (연습문제)](src/main/resources/public/detailed_game.html)
- [static : css / js / images / fonts](src/main/resources/static)

### 3주차 작업 내용
- 템플릿 배치 : `index.html` → templates, `css·js·images·fonts` → static (기존 index는 `indexbackup.html`로 백업)
- 자원 경로를 상대경로에서 Thymeleaf `@{...}` 표기로 변환 (28곳)
- 템플릿 버그 수정 : `label`의 `for` 속성이 `input`의 `id`와 불일치 (497·505·557번 라인)
- 네비게이션 메뉴 한글화 + 메뉴 폰트 12px → 16px
- 기술(Services) 영역을 웹 / AI / 보안 / 게임 4개로 재구성, 부트스트랩 아이콘 교체
- 아이콘 팩을 최신 CDN(bootstrap-icons 1.13.1)으로 교체
- 상세 페이지는 컨트롤러 없이 `resources/public`에서 정적 제공

## 2주차 - 스프링 부트 개발환경, 테스트 완료

- [DemoController.java : /hello, /hello2 URL 매핑](src/main/java/com/example/demo/DemoController.java)
- [hello.html](src/main/resources/templates/hello.html)
- [hello2.html : 속성 5개 출력 (연습문제)](src/main/resources/templates/hello2.html)

## 개발 환경

- Spring Boot 4.1.1 / Java 21 / Gradle
- Thymeleaf 템플릿 엔진, 내장 Tomcat 8080 포트
- Bootstrap 5.1.3 (TemplateMo 578 First Portfolio)
- MySQL 8.0 / Spring Data JPA (Hibernate) / Lombok
- Spring Security 7.1 (세션 로그인, BCrypt)

## 실행

```
.\gradlew.bat bootRun
```

접속 : http://localhost:8080

## 2주차-연습문제

<img width="360" height="162" alt="스크린샷 2026-09-09 133103" src="https://github.com/user-attachments/assets/d7ea6ab8-7e1d-4dc4-82ed-6ec691017f6a" />
<img width="1912" height="722" alt="자바웹 2주차 연습문제" src="https://github.com/user-attachments/assets/8c820579-90bb-4778-a5ba-d667687720ac" />

