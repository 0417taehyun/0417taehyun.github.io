---
title: "[ SQL 첫걸음 ] 제 5장 집계와 서브쿼리"
description: "[ SQL 첫걸음 ] 제 5장 집계와 서브쿼리"
tags:
    - "2021"
    - SQL
---

# [ SQL 첫걸음 ] 제 5장 집계와 서브쿼리

!!! note "참고"

    [SQL 첫걸음](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&barcode=9788968482311)을 참고로 공부한 내용입니다. 따라서 아래 명령어를 통해 나오게 되는 결괏값은 전부 해당 책을 통해 다운로드한 파일의 결과물입니다.


## 행 개수 구하기 - `COUNT`

SQL은 데이터베이스라 불리는 데이터 **집합**을 다루는 언어입니다. 따라서 집합의 개수나 합계를 구하기 위해 **집계함수**를 사용할 수 있습니다. SQL에서 사용하는 대표적인 집계함수는 아래와 같습니다.

* `COUNT`
* `SUM`
* `AVG`
* `MIN`
* `MAX`

이때 유의할 점은 일반적인 함수는 인수로 하나의 **값**을 지정하는 데 집계함수는 인수로 **집합**을 지정합니다.

### `COUNT`로 행 개수 구하기

`COUNT`는 테이블의 행 개수를 구하는 집계함수입니다.

아래와 같은 테이블 `sample51`이 존재한다고 가정해봅시다.

```sql
+------+------+----------+
| no   | name | quantity |
+------+------+----------+
|    1 | A    |        1 |
|    2 | A    |        2 |
|    3 | B    |       10 |
|    4 | C    |        3 |
|    5 | NULL |     NULL |
+------+------+----------+
```

`COUNT` 집계함수를 사용하여 행 개수를 구하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT COUNT(*) FROM sample51;

    +----------+
    | COUNT(*) |
    +----------+
    |        5 |
    +----------+
    1 row in set (0.00 sec)
    ```

</div>

인자 값으로 애스터리스크(`*`)가 지정되어 있는 건 `SELECT` 구에서 모든 열을 나타낼 때 사용하는 메타문자와 동일한 의미입니다. 다만 앞서 설명한 것처럼 집계함수에서는 집합을 인수로 하기 때문에 이 경우 모든 열이 아닌 **테이블 전체**를 의미합니다. 다시 말해 테이블 전체에서 행의 갯수가 5개이기 때문에 그 결괏값으로 `5`를 반환했습니다.

#### `WHERE` 구 지정하기

집합에서 하나의 값을 계산해내는 집계함수의 특징 때문에 `WHERE` 구의 유무와 관계없이 결괏값으로 하나의 행을 반환합니다. 단순 `SELECT`를 통한 검색과 `COUNT`를 사용한 결과 비교는 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample51 WHERE name='A';

    +------+------+----------+
    | no   | name | quantity |
    +------+------+----------+
    |    1 | A    |        1 |
    |    2 | A    |        2 |
    +------+------+----------+
    2 rows in set (0.00 sec)

    $ mysql > SELECT COUNT(*) FROM sample51 WHERE name='A';

    +----------+
    | COUNT(*) |
    +----------+
    |        2 |
    +----------+
    1 row in set (0.00 sec)
    ```

</div>

우리는 앞서 `SELECT` 구의 처리 순서가 `WHERE` 구 다음이라는 걸 확인했습니다. 따라서 `WHERE` 구를 통해 지정된 조건에 맞는 테이블의 행만 검색이 되고 이후 해당 테이블에 집계함수가 사용되어 결괏값으로 `2`가 반환되었습니다.


### 집계함수와 `NULL`값

`COUNT`의 인수로 열명을 지정할 수 있습니다. 열명을 지정하면 그 열에 한해서 행의 개수를 구할 수 있습니다. 여기서 유의할 점은 바로 `NULL` 값을 처리하는 방식입니다. 집계함수는 집합 안에 `NULL` 값이 존재할 경우 이를 제외하고 처리합니다.

