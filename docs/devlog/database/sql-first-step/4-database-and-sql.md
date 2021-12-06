---
title: "[ SQL 첫걸음 ] 제 4장 데이터의 추가, 삭제, 갱신"
description: "[ SQL 첫걸음 ] 제 4장 데이터의 추가, 삭제, 갱신"
tags:
    - "2021"
    - SQL
---

# [ SQL 첫걸음 ] 제 4장 데이터의 추가, 삭제, 갱신

!!! note "참고"
    [SQL 첫걸음](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&barcode=9788968482311)을 참고로 공부한 내용입니다. 따라서 아래 명령어를 통해 나오게 되는 결괏값은 전부 해당 책을 통해 다운로드한 파일의 결과물입니다.


## 행 추가하기 - `INSERT`

데이터베이스에서는 행을 추가할 때 `INSERT` 명령을 사용합니다. 그 형태는 `INSERT INTO 테이블명 VALUES(값1, 값2, ...)`와 같습니다.

### `INSERT`로 행 추가하기

아래와 같은 구조의 테이블 `sample41`에 `INSERT` 명령어를 통해 행을 추가한다고 가정해봅시다. 이때 유의할점은 각 열의 자료형과 제약조건입니다.

```sql
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| no    | int         | NO   |     | NULL    |       |
| a     | varchar(30) | YES  |     | NULL    |       |
| b     | date        | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
```

* `no` : `int` 자료형입니다.
* `a` : `varchar` 자료형이고 그 최대 길이가 `30`입니다.
* `b` : `date` 자료형입니다.

`no`가 `1`이고 `a`가 `ABC`, `b`가 `2014-01-25`인 행을 추가하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample41 VALUES(1, 'ABC', '2014-01-25');

    Query OK, 1 row affected (0.02 sec)
    ```

</div>

이제 그 결과를 `SELECT` 명령을 통해 확인하면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  1 | ABC  | 2014-01-25 |
    +----+------+------------+
    1 row in set (0.00 sec)
    ```

</div>

### 값을 저장할 열 지정하기

`INSERT` 명령으로 행을 추가할 경우 값을 저장할 열을 지정할 수 있습니다. 위와 동일한 `sample41` 테이블에 `no`열과 `a`열만 지정해 행을 추가하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample41 (a, no) VALUES('XYZ', 2);

    Query OK, 1 row affected (0.00 sec)
    ```

</div>

`SELECT` 명령을 통해 그 결과를 확인해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  1 | ABC  | 2014-01-25 |
    |  2 | XYZ  | NULL       |
    +----+------+------------+
    2 rows in set (0.00 sec)
    ```

</div>

`b`열의 경우 별도의 값이 추가되지 않았기 때문에 `NULL` 값이 저장된 것을 알 수 있습니다. `DESC sample41;`를 통해 살펴본 테이블의 구조에서 `DEFAULT` 값으로 `NULL`이 지정되어 있기 때문입니다.

### `NOT NULL` 제약

행을 추가할 때 유효한 값이 없는 상태(`NULL`)로 두고 싶을 경우에는 `VALUES` 구에서 `NULL`로 값을 지정할 수 있습니다. 이때 유의할 점은 `NOT NULL` 제약이 걸려있는 경우입니다. `DESC sample41;`을 통해 테이블의 구조를 다시 한 번 살펴보면 아래와 같습니다. `no`열의 경우 `Null`이 `NO`로 되어 있기 때문에 `NULL` 값을 허용하지 않는다는 의미입니다.

```sql
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| no    | int         | NO   |     | NULL    |       |
| a     | varchar(30) | YES  |     | NULL    |       |
| b     | date        | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
```

