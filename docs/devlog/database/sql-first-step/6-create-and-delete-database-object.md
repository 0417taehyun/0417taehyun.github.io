---
title: "[ SQL 첫걸음 ] 제 6장 데이터베이스 객체 작성과 삭제"
description: "[ SQL 첫걸음 ] 제 6장 데이터베이스 객체 작성과 삭제"
tags:
    - "2021"
    - SQL
---

# [ SQL 첫걸음 ] 제 6장 데이터베이스 객체 작성과 삭제

!!! note "참고"

    [SQL 첫걸음](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&barcode=9788968482311)을 참고로 공부한 내용입니다. 따라서 아래 명령어를 통해 나오게 되는 결괏값은 전부 해당 책을 통해 다운로드한 파일의 결과물입니다.


## 데이터베이스 객체

### 데이터베이스 객체

데이터베이스 객체란 **테이블**이나 **뷰**, **인덱스** 등 데이터베이스 내에 정의하는 모든 것을 일컫는 말로 구체적으로 이야기하면 **실체**를 가지는 어떤 것입니다. 따라서 객체의 종류에 따라 데이터베이스에 저장되는 내용도 달라집니다.

쉽게 테이블은 객체이며 해당 테이블을 조작할 수 있는 명령이었던 `SELECT`, `INSERT` 등은 객체가 아닙니다.

!!! warning "주의"

    C++, 자바, 파이썬 등의 프로그래밍에서 사용하는 객체지향 프로그래밍의 객체와 혼동해서는 안 됩니다.

    데이터베이스에서의 **레코드**, 다시 말해 필드의 집합을 **개체(Entity)**라고 합니다. 쉽게 말해 개체는 일종의 **정보**를 표현하는 단위로 한글로는 **실체**로 번역되기도 합니다.

    이러한 맥락에서 사실 해당 책에서 사용되는 객체(Object)는 개체(Entity)로 표현되는 게 더 맞을 수도 있으나 우선은 책에는 객체라 표현되어 있기에 해당 용어가 프로그래밍에서 사용되는 객체와는 다르다는 점에 유의해야 합니다.

객체는 **이름**을 가집니다. 따라서 데이터베이스 내에서 객체를 작성할 때는 이름이 중복되지 않게 해야 합니다. 테이블의 열이나 `SELECT` 명령에서의 별명(`AS`) 또한 이름을 갖지만 이것들은 모두 객체가 아닙니다. 실체가 존재하지 않기 때문입니다. 다만 이름을 붙일 때는 제약 사항, 다시 말해 아래와 같은 **명명 규칙(Naming Convention)**을 따라야 합니다.

* 기존 이름이나 예약어와 중복하지 않는다.
* 숫자로 시작할 수 없다.
* 언더스코어(`_`) 이외의 기호는 사용할 수 없다.
* 한글을 사용할 때는 더블쿼트(`""`) (MySQL에서는 백쿼트(`''`)) 로 둘러싼다.
* 시스템이 허용하는 길이를 초과하지 않는다.

이름은 어떤 데이터가 저장되어 있는지 파악하는 기준이 되는 경우가 많기 때문에 단순히 `a`와 같이 무의미한 이름이 아닌 연관된 유의미한 이름으로 짓는 게 중요합니다.

### 스키마

객체는 **스키마(Schema)**라는 그릇 안에서 만들어집니다. 따라서 객체의 이름이 같아도 스키마가 서로 다르면 상관없습니다. 이러한 특징 때문에 데이터베이스 객체는 **스키마 객체(Schema Object)**라 불리기도 합니다. 또한 테이블을 작성해서 구축해나가는 작업을 **스키마 설계(Schema Design)**라고 부릅니다. 이때 스키마는 SQL 명령의 **DDL(Data Definition Language)**을 이용하여 정의합니다.

조금 더 구체적인 예시로 MySQL의 경우 `CREATE DATABASE` 명령으로 작성한 데이터베이스가 곧 **스키마**가 됩니다. Oracle 등에서는 데이터베이스와 데이터베이스 사용자가 **계층적 사용자**가 됩니다.

!!! info "정보"

    동일한 스키마 또는 테이블 내에서 이름이 중복되어 충돌되지 않게 각각의 이름이 가지는 범위를 **네임스페이스(Namespace)**라고 합니다.

    따라서 스키마나 테이블은 **네임스페이스(Namespace)**이기도 합니다.