아래 예시를 통해 `no`열과 `name`열의 `COUNT` 함수 결괏값이 `NULL` 때문에 다른 것을 확인할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT COUNT(no), COUNT(name) FROM sample51;

    +-----------+-------------+
    | COUNT(no) | COUNT(name) |
    +-----------+-------------+
    |         5 |           4 |
    +-----------+-------------+
    1 row in set (0.00 sec)
    ```

</div>


!!! warning "주의"

    인수로 애스터리스크(`*`)를 사용할 때는 모든 열의 행수를 카운트하기 때문에 `NULL`값이 있어도 해당 정보가 무시되지 않고 집계됩니다.

    유의할 점은 인수로 애스터리스크(`*`)를 사용할 수 있는 건 집계함수에서 오로지 `COUNT` 뿐이라는 사실입니다.

### `DISTINCT`로 중복 제거

중복된 값을 제거하기 위해서는 `DISTINC` 키워드를 사용하면 됩니다. 아래 예시를 통해 `ALL` 키워드를 명시적으로 사용하여 중복된 값도 표시하는 것과 `DISTINCT` 키워드를 사용하여 해당 열의 중복 값을 제외하고 표시하는 방법을 확인할 수 있습니다. 이때 키워드를 생략하면 `ALL`로 간주됩니다.

<div class="termy">
    ```sh
    $ mysql > SELECT ALL name FROM sample51;

    +------+
    | name |
    +------+
    | A    |
    | A    |
    | B    |
    | C    |
    | NULL |
    +------+
    5 rows in set (0.00 sec)

    $ mysql > SELECT DISTINCT name FROM sample51;

    +------+
    | name |
    +------+
    | A    |
    | B    |
    | C    |
    | NULL |
    +------+
    4 rows in set (0.00 sec)
    ```

</div>

### 집계함수에서 `DISTINCT`

예를 들어 중복된 값을 제외한 그 개수를 알고 싶을 때 집계함수인 `COUNT`와 함께 `DISTINCT` 키워드를 사용할 수 있습니다. 이때 유의할 점은 `COUNT` 집계함수의 인수로 `DISTINCT`를 사용한다는 것입니다. 그 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT COUNT(name), COUNT(DISTINCT name) FROM sample51;

    +-------------+----------------------+
    | COUNT(name) | COUNT(DISTINCT name) |
    +-------------+----------------------+
    |           4 |                    3 |
    +-------------+----------------------+
    1 row in set (0.00 sec)
    ```

</div>


## `COUNT` 이외의 집계함수

앞서 집계함수의 대표적인 종류를 살펴봤던 것처럼 `COUNT` 외에도 다양한 집계함수가 존재합니다. 그리고 이때 유의할 점은 `COUNT` 집계함수에서는 인수로 애스터리스크(`*`)를 사용할 수 있었지만 다른 집계함수에서는 불가능하다는 것입니다.

### `SUM`으로 합계 구하기

`SUM` 집계함수를 사용하여 합를 구할 수 있습니다. 이전 `COUNT` 집계함수에서 사용한 테이블 `sample51`의 `qauntity`열 합계를 구하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT SUM(quantity) FROM sample51;

    +---------------+
    | SUM(quantity) |
    +---------------+
    |            16 |
    +---------------+
    1 row in set (0.01 sec)
    ```

</div>

이때 유의할 점은 `SUM` 집계함수가 지정할 수 있는 집합은 수치형만 가능하다는 것입니다. 문자열형이나 날짜시간형의 경우 합계를 구할 수 없습니다. 또한 `COUNT` 집계함수와 마찬가지로 `NULL` 값은 제거한 뒤에 합계를 계산합니다.

### `AVG`로 평균내기

`AVG` 집계함수를 사용하여 평균을 구할 수 있습니다. `quantity`열의 평균을 구하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql> SELECT AVG(quantity), SUM(quantity)/COUNT(quantity) FROM sample51;

    +---------------+-------------------------------+
    | AVG(quantity) | SUM(quantity)/COUNT(quantity) |
    +---------------+-------------------------------+
    |        4.0000 |                        4.0000 |
    +---------------+-------------------------------+
    1 row in set (0.01 sec)
    ```

</div>

이때 유의할 점은 `AVG` 집계함수를 사용하지 않더라도 `SUM`과 `COUNT` 집계함수를 통해 평균을 계산할 수 있다는 것입니다. 또한 `NULL` 값을 제거한 뒤에 평균값을 계산하는데 만약 이를 포함하여 계산하고 싶다면 아래와 같이 `CASE` 문을 사용해 `NULL` 값을 특정한 값으로 변환한 뒤에 `AVG` 집계함수를 사용하면 됩니다.

<div class="termy">
    ```sh
    $ mysql > SELECT AVG(CASE WHEN quantity IS NULL THEN 0 ELSE quantity END) FROM sample51;

    +----------------------------------------------------------+
    | AVG(CASE WHEN quantity IS NULL THEN 0 ELSE quantity END) |
    +----------------------------------------------------------+
    |                                                   3.2000 |
    +----------------------------------------------------------+
    1 row in set (0.00 sec)
    ```

</div>

### `MIN`, `MAX`로 최솟값, 최댓값 구하기

