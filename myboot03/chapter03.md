# Spring Data JPA를 이용한 단순 게시물 처리

학습내용

1. 쿼리 메소드라는 메소드의 이름만으로 원하는 SQL을 실행시키는 방법
2. `@Query`를 이용하여 구체화된 JPQL 처리
3. 페이징과 정렬
4. Querydsl을 이용한 동적쿼리

## 1) 스프링부트 프로젝트 생성 및 설정, 클래스와 인터페이스 작성

1. 스프링부트 프로젝트 생성 : DevTools / Lombok / JPA / MySql / Web 선택
2. `application.properties` 설정

  ```
   # 데이터베이스 JDBC 설정
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/jpa_ex?useSSL=false
   spring.datasource.username=jpa_user
   spring.datasource.password=jpa_user

   # 스키마 생성
   spring.jpa.hibernate.ddl-auto=update

   # DDL 생성시 데이터베이스의 고유의 기능을 사용하지 않음
   spring.jpa.generate-ddl=false

   # SQL 문 콘솔 출력
   spring.jpa.show-sql=true

   # 데이터베이스
   spring.jpa.database=mysql

   # 로그 레벨
   logging.level.org.hibernate=info

   # mysql 상세 지정
   spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
  ```

3. Board 클래스, BoardRepository 인터페이스 작성

  ```java
   @Getter
   @Setter
   @ToString
   @Entity
   @Table(name = "tbl_boards")
   public class Board {

       @Id // 기본키
       @GeneratedValue(strategy = GenerationType.AUTO) // 식별키를 부여하는 방식, 자동
       private Long bno;
       private String title;
       private String writer;
       private String content;
       @CreationTimestamp  // 엔티티가 생성되는 시점
       private Timestamp regdate;      // LocalDateTime
       @UpdateTimestamp    // 엔티티가 수정되는 시점
       private Timestamp updatedate;   // LocalDateTime
   }
  ```

  ```java
   public interface BoardRepository extends CrudRepository<Board, Long> {

   }
  ```

## 2) 쿼리 메서드

- Spring Data JPA는 메서드 이름만으로 원하는 Query를 실행할 수 있는 방법을 제공한다.

  - find...by...
  - read...by...
  - query...by...
  - get...by...
  - count...by...

- 쿼리 메서드의 리턴 타입은 `Page<T>`, `Slice<T>`, `List<T>`와 같은 `Collection` 형태가 될 수 있다.

- 예를 들어 제목으로 게시물을 검색한다고 한다면 `findBoardByTitle`라는 메서드 명으로 작성하면 된다.

  ```java
    public interface BoardRepository extends CrudRepository<Board, Long> {
        public List<Board> findBoardByTitle(String title);
    }
  ```

1. findBy를 이용한 특정 칼럼처리
    * 특정 칼럼을 조회할 경우 쿼리메서드의 이름을 `findBy`로 시작하는 방식을 이용
      ```java
      Collection<T> findBy + 속성 이름(속성 타입)
      ```

2. like 구문처리
    | 형태 | 쿼리 메서드 |
    |---|---|
    | 단순 like | Like |
    | 키워드 + '%' | StartingWith |
    | '%' + 키워드 | EndingWith |
    | '%' + 키워드 + '%' | Containing |

3. and 혹은 or 조건 처리
    * 경우에 따라 2개 이상의 속성을 이용해서 엔티티를 검색해야할 경우 쿼리 메서드에 `And`와 `Or`를 사용한다.

4. 부등호 처리
    * 쿼리 메서드에서 `>`, `<` 같은 부등호는 `GreaterThan`과 `LessThan`을 이용하여 처리한다.

5. order by 처리
    * 데이터를 가져오는 순서를 지정하기 위해서는 `OrderBy + 속성 + ASC or DESC`를 이용한다.

6. 페이징 처리와 정렬
    * 쿼리 메서들에는 특이하게도 모든 쿼리 메서드의 마지막 파라미터로 페이징 처리를 할 수 있는 `Pageable` 인터페이스와 정렬을 처리하는 `Sort` 인터페이스를 사용할 수 있다.

7. `page<T>` 타입
    * Spirng Data JPA에서 결과 데이터가 여러 개인 경우 `List<T>` 타입을 이용하기도 하지만 `Page<T>` 타입을 이용하면 Spring MVC와 연동할 때 상당한 편리함을 제공해준다.

## 3) `@Query` 이용

## 4) Querydsl을 이용한 동적 SQL의 처리