따라서 아래와 같이 `no`열에 `NULL` 값을 삽입(`INSERT`)하면 오류가 발생합니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample41 VALUES(NULL, NULL, NULL);

    ERROR 1048 (23000): Column 'no' cannot be null
    ```

</div>

### `DEFAULT`

`Default`라는 항목은 명시적으로 값을 지정하지 않았을 경우 사용하는 초깃값을 의미합니다. `sample41` 테이블의 경우 그 초깃값이 `NULL`로 되어있기 때문에 `NULL`로 값이 삽입되었습니다. 다른 테이블 `smple411`를 살펴보면 그 구조가 아래와 같습니다.

```sql
+-------+------+------+-----+---------+-------+
| Field | Type | Null | Key | Default | Extra |
+-------+------+------+-----+---------+-------+
| no    | int  | NO   |     | NULL    |       |
| d     | int  | YES  |     | 0       |       |
+-------+------+------+-----+---------+-------+
```

`d`열의 `Default` 항목 값이 `0`인 것을 확인할 수 있습니다. 아래와 같이 값을 삽입(`INSERT`)하고 결과를 확인(`SELECT`)해보면 `Default` 항목의 값이었던 `0`이 저장된 것을 확인할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample411 (no) VALUES(1);

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample411;

    +----+------+
    | no | d    |
    +----+------+
    |  1 |    0 |
    +----+------+
    1 row in set (0.01 sec)
    ```

</div>

#### 암묵적으로 디폴트 저장

입력하는 열, 다시 말해 `d`열을 사용하지 않아도 그 기본값인 `0`이 자동으로 저장되는 것을 암묵적으로 저장한다고 합니다.

이와 반대로 `INERT INTO sample411 (no, d) VALUES(1, DEFAULT);`와 같은 형태로 명령문을 실행해도 `DEFAULT` 키워드를 통해 `d`열의 기본값인 `0`이 입력됩니다. 이처럼 기본값을 키워드(`DEFAULT`)를 통해 지정하는 것을 `DEFAULT`를 명시적으로 지정하는 방법이라고 합니다.


## 삭제하기 - `DELETE`

데이터베이스의 테이블에서 행을 삭제하기 위해서는 `DELETE` 명령을 사용합니다. 그 형태는 `DELETE FROM 테이블명 WHERE 조건식`과 같습니다.

### `DELETE`로 행 삭제하기

이전에 `INSERT` 명령을 통해 데이터를 삽입한 테이블 `sample41`에 행이 아래와 같이 존재한다고 가정해봅시다.

```sql
+----+------+------------+
| no | a    | b          |
+----+------+------------+
|  1 | ABC  | 2014-01-25 |
|  2 | XYZ  | NULL       |
+----+------+------------+
```

여기서 `DELETE FROM sample41;` 명령어를 사용할 경우 해당 테이블 내의 모든 행을 삭제하고 `WHERE` 구를 통해 조건을 붙여줄 경우 조건에 일치하는 행만 삭제됩니다. `no`열이 `2`인 행만 삭제(`DELETE`)하고 그 결과를 확인(`SELECT`)해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > DELETE FROM sample41 WHERE no=2;

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  1 | ABC  | 2014-01-25 |
    +----+------+------------+
    1 row in set (0.00 sec)
    ```

</div>

### `DELETE` 명령 구

`SELECT` 구와 마찬가지로 `WHERE` 구의 조건 또한 `AND`, `OR` 등의 연산을 통하여 조건식을 다채롭게 사용할 수 있습니다.

!!! info "정보"

    원래 `DELETE` 명령에 `ORDER BY` 구는 사용할 수 없지만 MySQL의 경우 `ORDER BY` 구와 `LIMIT` 구를 사용하여 삭제할 행을 지정할 수 있습니다.


## 데이터 갱신하기 - `UPDATE`

테이블의 셀에 저장되어 있는 값을 갱신하려면 `UPDATE` 명령을 사용합니다. 그 형태는 `UPDATE 테이블명 SET 열1 = 값1, 열2 = 값2, ... WHERE 조건식`과 같습니다.

### `UPDATE`로 데이터 갱신하기

아래와 같이 데이터가 저장되어 있는 테이블 `sample41`이 존재한다고 가정해봅시다.

```sql
+----+------+------------+
| no | a    | b          |
+----+------+------------+
|  1 | ABC  | 2014-01-25 |
+----+------+------------+
```

`no`열의 값이 `1`인 행의 `a`열 값을 `XYZ`로, `b`열의 값을 `NULL`로 변경(`UPDATE`)하고 확인(`SELECT`)하면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample41 SET a='XYZ', b=NULL WHERE no=1;

    Query OK, 1 row affected (0.00 sec)
    Rows matched: 1  Changed: 1  Warnings: 0

    $ mysql > SELECT * FROM sample41;

    +----+------+------+
    | no | a    | b    |
    +----+------+------+
    |  1 | XYZ  | NULL |
    +----+------+------+
    1 row in set (0.00 sec)
    ```