## 테이블 작성, 삭제, 변경

데이터베이스 객체인 테이블을 작성, 삭제, 변경하는 명령을 **DDL(Data Definition Language)**이라 합니다.

### 테이블 작성

`CREATE` 명령을 사용하여 테이블, 뷰, 인덱스 등의 객체를 작성할 수 있습니다. 그 형태는 간단하게 표현해보면 아래와 같습니다.

```sql
CREATE TABLE 테이블명 (
    열 정의1,
    열 정의2,
    ...
)
```

열을 정의할 때는 열명을 붙이고 자료형으로 `INTEGER`, `VARCHAR` 등을 지정합니다. 특히 `CHAR` 또는 `VARCHAR`의 경우 문자열형이기 때문에 최대길이를 괄호(`()`)를 사용하여 함께 지정해줘야 합니다. 또한 열을 정의할 때 `DEFAULT` 키워드를 사용하여 기본값을 설정할 수 있습니다. 마지막으로 `NULL`을 허용할 것인지 지정해야 합니다. 생략했을 때는 `NULL`을 허용하는 것으로 인지합니다.

최대 길이가 `8`인 문자열형 `name`열, 기본값으로 현재 시간을 저장하는 날짜시간형 `create_at`열, 그리고 `no`열을 가지는 테이블 `sample62`를 만드는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > CREATE TABLE sample62 (
        -> no INTEGER NOT NULL,
        -> name VARCHAR(8) NOT NULL,
        -> created_at DATETIME DEFAULT CURRENT_TIMESTAMP
        -> );

    Query OK, 0 rows affected (0.02 sec)

    $ mysql> DESC sample62;

    +------------+------------+------+-----+-------------------+-------------------+
    | Field      | Type       | Null | Key | Default           | Extra             |
    +------------+------------+------+-----+-------------------+-------------------+
    | no         | int        | NO   |     | NULL              |                   |
    | name       | varchar(8) | NO   |     | NULL              |                   |
    | created_at | datetime   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    +------------+------------+------+-----+-------------------+-------------------+
    3 rows in set (0.00 sec)
    ```

</div>

`INSERT` 명령을 통해 실제로 데이터를 입력해보고 확인해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample62 (no, name) VALUES (1, '테스트');

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample62;

    +----+-----------+---------------------+
    | no | name      | created_at          |
    +----+-----------+---------------------+
    |  1 | 테스트    | 2021-12-08 17:52:55 |
    +----+-----------+---------------------+
    1 row in set (0.00 sec)
    ```

</div>

!!! info "정보"

    날짜시간형의 경우 현재 시간을 기본값으로 사용하고 싶다면 MySQL에서는 `CURRENT_TIMESTAMP`, Oracle에서는 `SYSDATE`를 사용합니다.

### 테이블 삭제

`DROP` 명령을 통해 필요없는 객체를 삭제할 수 있씁니다. 이때 유의할 점은 많은 데이터베이스가 삭제 명령에 따로 확인을 요구하지 않는다는 것입니다. 따라서 실수로 삭제하지 않게 조심해야 합니다.

앞서 만든 테이블 `sample62`를 삭제하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > DROP TABLE sample62;

    Query OK, 0 rows affected (0.00 sec)

    $ mysql > DESC sample62;

    ERROR 1146 (42S02): Table 'sample.sample62' doesn't exist
    ```

</div>

#### 데이터 행 삭제

테이블 정의는 그대로 둔 채 데이터, 다시 말해 행만 삭제할 때는 `DROP`이 아닌 `DELETE` 명령을 사용합니다. 이때 `WHERE` 구를 사용하여 조건을 지정하지 않으면 테이블의 모든 행이 삭제됩니다.

그러나 `DELETE` 명령의 경우 행 단위로 내부처리가 일어나기 때문에 삭제할 행이 많으면 처리속도가 늦어집니다. 따라서 테이블 내의 모든 행을 삭제해야 할 때 빠른 속도의 작업 처리가 필요하다면 DDL로 분류되는 `TRUNCATE TABLE` 명령을 사용할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > TRUNCATE TABLE sample62;

    Query OK, 0 rows affected (0.01 sec)

    $ mysql > SELECT * FROM sample62;

    Empty set (0.00 sec)
    ```

</div>

### 테이블 변경

테이블을 작성한 뒤에도 열 구성은 변경 가능합니다. `ALTER` 명령을 통해 객체를 변경할 수 있습니다.

