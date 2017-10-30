# Spring Data JPA

## 01) JPA(Java Persistence API)?

* Java를 이용해서 데이터를 관리, 유지하는 기업을 하나의 스펙으로 정리한 표준
* Java 진영에서 EJB라는 기술 스펙에서 엔티티 빈이라는 데이터를 처리하는 스펙을 정한 것이 JPA의 시초
* JPA는 ORM의 개념을 Java 언어에서 구현하기 위한 스펙으로 ORM이 일조의 비전에 불과하다면, JPA는 Java에서 이를 구현하기 위한 상세화한 통과기준
* 좀더 JPA를 쉽게 설명하자면 기존의 JDBC 등을 이용해서 직접 구현했던 데이터베이스 작업을 대신 처리해주는 추상화된 계층의 구현스펙이라고 할 수 있음

## 02) ORM(Object Relational Mapping)?

* ORM은 객체지향에서 말하는 객체와 데이터베이스에서 말하는 개체가 상당하 유사하다는 입장에서 시작한다.
* 예를 들어 회원정보의 클래스와 회원정보 테이블의 구조가 유사하다고 한다면 ORM에서는 이러한 유사한 점을 한번에 처리할 수 있지 않을까 라는 생각으로 시작하게 된다.
* ORM은 또한 특정한 언어에 종속적인 개념이 아니라 객체지향과 관계형 데이터베이스를 매핑시킨다는 추상화된 개념이다.

## 03) JPA의 장단점?

### 장점

* 데이터베이스 관련 코드에 대해 유연함을 얻을 수 있다.
* 데이터베이스와 독립적 관계이다.

### 단점

* 학습곡선이 크다
* 근복적인 객체지향 설계사상이 반영되어야한다.
* 특정 데이터베이스의 강력함을 사용할 수 없다는 문제점이 존재한다.

## 04) JPA 용어

### 1. 엔티티(Entity)

* 엔티티라는 용어는 데이터베이스상에서 데이터로 관리하는 대상을 의미한다.
* 예를 들어 '상품', '회사', '직원' 등과 같이 명사이면서 업무와 관련된 데이터를 엔티티로 규정할 수 있다.
* 데이터베이스에서는 엔티티를 위해 테이블을 설계하고, 데이터를 추가하는데 이렇게 추가된 데이터를 '인스턴스', '레코드'라고 호칭한다.
* JPA에서는 Java를 이용하여 이러한 엔티티를 관리하기 때문에 엔티티 타입의 존재는 항상 클래스가 된다.

### 2. 엔티티매니저(Entity Manager)

* 엔티티 객체들을 관리하는 역할을 한다.
* 여기서 말하는 관리는 'Life Cycle'을 의미하는데 엔티티 매니저는 자신이 관리해야할 엔티티 객체들을 '영속성 컨텍스트(Persistence Context)'라는 곳에 넣어두고, 객체들을 관리한다.

### 3. 영속성 컨텍스트(Persistence Context)

* 영속성 컨텍스트란 JPA가 엔티티 객체들을 모아두는 공간이라고 할 수 있다.
* 컨텍스트라는 용어는 하나의 공간이나 울타리라는 개념으로 이해하는 것이 좋다.

#### 엔티티 매니저를 통해 실행하는 메서드

1. New(비영속) : Java 영역에 객체만 존재하고, 데이터베이스와 연동된 적이 없는 상태, 순수한 Java 객체
2. Managed(영속) : 데이터베이스에 저장되고, 메모리상에서도 같은 상태로 존재하는 상태, 객체는 영속 컨텍스트에 들어가게되고, id(PK)값을 통해 엔티티 객체를 꺼내 사용할 수 있게 된다.
3. Removed(삭제) : 데이터베이스상에서 삭제된 상태, 객체는 더이상 영속 컨텍스트에 존재하지 않는다.
4. Detached(준영속) : 영속 컨텍스트에서 엔티티 객체를 꺼내서 사용하는 상태, 준영속 상태의 객체는 고유한 id(PK)를 가지고 있지만, 아직 데이터베이스 동기화가 이루어지지 않은 상태를 의미한다.

## 04) Spring Data JPA 프로젝트 생성 및 테스트

1. Lombok, JPA, Mysql 선택
2. DataSource 설정 (application.properties)

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
    logging.level.org.hibernate=debug
    
    # mysql 상세 지정
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
    ```
    
3. 엔티티 클래스 설계
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
    
4. 테스트 코드
    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class BoardRepositoryTests {
    
        @Autowired
        private BoardRepository boardRepository;
    
        @Test
        public void inspect() {
    
            // 실제 객체의 클래스 이름
            Class<?> clz = boardRepository.getClass();
            System.out.println(clz.getName());
    
            // 클래스가 구현하고 있는 인터페이스 목록
            Class<?>[] interfaces = clz.getInterfaces();
            Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));
    
            // 클래스의 부모 클래스
            Class<?> superClasses = clz.getSuperclass();
            System.out.println(superClasses.getName());
    
        }
    
        // 입력
        @Test
        public void testInsert() {
            Board board = new Board();
            board.setTitle("제목..");
            board.setContent("내용...");
            board.setWriter("홍길동");
            boardRepository.save(board);
        }
    
        // 조회
        @Test
        public void testRead() {
            Board board = boardRepository.findOne(1L);
            System.out.println(board);
        }
    
        // 수정
        @Test
        public void testUpdate() {
            System.out.println("Read First...");
            Board board = boardRepository.findOne(1L);
    
            System.out.println("Update Title...");
            board.setTitle("수정된 제목...");
    
            System.out.println("Call Save...");
            boardRepository.save(board);
        }
    
        // 삭제
        @Test
        public void testDelete() {
            System.out.println("Delete Entity...");
            boardRepository.delete(1L);
        }
    }
    ```