</div>

!!! warning "주의"

    `DELETE` 명령과 마찬가지로 `WHERE` 구를 생략할 경우 테이블 내의 모든 행이 갱신됩니다.

### `UPDATE`로 갱신할 경우 주의사항

예를 들어 아래와 같은 테이블 `sample41`이 존재한다고 가정해봅시다.

```sql
+----+------+------+
| no | a    | b    |
+----+------+------+
|  1 | XYZ  | NULL |
|  2 | ABC  | NULL |
+----+------+------+
```

이때 `UDATE sample41 SET no = no + 1;`과 같은 명령어를 사용할 경우 갱신 후의 값이 갱신 전의 값인 본래 값에서 `1`을 더한 결과입니다. 그 결과를 확인하면 `WHERE` 구를 생략했기 때문에 모든 행에 해당 갱신이 적용되었고 아래와 같이 `no`열이 전부 `1`씩 증가한 것을 확인할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample41 SET no = no + 1;

    Query OK, 2 rows affected (0.00 sec)
    Rows matched: 2  Changed: 2  Warnings: 0

    $ mysql > SELECt * FROM sample41;

    +----+------+------+
    | no | a    | b    |
    +----+------+------+
    |  2 | XYZ  | NULL |
    |  3 | ABC  | NULL |
    +----+------+------+
    2 rows in set (0.00 sec)
    ```

</div>

### 복수열 갱신

만약 복수열을 갱신할 경우 콤마(`,`)을 통해 열을 구분하여 갱신할 수 있습니다. 예를 들어 복수의 `a`열과 `b`열 모두를 갱신(`UPDATE`)하고 확인(`SELECT`)하면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample41 SET a='DEF', b='2021-12-06' WHERE no=3;

    Query OK, 1 row affected (0.00 sec)
    Rows matched: 1  Changed: 1  Warnings: 0

    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  2 | XYZ  | NULL       |
    |  3 | DEF  | 2021-12-06 |
    +----+------+------------+
    2 rows in set (0.00 sec)
    ```

</div>

#### `SET` 구의 실행 순서

여러 개의 열을 한 번에 갱신할 수 있어 편리하지만 갱신 처리의 순서 또한 존재하기 때문에 주의해서 사용해야 합니다. 예를 들어 아래 두 갱신은 처리 순서가 서로 다르기 때문에 결과가 달라집니다.

```sql
UPDATE sample41 SET no = no + 1, a = no;
UPDATE sample41 SET a = no, no = no + 1;
```

먼저 첫 번째 명령문 `UPDATE sample41 SET no = no + 1, a = no;`를 입력하고 확인(`SELECT`)해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample41 SET no = no + 1, a = no;

    Query OK, 2 rows affected (0.01 sec)
    Rows matched: 2  Changed: 2  Warnings: 0

    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  3 | 3    | NULL       |
    |  4 | 4    | 2021-12-06 |
    +----+------+------------+
    2 rows in set (0.00 sec)
    ```

</div>

다음으로 두 번째 명령문 `UPDATE sample41 SET a = no, no = no + 1;`을 입력하고 확인(`SELECT`)해보면 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample41 SET a = no, no = no + 1;

    Query OK, 2 rows affected (0.01 sec)
    Rows matched: 2  Changed: 2  Warnings: 0

    $ mysql > SELECT * FROM sample41;

    +----+------+------------+
    | no | a    | b          |
    +----+------+------------+
    |  3 | 2    | NULL       |
    |  4 | 3    | 2021-12-06 |
    +----+------+------------+
    2 rows in set (0.00 sec)
    ```

</div>

두 결괏값을 확인해보면 다른 것을 알 수 있습니다.

첫 번째 명령문 `SET no = no + 1, a = no`의 경우 `no`열의 값이 증가한 다음 그것을 `a`열에 대입하기 때문에 `no`열과 `a`열의 값이 동일해집니다.

반대로 두 번째 명령문 `SET a = no, no = no + 1`의 경우 먼저 `a`열에 기존 `no`열 값을 대입한 뒤에 `no`열 값이 증가하기 때문에 `no`열과 `a`열 값의 차이가 `1` 발생합니다.