`MIN`과 `MAX` 집계함수를 사용하여 각각 집합에서의 최솟값과 최댓값을 구할 수 있습니다. 그 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT MIN(name), MIN(quantity), MAX(name), MAX(quantity) FROM sample51;

    +-----------+---------------+-----------+---------------+
    | MIN(name) | MIN(quantity) | MAX(name) | MAX(quantity) |
    +-----------+---------------+-----------+---------------+
    | A         |             1 | C         |            10 |
    +-----------+---------------+-----------+---------------+
    1 row in set (0.00 sec)
    ```

</div>

이때 유의할 점은 문자열형의 경우 사전식 배열을 통해 그 대소비교가 가능하고 날짜시간형 또한 마찬가지이므로 `SUM` 또는 `AVG` 집계함수와 다르게 수치형이 아는 다른 것에도 `MIN` 및 `MAX` 집계함수를 사용할 수 있다는 것입니다. 그러나 `NULL` 값을 무시하는 점은 다른 집계함수와 동일합니다.


## 그룹화 - `GROUP BY`

`GROUP BY`를 사용하여 집계함수로 넘겨줄 집합을 **그룹**으로 나눌 수 있습니다.

### `GROUP BY`로 그룹화

다시 한 번 `sample51` 테이블의 데이터를 보면 아래와 같이 중복되는 값이 존재하는 것을 알 수 있습니다.

```sql
+------+------+----------+
| no   | name | quantity |
+------+------+----------+
|    1 | A    |        1 |
|    2 | A    |        2 |
|    3 | B    |       10 |
|    4 | C    |        3 |
|    5 | NULL |     NULL |
+------+------+----------+
```

`name` 열을 기준으로 `GROUP BY`를 사용하여 그룹화하면 그 결과가 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql> SELECT name FROM sample51 GROUP BY name;

    +------+
    | name |
    +------+
    | A    |
    | B    |
    | C    |
    | NULL |
    +------+
    4 rows in set (0.00 sec)
    ```

</div>

`DISTINCT` 키워드를 사용했을 때와 마찬가지로 지정된 열의 값이 같은 행이 하나의 **그룹**으로 묶입니다. `GROUP BY`와 집계함수를 같이 사용하지 않으면 사실 `DISTINCT`와 큰 차이가 없습니다. 중복만 제거되기 때문입니다. `GROUP BY`와 집계함수를 함께 사용한 결과는 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT name, COUNT(name), SUM(quantity) FROM sample51 GROUP BY name;

    +------+-------------+---------------+
    | name | COUNT(name) | SUM(quantity) |
    +------+-------------+---------------+
    | A    |           2 |             3 |
    | B    |           1 |            10 |
    | C    |           1 |             3 |
    | NULL |           0 |          NULL |
    +------+-------------+---------------+
    4 rows in set (0.00 sec)
    ```

</div>

`A` 그룹의 경우 두 개의 행이 존재했기 때문에 `COUNT(name)`의 결괏값이 `2`이며 `SUM(quantity)`의 결괏값 또한 두 행의 `quantity`열의 값이 합산되어 반환되었습니다.

`GROUP BY`는 실제로 업무 환경에서 쓰이는 경우가 많습니다. 예를 들어 판매 실적 등을 통계 내어 살펴볼 때면 날짜별 또는 상품별 등 특정한 단위로 집계할 때 `GROUP BY`를 사용해 그룹화하게 됩니다.

### `HAVING` 구로 조건 지정

집계함수는 `WHERE` 구의 조건식에는 사용할 수 없습니다. 예를 들어 `SELECT name, COUNT(name) FROM sample51 WHERE COUNT(name) = 1 GROUP BY name;`과 같은 형태의 명려운을 입력하면 오류가 발생합니다. 이는 `GROUP BY`와 `WHERE` 구의 내부처리 순서 때문입니다. `WHERE` 구로 행을 검색하는 처리가 `GROUP BY`로 그룹화하는 것보다 앞섭니다. 


집계한 결과에서 조건에 맞는 값만 걸러내기 위해서는 `SELECT` 명령에 `HAVING` 구를 사용할 수 있습니다. `WHERE` 구와 `HAVING` 구를 전부 사용하여 지정된 조건으로 검색하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT name, COUNT(name), SUM(quantity) FROM sample51 WHERE quantity < 10 GROUP BY name HAVING COUNT(name) > 1;

    +------+-------------+---------------+
    | name | COUNT(name) | SUM(quantity) |
    +------+-------------+---------------+
    | A    |           2 |             3 |
    +------+-------------+---------------+
    1 row in set (0.01 sec)
    ```