#### 열 추가

`ALTER TABLE` 명령을 통해 테이블의 열 구성을 변경하는데 열을 추가하려면 `ADD` 하부명령을 사용합니다. 이때 열을 정의하는 방법은 `CREATE TABLE` 때와 동일합니다.

이전에 만들었던 테이블 `sample62`에 최대길이가 `16`이고 `VARCHAR`형인 열 `nickname`을 추가하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 ADD (
        -> nickname VARCHAR(16) NOT NULL
        -> );

    Query OK, 0 rows affected (0.02 sec)
    Records: 0  Duplicates: 0  Warnings: 0

    $ mysql> DESC sample62;

    +------------+-------------+------+-----+-------------------+-------------------+
    | Field      | Type        | Null | Key | Default           | Extra             |
    +------------+-------------+------+-----+-------------------+-------------------+
    | no         | int         | NO   |     | NULL              |                   |
    | name       | varchar(8)  | NO   |     | NULL              |                   |
    | created_at | datetime    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | nickname   | varchar(16) | NO   |     | NULL              |                   |
    +------------+-------------+------+-----+-------------------+-------------------+
    4 rows in set (0.01 sec)
    ```

</div>

#### 열 속성 변경

`ALTER TABLE` 명령에서 열 속성을 변경하라면 `MODIFY` 하부명령을 사용합니다. 이때 열을 정의하는 방법은 `CREATE TABLE` 때와 동일합니다.

테이블 `sample62`에 존재하는 `nickname` 열의 제약조건을 `NULL` 값을 허용하는 걸로 변경하고 최대길이 또한 `8`로 줄이는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 MODIFY nickname VARCHAR(8) NULL;

    Query OK, 0 rows affected (0.02 sec)
    Records: 0  Duplicates: 0  Warnings: 0

    $ mysql > DESC sample62;

    +------------+------------+------+-----+-------------------+-------------------+
    | Field      | Type       | Null | Key | Default           | Extra             |
    +------------+------------+------+-----+-------------------+-------------------+
    | no         | int        | NO   |     | NULL              |                   |
    | name       | varchar(8) | NO   |     | NULL              |                   |
    | created_at | datetime   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | nickname   | varchar(8) | YES  |     | NULL              |                   |
    +------------+------------+------+-----+-------------------+-------------------+
    4 rows in set (0.00 sec)
    ```

</div>

#### 열 이름 변경

`ALTER TABLE` 명령에서 열의 이름을 변경하려면 `CHANGE` 하부명령을 사용합니다. 첫 번째로 기존 열 이름을 입력하고 뒤이어 신규 열 이름을 입력하면 기존 열 이름이 해당 신규 열 이름으로 변경됩니다.

!!! info "정보'

    `CHANGE` 하부명령은 열 이름 뿐만 아니라 열 속성도 변경할 수 있습니다.

    Oracle에서는 열 이름을 변경할 경우 `RENAME TO` 하부명령을 사용합니다.

테이블 `sample62`에 존재하는 `name`열의 이름을 `real_name`으로 변경하고 최대 길이 또한 `16`으로 변경하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 CHANGE name real_name VARCHAR(16);

    Query OK, 0 rows affected (0.02 sec)
    Records: 0  Duplicates: 0  Warnings: 0

    $ mysql > DESC sample62;

    +------------+-------------+------+-----+-------------------+-------------------+
    | Field      | Type        | Null | Key | Default           | Extra             |
    +------------+-------------+------+-----+-------------------+-------------------+
    | no         | int         | NO   |     | NULL              |                   |
    | real_name  | varchar(16) | YES  |     | NULL              |                   |
    | created_at | datetime    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | nickname   | varchar(8)  | YES  |     | NULL              |                   |
    +------------+-------------+------+-----+-------------------+-------------------+
    4 rows in set (0.00 sec)
    ```

</div>

#### 열 삭제

`ALTER TABLE` 명령에서 열을 삭제하려면 `DROP` 하부명령을 사용합니다. 뒤에 삭제하고 싶은 열명을 지정하면 됩니다.

테이블 `sample62`에 존재하는 `real_name`열을 삭제하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 DROP real_name;

    Query OK, 0 rows affected (0.01 sec)
    Records: 0  Duplicates: 0  Warnings: 0

    $ mysql > DESC sample62;

    +------------+------------+------+-----+-------------------+-------------------+
    | Field      | Type       | Null | Key | Default           | Extra             |
    +------------+------------+------+-----+-------------------+-------------------+
    | no         | int        | NO   |     | NULL              |                   |
    | created_at | datetime   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | nickname   | varchar(8) | YES  |     | NULL              |                   |
    +------------+------------+------+-----+-------------------+-------------------+
    3 rows in set (0.00 sec)
    ```