!!! warning "주의"

    데이터베이스 제품에 따라 그 처리 방식이 달라집니다. MySQL의 경우 위와 같이 서로 다른 결괏값이 나오지만 Oracle에서는 기술한 식의 순서가 처리에 영향을 주지 않기 때문에 두 명령문의 결괏값이 동일합니다.


### `NULL`로 갱신하기

앞서 `b`열의 값을 `NULL`로 갱신한 것처럼 셀을 `NULL` 값으로 갱신할 수 있습니다. 이때 `NOT NULL` 제약이 걸려 있는 행을 `NULL` 값으로 갱신하려 시도할 경우 오류가 발생한다는 점에 유의해야 합니다.


## 물리삭제와 논리삭제

데이터베이스에서 데이터를 삭제하는 방법은 용도에 따라 크게 **물리삭제(Hard Delete)**와 **논리삭제(Soft Delete)**로 나뉩니다. 하지만 해당 삭제는 전용 SQL 명령이 따로 존재하지 않는 **시스템 설계 분야**에 관한 부분으로 시스템을 구축할 때 자주 사용하는 용어입니다.

### 두 종류의 삭제방법

**물리삭제(Hard Delete)**는 `DELETE` 명령을 사용해 직접 데이터를 삭제하는 방법을 의미합니다.

한편 **논리삭제(Soft Delete)**의 경우 실제로 행을 삭제하는 `DELETE`와 달리 행을 삭제하지 않고 `delete_flag` 같은 열을 통해 해당 열만 갱신하여 데이터를 보존하는 방법을 의미합니다. 실제 데이터 자체는 삭제되지 않고 존재하지만 `SELECT` 명령을 통해 검색을 하거나 해당 행을 참조할 때는 `delete_flag`열을 통해 검색되지 않거나 참조되지 않게 제외시킵니다. 이런 방법을 통해 결론적으로 행이 삭제된 것처럼 보이게 하는 것입니다.

!!! info "정보"

    **논리삭제**의 방법에는 `delete_flag` 외에도 여러 가지가 존재합니다. 예를 들어 NodeJS에서 Sequelize ORM을 사용할 경우 `delete_at`이라는 삭제 날짜 열을 활용하여 데이터를 삭제하지 않고 삭제 날짜를 입력해 삭제된 것처럼 보이게 합니다.

논리삭제를 사용하는 가장 큰 이유 중 하나는 데이터를 삭제하지 않기 때문에 삭제되기 이전 상태로 되돌리기 간편하다는 것입니다. 또한 데이터를 통해 서비스 측면에서 여러 가지 인사이트를 도출할 수 있기 때문에 삭제되는 데이터도 중요합니다.

그러나 반대로 데이터베이스에 저장해야 할 데이터가 그만큼 삭제되지 않고 유지되는 것이기 때문에 검색속도가 떨어지는 등의 단점이 존재합니다. 또한 애플리케이션 입장에서는 삭제임에도 불구하고 `DELETE` 명령이 아닌 `UPDATE` 명령을 사용하기 때문에 혼란을 야기합니다.

### 삭제방법 선택하기

어떤 방법으로 삭제할 것인지는 시스템의 특성이나 테이블에 저장되어 있는 데이터의 특성에 따라 다릅니다.

예를 들어 소셜 네트워킹 서비스의 경우 사용자 개인정보를 다루기 때문에 사용자가 탈퇴하면 개인정보 유출을 방지하는 측면에서 물리삭제를 선택하는 게 좋습니다.

반대로 이커머스 서비스의 경우 사용자가 주문을 취소하더라도 발주는 된 것이기 때문에 해당 정보가 주문 취소 이유를 분석하기 위한 통계 등 유용하게 사용될 수 방법이 많아 논리삭제를 선택합니다.

한편으로는 하드웨어의 제한으로 물리삭제를 할 수밖에 없는 경우도 존재합니다. 앞서 논리삭제의 단점에서 결국 데이터베이스 내에 데이터가 삭제되지 않고 존재하는 것이기 때문에 저장할 수 있는 데이터의 크기는 메모리에 한정되어 있기 때문입니다. 이럴 때는 어쩔 수 없이 물리삭제를 사용해야 합니다.