</div>

이는 아래와 같은 순서로 작동하여 최종 결과물을 반환합니다.

1. `WHERE` 구를 통해 `quantity`열의 데이터 값이 `10`을 초과하는 행은 제외했습니다.
2. `GROUP BY` 구를 통해 `name`열을 기준으로 데이터를 그룹화하였습니다.
3. `HAVING` 구를 통해 집계함수 `COUNT`를 사용하여 `name`열의 수가 `1` 이하인 행은 제외했습니다.
4. `SELECT` 구를 통해 `name`열과 집계함수 `COUNT`를 사용하여 `name`열의 갯수를 센 것, 그리고 집계함수 `SUM`을 사용하여 `quantity`열의 합산을 구한 것을 검색하여 반환했습니다.

!!! info "정보"

    결과적으로 내부처리 순서를 정리해보면 `WHERE` 구, `GROUP BY` 구, `HAVING` 구, `SELECT 구` 그리고 `ORDER BY` 구 순서입니다.

    유의할 점은 `WHERE` 구, `GROUP BY` 구, `HAVING` 구 모두 `SELECT` 구보다 먼저 처리되기 때문에 `SELECT` 구에서 사용하는 별명(`AS`)을 사용할 수 없습니다.

### 복수열의 그룹화

`GROUP BY`를 사용할 때는 `GROUP BY`에 지정한 열 이외의 열은 집계함수를 사용하지 않으면 `SELECT` 구에 기술해서는 안됩니다. 왜냐하면 어떤 값을 반환해야 할 지 모르기 때문입니다.

예를 들어 `name`열을 `GROUP BY`로 그룹화하였을 때 `no`열을 집계함수를 사용하지 않고 `SELECT` 구에 기술할 경우 아래와 같은 오류를 반환합니다

<div class="termy">
    ```sh
    $ mysql > SELECT no, name FROM sample51 GROUP BY name;

    ERROR 1055 (42000): Expression #1 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'sample.sample51.no' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
    ```

</div>

만약 그룹화된 열 이외의 열을 사용하고 싶다면 아래와 같이 콤마(`,`)를 통해 복수의 열을 그룹화하면 됩니다.

<div class="termy">
    ```sh
    $ mysql > SELECT no, name FROM sample51 GROUP BY no, name;

    +------+------+
    | no   | name |
    +------+------+
    |    1 | A    |
    |    2 | A    |
    |    3 | B    |
    |    4 | C    |
    |    5 | NULL |
    +------+------+
    5 rows in set (0.00 sec)
    ```

</div>

### 결괏값 정렬

`GROUP BY`로 그룹화해도 실행결과 순서를 정렬할 수는 없습니다. 같은 값을 내부적으로 나누는 과정에서 순서가 뒤바뀌는 부작용이 발생할 수 있기 때문입니다. 그러나 이는 결국 내부처리 문제이기 때문에 데이터베이스 제품에 따라 다릅니다.