</div>

### `ALTER TABLE`로 테이블 관리

#### 최대길이 연장

대규모 데이터베이스에서는 데이터의 크기가 매우 커질 때가 많습니다. 특히 행 개수가 많은 테이블에서는 데이터 하나의 크기만을 최적화하더라도 저장공간을 효율적으로 관리할 수 있습니다. 이럴 때 `ALTER TABLE`을 활용하여 해당 열의 자료형만 변경하거나 문자열형의 경우 최대길이를 조절하여 저장공간을 관리할 수 있습니다.

이때 유의할 점은 데이터가 이미 저장되어 있을 때 해당 데이터가 변경하려는 자료형에 알맞지 않은 경우 오류가 발생한다는 것과 또한 최대길이도 마찬가지로 저장되어 있는 데이터의 길이가 만약 변경하려는 최대길이보다 클 경우 오류가 발생한다는 것입니다.

먼저 아래와 같이 데이터가 저장되어 있는 테이블 `sample62`가 있다고 가정해봅시다.

```sql
+----+---------------------+------------------------+
| no | created_at          | nickname               |
+----+---------------------+------------------------+
|  1 | 2021-12-08 19:42:07 | 테스트용 닉네임            |
+----+---------------------+------------------------+
```

만약 `nickname`열의 자료형을 INTEGER형으로 변결하려면 아래와 같은 오류가 발생합니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 MODIFY nickname INTEGER;

    ERROR 1366 (HY000): Incorrect integer value: '테스트용 닉네임' for column 'nickname' at row 1
    ```

</div>

또한 최대길이를 변경했을 때 이미 저장된 데이터의 길이가 변경하려는 길이보다 클 경우에 아래와 같은 오류가 발생합니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 MODIFY nickname VARCHAR(4);

    ERROR 1265 (01000): Data truncated for column 'nickname' at row 1
    ```

</div>

#### 열 추가

`ALTER TABLE ADD ...` 명령문을 통해 열을 추가하면 행을 추가하는 `INSERT` 명령을 꼭 확인해야 합니다. 추가된 열이 `NULL` 값을 허용하거나 `DEFAULT` 값이 존재하지 않는 이상 해당 열에 데이터 값을 지정해줘야 하기 때문입니다.

기존에 데이터가 존재하는 테이블 `sample62`에 아래와 같이 `NOT NULL` 제약조건을 걸어 `name`열을 추가한다고 가정해봅시다.

```sql
ALTER TABLE sample62 ADD name VARCHAR(4) NOT NULL;
```

기존 존재하던 행의 새로 추가된 `name`열 값과 `DESC` 명령을 통해 테이블 구조를 확인해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample62;

    +----+---------------------+------------------------+------+
    | no | created_at          | nickname               | name |
    +----+---------------------+------------------------+------+
    |  1 | 2021-12-08 19:42:07 | 테스트용 닉네임        |      |
    +----+---------------------+------------------------+------+
    1 row in set (0.00 sec)

    $ mysql > DESC sample62;

    +------------+------------+------+-----+-------------------+-------------------+
    | Field      | Type       | Null | Key | Default           | Extra             |
    +------------+------------+------+-----+-------------------+-------------------+
    | no         | int        | NO   |     | NULL              |                   |
    | created_at | datetime   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
    | nickname   | varchar(8) | YES  |     | NULL              |                   |
    | name       | varchar(4) | NO   |     | NULL              |                   |
    +------------+------------+------+-----+-------------------+-------------------+
    4 rows in set (0.01 sec)
    ```

</div>

추가된 `name`열의 `Default` 값이 `NULL`로 되어 있음에도 제약조건이 `NOT NULL`로 되어 있기 때문에 기본값이 빈 문자열, 다시 말해 `''`로 추가된 것을 확인할 수 있습니다.

따라서 `WHERE` 구와 함께 `IS NULL`과 `= ''`를 조건으로 검색(`SELECT`)하면 결과는 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample62 WHERE name IS NULL;

    Empty set (0.00 sec)

    $ mysql > SELECT * FROM sample62 WHERE name = '';

    +----+---------------------+------------------------+------+
    | no | created_at          | nickname               | name |
    +----+---------------------+------------------------+------+
    |  1 | 2021-12-08 19:42:07 | 테스트용 닉네임        |      |
    +----+---------------------+------------------------+------+
    1 row in set (0.00 sec)
    ```

