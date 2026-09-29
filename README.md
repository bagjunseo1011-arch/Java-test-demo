# 자바웹프로그래밍(2) 실습

미디어소프트웨어학과 20230990 박준서

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

### DB 비밀번호 설정 (clone 후 실행 시)
비밀번호는 공개 저장소에 올라가지 않도록 `.gitignore` 처리된 파일에 따로 둡니다.
`src/main/resources/secret.properties` 파일을 만들고 아래 한 줄을 넣으세요.

```
spring.datasource.password=본인_MySQL_root_비밀번호
```

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

## 실행

```
.\gradlew.bat bootRun
```

접속 : http://localhost:8080

## 2주차-연습문제

<img width="360" height="162" alt="스크린샷 2026-09-09 133103" src="https://github.com/user-attachments/assets/d7ea6ab8-7e1d-4dc4-82ed-6ec691017f6a" />
<img width="1912" height="722" alt="자바웹 2주차 연습문제" src="https://github.com/user-attachments/assets/8c820579-90bb-4778-a5ba-d667687720ac" />