만약 `GROUP BY`를 사용한 뒤에 결괏값을 정렬하고 싶다면 아래와 같이 `ORDER BY` 구를 사용하면 됩니다. 이때 `ORDER BY` 구는 `SELECT` 구 다음에 실행되기 때문에 별명(`AS`)을 사용할 수 있습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT name, COUNT(name), SUM(quantity) AS quantity_sum FROM sample51 GROUP BY name ORDER BY quantity_sum DESC;

    +------+-------------+--------------+
    | name | COUNT(name) | quantity_sum |
    +------+-------------+--------------+
    | B    |           1 |           10 |
    | A    |           2 |            3 |
    | C    |           1 |            3 |
    | NULL |           0 |         NULL |
    +------+-------------+--------------+
    4 rows in set (0.00 sec)
    ```

</div>


## 서브쿼리

**서브쿼리**란 `SELECT` 명령에 의한 부수적인 데이터 질의입니다. `(SELECT 명령)`과 같이 괄호(`()`)를 통해 사용하며 주로 `WHERE` 구에서 사용됩니다.

### `DELETE`의 `WHERE` 구에서 서브쿼리 사용하기

아래와 같은 테이블 `sample54`이 있다고 가정해봅시다.

```sql
+------+------+
| no   | a    |
+------+------+
|    1 |  100 |
|    2 |  900 |
|    3 |   20 |
|    4 |   80 |
+------+------+
```

이때 `a`열의 값이 가장 작은 행을 삭제하는 방법은 아래와 같습니다. (MySQL 기준 쿼리입니다.)

<div class="termy">
    ```sh
    $ mysql > DELETE FROM sample54 WHERE a = (SELECT a FROM (SELECT MIN(a) AS a FROM sample54) AS x);

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample54;

    +------+------+
    | no   | a    |
    +------+------+
    |    1 |  100 |
    |    2 |  900 |
    |    4 |   80 |
    +------+------+
    3 rows in set (0.00 sec)
    ```

</div>

!!! info "정보"

    다른 SQL의 경우 `DELETE FROM sample54 WHERE a = (SELECT MIN(a) FROM sample54);`와 같이 명령문을 입력해도 정상 작동합니다.

    MySQL의 경우 데이터를 추가하거나 갱신할 때 동일한 테이블을 서브쿼리에서 사용할 수 없도록 되어 있기 때문에 인라인 뷰로 임시 테이블을 만들도록 처리해야 합니다.

    또한 서브쿼리에 별명(`AS`)을 무조건 붙여줘야 오류가 발생하지 않기 때문에 `x`라는 별명을 붙였습니다.

SQL에는 변수가 따로 존재하지 않지만 사용하는 것은 가능합니다. 아래는 MySQL 클라이언트에 한해서 사용가능한 예시입니다.

<div class="termy">
    ```sh
    $ mysql > SET @a = (SELECT MIN(a) FROM sample54);

    Query OK, 0 rows affected (0.01 sec)

    $ mysql > DELETE FROM sample54 WHERE a = @a;

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample54;

    +------+------+
    | no   | a    |
    +------+------+
    |    1 |  100 |
    |    2 |  900 |
    +------+------+
    2 rows in set (0.00 sec)
    ```

</div>

### 스칼라 값

서브쿼리를 사용할 때는 해당 `SELECT` 명령이 반환하는 값이 무엇인지 주의할 필요가 있습니다. 보통 다음과 같은 네 가지가 일반적인 서브쿼리의 반환 패턴입니다.

* 하나의 값을 반환하는 패턴
    * 예시 : `SELECT MIN(a) FROM sample54;`
* 복수의 행이 반횐되지만 열은 하나인 패턴
    * 예시 : `SELECT no FROM sample54;`
* 하나의 행이 반환되지만 열이 복수인 패턴
    * 예시 : `SELECT MIN(a), MAX(no) FROM sample54;`
* 복수의 행, 복수의 열이 반환되는 패턴
    * 예시 : `SELECT no, a FROM sample54;`

이때 하나의 값을 반환하는 패턴을 데이터베이스에서 **스칼라 값**을 반환한다고 합니다.

통상적으로 두 가지가 서로 동일한지 여부를 비교할 때는 단일 값으로 비교합니다. 이를 더 자세하게 말하면 `WHERE` 구에서 서브쿼리가 반환한 스칼라 값을 비교(`=`)할 수 있어 이럴 경우에 유용합니다.

이처럼 스칼라 값을 반환하는 서브쿼리를 특별히 **스칼라 서브쿼리**라 부르기도 합니다. 앞서 집계함수를 `WHERE` 구에서 사용할 수 없다는 걸 알게 되었는데 스칼라 서브쿼리를 사용하면 집계함수 또한 `WHERE` 구에서 사용할 수 있습니다.

### `SELECT` 구에서 서브쿼리 사용하기

문법적으로 서브쿼리는 하나의 항목으로 취급합니다. 따라서 서브쿼리를 사용할 때는 스칼라 서브쿼리인지 확인해야 합니다.

`SELECT` 구에서 스칼라 서브쿼리를 사용하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT (SELECT COUNT(*) FROM sample51) AS 'sample51', (SELECT COUNT(*) FROM sample54) AS 'sample54' FROM DUAL;

    +----------+----------+
    | sample51 | sample54 |
    +----------+----------+
    |        5 |        2 |
    +----------+----------+
    1 row in set (0.00 sec)
    ```

</div>

!!! info "정보"

    MySQL 등에서는 `FROM DUAL` 부분을 생략해도 좋습니다. 하지만 Oracle과 같은 전통적인 데이터베이스에서는 `FROM` 구를 생략할 수 없습니다.

    `DUAL` 키워드는 시스템 쪽에서 데이터베이스에 기본으로 작성되는 일종의 임시 테이블입니다.

### `SET` 구에서 서브쿼리 사용하기