</div>

INTEGER형의 경우는 아래와 같이 제약조건이 `NOT NULL`일 때 기본값으로 `0`이 입력되는 것을 확인할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > ALTER TABLE sample62 ADD test INTEGER NOT NULL;

    Query OK, 0 rows affected (0.00 sec)
    Records: 0  Duplicates: 0  Warnings: 0

    $ mysql > SELECT * FROM sample62;

    +----+---------------------+------------------------+------+------+
    | no | created_at          | nickname               | name | test |
    +----+---------------------+------------------------+------+------+
    |  1 | 2021-12-08 19:42:07 | 테스트용 닉네임            |      |    0 |
    +----+---------------------+------------------------+------+------+
    1 row in set (0.01 sec)
    ```

</div>

!!! info "정보"

    제약조건이 `NULL`값을 허용하고 별다른 `DEFAULT` 키워드를 통한 기본값 설정이 없을 경우에는 자료형에 상관없이 기본값이 전부 `NULL`로 입력됩니다.


## 제약

`CREATE TABLE`로 테이블을 정의할 때 `NOT NULL`과 같은 제약 또한 정의할 수 있습니다. 이러한 제약은 저장될 데이터를 단어 의미 그대로 제한하는 역할을 합니다. `NOT NULL` 외에도 대표적인 제약조건으로는 **기본기(Primary Key)** 제약이나 **외부참조(정합)** 제약 등이 있습니다.

### 테이블 작성시 제약 정의

`NOT NULL` 제약조건을 가진 `name`열과 `NOT NULL` 제약조건 및 `UNIQUE` 제약조건을 가진 `no`열로 구성된 테이블 `sample531`을 만드는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > CREATE TABLE sample531 (
        -> no INTEGER NOT NULL UNIQUE,
        -> name VARCHAR(8) NOT NULL
        -> );

    Query OK, 0 rows affected (0.01 sec)

    $ mysql > DESC sample531;

    +-------+------------+------+-----+---------+-------+
    | Field | Type       | Null | Key | Default | Extra |
    +-------+------------+------+-----+---------+-------+
    | no    | int        | NO   | PRI | NULL    |       |
    | name  | varchar(8) | NO   |     | NULL    |       |
    +-------+------------+------+-----+---------+-------+
    2 rows in set (0.01 sec)
    ```

</div>

이처럼 열에 대해 정의하는 제약을 **열 제약**이라 합니다.

