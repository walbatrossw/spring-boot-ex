
# SpringBoot 예제

## 1. 개발 환경

- IDE : IntelliJ
- SpringBoot : 1.5.17.RELEASE / 2.1.0.RELEASE
- Maven
- Java 8
- MySQL
- JPA
- Thymeleaf
- Spring Security

## 2. bootdemo1 내용 : 프로젝트 생성 및 lombok

- SpringBoot 버전 : 1.5.17.RELEASE
- SpringBoot 프로젝트 생성
- Lombok 라이브러리
- Controller 테스트

## 3. bootdemo2 내용 : Spring Data JPA

- SpringBoot 버전 : 1.5.17.RELEASE
- JPA 간단 요약 정리
- 스프링부트 프로젝트 생성
- DataSource 설정
- Entity 클래스 설계
- Repository 인터페이스 설계
- Entity 테스트
- CRUD 테스트

## 4. bootdemo3 내용 : Spring Data JPA 단순 게시물 처리

- SpringBoot 버전 : 1.5.17.RELEASE
- 쿼리 연습
    - 쿼리 메서드
    - 페이징 처리, 정렬
    - Page<T> 타입
- @Query 애너테이션
- Querydsl 동적 SQL

## 5. bootdemo4 내용 : JPA 연관 관계

- Springboot 버전 : 2.1.0.RELEASE
- 연관관계 처리
    - 단방향
    - 양방향

## 6. bootdemo5 내용 : Thymeleaf 사용

- 객체를 화면에 출력하기
- 리스트를 화면에 출력하기
- 지역변수 선언, if ~ unless 제어처리
- 인라인 스타일로 Thymeleaf 사용
- Thymeleaf 유틸 객체
    - 날짜 관련
    - 숫자 관련
    - 문자 관련
- Thymeleaf 링크 처리
- Thymeleaf 레이아웃 기능

## 7. bootdemo6 내용 : 게시판 게시물 CRUD

- Spring MVC를 이용한 웹페이지 처리
- Lteadmin 적용
- 페이징 처리
- 게시물 CRUD 처리
- 게시물 입력 페이지 : summernote 에디터 적용


## 8. bootdemo7 내용 : 게시판 REST방식의 댓글 처리 JPA처리

- RestController 설계
- SB Admin 적용
- 댓글 Ajax방식으로 CRUD 처리
- 댓글 입력 영역 : summernote 에디터 적용
- 게시물 리스트의 댓글 갯수처리
    - 동적으로 JPQL 처리

## 9. bootdemo8 내용 : 스프링 웹 시큐리티

- 회원 권한 설계
- 로그인/로그아웃 설계
- 인증방식
    - JDBC를 이용한 인증처리
    - 사용자 정의 `UserDetailsService` 작성
    - `MemberRepository` 연동
- Thymeleaf에서 스프링 시큐리티 처리
- Remember-Me 인증처리
- MVC 애너테이션 처리(`@Secured`)
- `PasswordEncoder` 사용
- 회원가입 처리

## 10. bootdemo9 내용 : 게시판에 스프링 웹 시큐리티 적용하기