`UDPATE`의 `SET` 구에도 서브쿼리를 사용할 수 있습니다. 방법은 아래와 같으며 그 결과 `a`열의 값이 전부 `a`열의 최댓값으로 갱신되었습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample54 SET a = (SELECT a FROM (SELECT MAX(a) AS a FROM sample54) AS x);

    Query OK, 1 row affected (0.00 sec)
    Rows matched: 2  Changed: 1  Warnings: 0

    $ mysql > SELECT * FROM sample54;

    +------+------+
    | no   | a    |
    +------+------+
    |    1 |  900 |
    |    2 |  900 |
    +------+------+
    2 rows in set (0.00 sec)
    ```

</div>

### `FROM` 구에서 서브쿼리 사용하기

`FROM` 구에서도 서브쿼리를 사용할 수 있습니다. 다만 굳이 스칼라 서브쿼리가 아니더라도 상관 없습니다. 예를 들어 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM (SELECT * FROM sample54) AS sq;

    +------+------+
    | no   | a    |
    +------+------+
    |    1 |  900 |
    |    2 |  900 |
    +------+------+
    2 rows in set (0.00 sec)
    ```

</div>

이처럼 `SELECT` 명령 안에 `SELECT` 명령이 들어 있는 구조를 **중첩 구조(Nested Structure)**라 합니다. 이때 중첩 구조는 몇 단계로 구성되어 있어도 상관 없습니다.

#### 실제 업무에서 `FROM` 구에 서브쿼리를 지정하여 사용하는 경우

Oracle 같은 경우 `LIMIT` 구 존재하지 않기 때문에 `ROWNUM` 구로 행 개수를 제한하는 걸 이전에 한 번 살펴봤습니다. 그러나 `ROWNUM`의 경우 `WHERE` 구로 인해 번호가 할당되는 방식이기 때문에 행 개수가 제한됩니다. 따라서 이를 정렬한 이후에 상위 몇 건을 추출하는 등의 부수 조건을 붙일 수 없습니다. 이럴 때 `FROM` 구에 서브쿼리를 사용합니다. 방법은 아래와 같습니다.

```sql
SELECT * FROM (
    SELECT * FROM sample54 ORDER BY a DESC
) AS sq WHERE ROWNUM <= 2;
```

### `INSERT` 명령과 서브쿼리

`INSERT` 명령에도 서브쿼리를 사용할 수 있습니다. 방법은 아래와 같이 두 가지 입니다.

* `VALUES` 구의 일부로 서브쿼리를 사용하는 방법
* `VALUES` 구 대신 `SELECT` 명령을 사용하는 방법

아래와 같은 테이블 구조를 가진 테이블 `sample541`이 있다고 가정해봅시다.

```sql
+-------+------+------+-----+---------+-------+
| Field | Type | Null | Key | Default | Extra |
+-------+------+------+-----+---------+-------+
| a     | int  | YES  |     | NULL    |       |
| b     | int  | YES  |     | NULL    |       |
+-------+------+------+-----+---------+-------+
```

다음은 `VALUES` 구의 값으로 서브쿼리를 사용하는 방법입니다. 이때 유의할 점은 서브쿼리로 스칼라 서브쿼리를 지정해야 합니다.

<div class="termy">
    ```sh
    $ mysql > INSERT INTO sample541 VALUES((SELECT COUNT(*) FROM sample51), (SELECT COUNT(*) FROM sample54));

    Query OK, 1 row affected (0.00 sec)

    $ mysql > SELECT * FROM sample541;

    +------+------+
    | a    | b    |
    +------+------+
    |    5 |    2 |
    +------+------+
    1 row in set (0.00 sec)
    ```

</div>

#### `INSERT SELECT`

아래와 같이 `VALUES` 대신에 `SELECT` 명령을 사용할 수도 있습니다. 예들 들면 아래와 같습니다.

<div class"termy">
    ```sh
    $ mysql > INSERT INTO sample541 SELECT 1, 2;

    Query OK, 1 row affected (0.00 sec)
    Records: 1  Duplicates: 0  Warnings: 0

    $ mysql > SELECT * FROM sample541;

    +------+------+
    | a    | b    |
    +------+------+
    |    5 |    2 |
    |    1 |    2 |
    +------+------+
    2 rows in set (0.00 sec)
    ```

</div>