`NOT NULL` 제약조건 및 기본키(`PRIMARY KEY`) 제약조건을 가진 `no`열과 `sub_no`열, 그리고 아무런 제약조건을 가지고 있지 않은 `name`열로 구성된 테이블 `sample632`를 만드는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > CREATE TABLE sample632 (
        -> no INTEGER NOT NULL,
        -> sub_no INTEGER NOT NULL,
        -> name VARCHAR(8),
        -> PRIMARY KEY (no, sub_no)
        -> );

    Query OK, 0 rows affected (0.01 sec)

    $ mysql > DESC sample632;

    +--------+------------+------+-----+---------+-------+
    | Field  | Type       | Null | Key | Default | Extra |
    +--------+------------+------+-----+---------+-------+
    | no     | int        | NO   | PRI | NULL    |       |
    | sub_no | int        | NO   | PRI | NULL    |       |
    | name   | varchar(8) | YES  |     | NULL    |       |
    +--------+------------+------+-----+---------+-------+
    3 rows in set (0.00 sec)
    ```

</div>

### 제약 추가

기존에 존재하던 테이블에도 나중에 제약을 따로 추가할 수 있습니다. 이때 **열 제약**과 **테이블 제약**이라는 두 가지 방법이 존재합니다.

#### 열 제약 추가

`ALTER TABLE`로 열 정의를 변경할 때 

#### 테이블 제약 추가

### 제약 삭제

### 기본키



#### 복수의 열로 기본키 구성하기

기본키 제약에는 이를 구성할 열 지정이 필요하며 이때 지정된 열은 `NOT NULL` 제약이 설정되어 있어야 합니다. 다시 말해 기본키로는 `NULL` 값이 허용되지 않습니다.

이때 기본키를 구성하는 열은 복수여도 무관합니다. 따라서 아래와 같이 `a`열과 `b`열이 모두 기본키인 테이블 `sample635`의 경우 하나의 열만 봤을 때는 중복되는 값이 존재하기 때문에 기본키 제약에 위반되지만 `a`열과 `b`열을 하나의 쌍으로 봤을 때는 `(1, 1)`, `(1, 2)`와 같이 전부 중복되지 않는 쌍이기 때문에 기본키 제약에 위반되지 않습니다.

```sql
+---+---+
| a | b |
+---+---+
| 1 | 1 |
| 1 | 2 |
| 1 | 3 |
| 2 | 1 |
| 2 | 2 |
+---+---+
```


## 인덱스 구조

### 인덱스

**인덱스(Index)**는 **색인**이라고도 불리며 그 역할은 검색속도를 향상시키는 데 있습니다. 여기서 검색은 `SELECT` 명령 및 `WHERE` 구를 통해 조건을 지정하여 그에 알맞는 행을 찾는 과정을 의미합니다. 테이블에 인덱스가 존재하면 효율적인 검색이 가능해져 `WHERE` 구로 조건이 지정된 `SELECT` 명령의 처리 속도가 향상됩니다.

예를 들어 목차를 생각하면 편합니다. 백과사전에 목차가 존재하지 않으면 `과일`이라는 단어를 찾을 때 앞에서부터 하나씩 다 살펴봐야 합니다. 그러나 목차가 존재하면 해당 목차를 통해 `과`가 일치하는 곳으로 바로 가서 훨씬 효율적으로 찾을 수 있습니다.

인덱스 또한 이처럼 검색에 사용되는 **키워드**와 대응하는 **행의 장소**가 저장되어 있습니다. 이때 인덱스는 테이블과는 별개로 독립된 데이터베이스 객체로 작성됩니다. 그러나 인덱스는 결국 테이블에 종속되어 있기 때문에 테이블이 없으면 아무런 의미가 없어 테이블이 삭제될 때 함께 삭제됩니다.

### 검색에 사용하는 알고리즘

데이터베이스의 인덱스에 쓰이는 대표적인 검색 알고리즘은 이진 트리(Binary Tree)를 활용한 **이진 검색(Binary Serach)** 또는 **해시(Hash)**가 있습니다.

#### 풀 테이블 스캔(Full Table Scan)

인덱스가 지정되지 않은 테이블을 검색할 때는 **풀 테이블 스캔(Full Table Scan)**이라 불리는 검색 방법을 사용합니다. 이르 그대로 테이블에 저장된 모든 값을 처음부터 차례로 조사해나갑니다. 매우 단순한 방법으로 만약 행이 100개가 존재한다면 값을 최대 100번 비교합니다.

#### 이진 탐색(Binary Search)

**이진 탐색(Binary Search)**은 집합을 반으로 나누어 조사하는 방법으로 차례로 나열된 집합에 대해 유요한 검색 방법입니다. 

```
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| 1 | 2 | 3 | 5 | 10 | 11 | 19 | 20 | 23 | 30 | 31 | 32 | 38 | 40 | 100 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```


#### 이진 트리(Binary Tree)

이진 탐색은 고속으로 검색이 가능하지만 데이터가 미리 정렬되어 있어야 하는 단점이 있습니다. 테이블 내의 모든 행을 언제나 정렬된 상태로 두는 것은 어렵기 때문에 이럴 때 테이블 데이터와 별개로 인덱스용 데이터를 저장장치에 만듭니다. 이때 사용하는 자료구조가 바로 **이진 트리(Binary Tree)**입니다.

### 유일성

이진 트리에서는 동일한 값을 가진 노드가 존재할 수 없습니다. 따라서 무조건적으로 대소비교가 가능하여 가지가 두 개 생성됩니다. 이러한 **유일성**을 위해 결국 데이터베이스 또한 기본키 제약을 통하여 유일한 값을 가지게 해야 합니다.


## 인덱스 작성과 삭제

### 인덱스 작성

### 인덱스 삭제

### `EXPLAIN`

### 최적화



## 뷰 작성과 삭제

### 뷰

### 뷰 작성과 삭제

### 뷰의 약점