이때 `SELECT 1, 2`의 경우 MySQL에서 실행해보면 그 결과가 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT 1, 2;

    +---+---+
    | 1 | 2 |
    +---+---+
    | 1 | 2 |
    +---+---+
    1 row in set (0.00 sec)
    ```

</div>

위와 같이 `VALUES` 대신에 `SELECT` 명령을 서브쿼리로 쓰는 걸 `INSERT`와 `SELECT`를 합친 것만 같아서 `INSERT SELECT` 명령이라 부릅니다. 이때 `SELECT` 명령이 반환하는 값이 꼭 스칼라 값일 필요는 없습니다. 단지 반환하는 열 수와 자료형이 `INSERT`할 테이블과 일치하기만 하면 됩니다. 결과적으로 이러한 특징 덕분에 데이터의 복사나 이동을 할 때 자주 사용하는 명령입니다.

## 상관 서브쿼리

서브쿼리의 일종을 **상관 서브쿼리**라 합니다. 더 자세한 정의는 [상관 서브쿼리](#상관-서브쿼리)에서 확인하겠습니다.

### `EXISTS`

서브쿼리를 사용해 검색할 때 데이터 **존재여부**를 판별하기 위해 조건을 지정할 수 있습니다. 이런 경우 `EXISTS` 술어를 사용할 수 있습니다. 이때 `EXISTS` 술어는 단지 반환된 행이 있는지를 확인해보고 값의 있으면 **참(True)**, 없으면 **거짓(False)**을 반환하므로 굳이 스칼라 서브쿼리일 필요는 없습니다.

아래와 같은 테이블 `sample551`이 있다고 가정해봅시다.

```sql
+------+------+
| no   | a    |
+------+------+
|    1 | NULL |
|    2 | NULL |
|    3 | NULL |
|    4 | NULL |
|    5 | NULL |
+------+------+
```

그리고 아래와 같은 테이블 `sample552`도 있다고 가정해봅시다.

```sql
+------+
| no2  |
+------+
|    3 |
|    5 |
+------+
```

`sample552` 테이블 속 `no`열의 값과 같은 행이 있다면 `sample551` 테이블의 해당 행의 `a` 열 값을 `있음`이라는 값으로, 아니면 `없음`이라는 값으로 갱신하는 방법은 아래와 같습니다.

```sql
UPDATE sample551 SET a = '있음' WHERE ...
UPDATE sample551 SET a = '없음' WHERE ...
```

이때 `WHERE` 구에 `no = 1`과 같은 단순한 조건식을 기술할 수 없습니다. 이를 위해서는 아래와 같이 `EXISTS` 술어를 사용해야 합니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample551 SET a = '있음' WHERE EXISTS (SELECT * FROM sample552 WHERE no2 = no);

    Query OK, 2 rows affected (0.00 sec)
    Rows matched: 2  Changed: 2  Warnings: 0

    $ mysql > SELECT * FROM sample551;

    +------+--------+
    | no   | a      |
    +------+--------+
    |    1 | NULL   |
    |    2 | NULL   |
    |    3 | 있음    |
    |    4 | NULL   |
    |    5 | 있음    |
    +------+--------+
    5 rows in set (0.00 sec)
    ```

</div>

이때 서브쿼리가 한 줄 이상의 행을 반환하면 참이 되고 행이 없으면 거짓이 됩니다.

### `NOT EXISTS`

`없음`의 경우 행이 존재하지 않는 상태가 참이므로 `NOT EXISTS`를 사용합니다. 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > UPDATE sample551 SET a = '없음' WHERE NOT EXISTS (SELECT * FROM sample552 WHERE no2 = no);

    Query OK, 3 rows affected (0.00 sec)
    Rows matched: 3  Changed: 3  Warnings: 0

    $ mysql > SELECT * FROM sample551;

    +------+--------+
    | no   | a      |
    +------+--------+
    |    1 | 없음   |
    |    2 | 없음   |
    |    3 | 있음   |
    |    4 | 없음   |
    |    5 | 있음   |
    +------+--------+
    5 rows in set (0.00 sec)
    ```

</div>

!!! info "정보"

    `UPDATE` 외에도 `SELECT` 또는 `DELETE` 명령으로도 `EXISTS` 또는 `NOT EXISTS` 서브쿼리를 사용할 수 있습니다.

### 상관 서브쿼리

아래 `있음`으로 데이터를 갱신하는 명령문을 다시 한 번 살펴보겠습니다.

```sql
UPDATE sample551 SET a = '있음' WHERE
    EXISTS (SELECT * FROM sample552 WHERE no2 = no);
```

`UPDATE` 명령이 **부모**가 되고 `WHERE` 구에 괄호로 묶은 부분이 **자식**, 다시 말해 서브쿼리가 됩니다. 이때 자식인 서브쿼리에서 `sample552` 테이블의 `no2`열 값이 부모인 `sample551` 테이블의 `no`열 값과 일치하는 지 판별하게 되며 이처럼 부모 명령과 자식인 서브쿼리가 특정 관계를 맺는 것을 **상관 서브쿼리**라 합니다.

앞서 `DELETE`에서 사용한 다음과 같은 명령문은 상관 서브쿼리가 아닙니다. 해당 서브쿼리를 단독 쿼리로 실행할 수 있기 때문입니다.

```sql
DELETE FROM sample54 WHERE a = (SELECT a FROM (SELECT MIN(a) AS a FROM sample54) AS x);
```

상관 서브쿼리의 경우 부모 명령과 연관되어 처리되기 때문에 서브쿼리 부분만을 따로 떼어내어 실행시킬 수 없습니다.

#### 테이블명 붙이기

만약에 테이블 `sample552`의 열 이름이 `no2`가 아닌 `no`였다면 `sample551`의 열과 중복됩니다. 이렇게 해당 열의 이름이 중복되어 어떤 테이블에 속해있는 열인지 구분이 되지 않을 경우 오류가 발생합니다. 이런 경우 점(`.`)을 사용해 앞 부분에 해당 열이 속한 테이블 명을 입력해서 구분지을 수 있습니다. 명령문 예시는 아래와 같습니다.

```sql
UPDATE sample551 SET a = '있음' WHERE EXISTS (
    SELECT * FROM sample552 WHERE sample552.no = sample551.no
);
```

!!! info "정보"

    MySQL에서 만약 `WHERE no = no`와 같이 기술했다면 해당 `no`열이 전부 `sample551` 테이블의 열로 판단되어 결국 무조건 참이기 때문에 모든 `a`열의 값이 `있음`으로 갱신됩니다.

### `IN`

스칼라 값끼리 비교할 때는 비교 연산자(`=`)를 사용하지만 집합을 비교할 때는 이를 사용할 수 없습니다. 이때 `IN`을 사용하여 집합 안의 값이 존재하는 지 조사할 수 있어 이를 통해 집합끼리의 비교가 가능해집니다.

`IN`을 사용해서 테이블 `sample551`의 `no`열에 `3`과 `5`가 들어있는 경우만을 반환하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample551 WHERE no IN(3, 5);

    +------+--------+
    | no   | a      |
    +------+--------+
    |    3 | 있음    |
    |    5 | 있음    |
    +------+--------+
    2 rows in set (0.00 sec)
    ```

</div>

위 방법은 `SELECT * FROM sample551 WHERE no = 3 OR no = 5` 명령문과 반환되는 결괏값이 동일합니다. 그러나 `IN`의 경우 더 많은 집합을 비교해야 할 때 유용합니다.

집합 부분을 서브쿼리로 지정할 수도 있습니다. 위 예시를 서브쿼리로 지정하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > SELECT * FROM sample551 WHERE no IN (SELECT * FROM sample552);

    +------+--------+
    | no   | a      |
    +------+--------+
    |    3 | 있음   |
    |    5 | 있음   |
    +------+--------+
    2 rows in set (0.00 sec)
    ```

</div>

이때 서브쿼리가 반드시 스칼라 서브쿼리일 필요는 없습니다. `IN`의 경우 좌측에 하나의 열이 지정되어 있기 때문에 `IN`을 통한 우측 집합 내에 값이 포함되어 있으면 참이 됩니다. 반대로 `NOT EXISTS`처럼 `NOT IN`을 지정하면 집합에 값이 포함되어 있지 않을 경우 참이 됩니다.

#### `IN`과 `NULL`

집계함수에서는 집합 안의 `NULL` 값을 무시하고 처리했습니다. 그러나 `IN`에서는 집합 안에 `NULL` 값이 있어도 무시하지 않습니다. 다만 `NULL` 값을 비교 연산자(`=`)를 통해 비교하지 못하기 때문에 `IN`을 사용해도 `NULL` 값을 비교할 수는 없습니다. 만약 `NOT IN`을 사용할 경우 `NULL` 값이 있으면 좌측 값이 집합 안에 포함되어 있지 않아도 참을 반환하지 않고 결괏값으로 `UNKNOWN`을 반환합니다.

결론적으로 `NULL` 값을 비교하기 위해서는 `IS NULL`을 사용해야합니다.

!!! info "정보"

    MySQL의 경우 집합에 `NULL`이 포함되어 있는 경우, 조건식 `IN`은 왼쪽 값이 집합에 포함되어 있으면 **참**을, 그렇지 않으면 `NULL`을 반환합니다. `NOT IN`의 경우 반대로 왼쪽 값이 집합에 포함되어 있으면 **거짓**을, 그렇지 않으면 `NULL`을 반환합니다. 따라서 `NOT IN`의 경우 집합에 `NULL`이 포함되어 있다면 결괏값이 `0`이 됩니다.

    좌측 값이 `NULL`인 경우에도 결국 `NULL`은 우측 값과 비교가 불가능하기 때문에 조건식은 참 또는 거짓이 아닌 `NULL`을 반환합니다.
