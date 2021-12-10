---
title: "[ 프로그래머스 ] SQL LEVEL 1 : 모든 레코드 조회하기"
desciprtion: "[ 프로그래머스 ] SQL LEVEL 1 : 모든 레코드 조회하기"
tags:
    - "2021"
    - 알고리즘
    - 프로그래머스
    - SQL
---

# [ 프로그래머스 ] SQL LEVEL 1 : 모든 레코드 조회하기

!!! info "정보"

    프로그래머스에서 제공하는 SQL 문제 [모든 레코드 조회하기](https://programmers.co.kr/learn/courses/30/lessons/59034)입니다.

## 문제

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

|NAME|TYPE|NULLABLE|
|:-:|:--:|:-------:|
|`ANIMAL_ID`|VARCHAR(N)|`FALSE`|
|`ANIMAL_TYPE`|VARCHAR(N)|`FALSE`|
|`DATETIME`|DATETIME|`FALSE`|
|`INTAKE_CONDITION`|VARCHAR(N)|`FALSE`|
|`NAME`|VARCHAR(N)|`TRUE`|
|`SEX_UPON_INTAKE`|VARCHAR(N)|`FALSE`|

동물 보호소에 들어온 모든 동물의 정보를 `ANIMAL_ID`순으로 조회하는 SQL문을 작성해주세요.


## 풀이

풀이는 매우 간단하다. 아래와 같이 `SELECT` 명령으로 모든 열을 검색하고 이때 결괏값을 `ORDER BY` 명령을 통해 `ANIMAL_ID`를 기준으로 정렬하면 된다.

```sql
{!../docs_src/algorithms/programmers/sql/level-1/1_get_all_records.sql[ln:3]!}
```

## 기타

해당 문제에 `ORDER BY` 명령이 아닌 `GROUP BY` 명령을 사용하여 `ANIMAL_ID`를 기준으로 그룹화하였는데 출력되는 결괏값은 동일한데 어째서 정답이 아닌 지 궁금해하는 질문이 있었다.

우선 [[ SQL 첫걸음 ] 제 5장 집계와 서브쿼리 ](https://www.weekwith.me/devlog/database/sql-first-step/5-aggregation-and-subquery/#_2) 부분을 살펴보면 `GROUP BY`로 그룹화하더라도 실행결과 순서를 정렬할 수 없다고 적혀있다. 데이터베이스 내부에서 처리를 할 때 동일한 값을 그룹으로 묶는 과정에서 서로 뒤바뀌는 부작용을 예방하기 위해서다. 따라서 `GROUP BY`를 사용하더라도 실행결과가 정렬되어 있길 원한다면 `ORDER BY` 명령을 사용해야 한다.

SQL 명령의 내부 처리 순서는 아래와 같다.

`FROM` > `WHERE` > `GROUP BY` > `HAVING` > `SELECT` > `ORDER BY`

그러면 만약 정렬한 이후 `GROUP BY` 명령을 통해 그룹화하고 싶다면 어떻게 해야 할까? `FROM` 구 내에 서브쿼리를 활용하여 인라인 뷰를 만든 뒤에 `GROUP BY` 명령을 사용하면 된다.

!!! info "정보"

    `FROM` 구에 사용되는 서브쿼리를 **인라인 뷰(Inline View)**라 한다. 그 결과가 마치 실행 시에 동적으로 생성된 테이블인 것처럼 사용할 수 있기 때문이다. 다시 말해 일종의 가상 테이블이다.

```sql
{!../docs_src/algorithms/programmers/sql/level-1/1_get_all_records.sql[ln:6-12]!}
```

이때 유의할 점은 위와 같이 서브쿼리 내에 `ORDER BY`를 사용할 경우 단일 쿼리를 사용할 때와 결괏값이 달라질 수 있다. 다시 말해 서브쿼리인 `SELECT * FROM ANIMAL_INS ORDER BY ANIMAL_INS.ANIMAL_ID` 명령문을 따로 빼서 사용하면 서브쿼리로 사용할 때와 결괏값이 달라질 수 있다는 의미이다. DBMS가 성능을 고려하여 해당 `ORDER BY`를 자동으로 삭제하기 때문이다.

`EXPLAIN` 키워드를 통해 살펴보면 아래와 같이 `type` 부분이 `ALL`로 반환되는 것을 확인할 수 있다. 인덱스로 사용되는 서브쿼리 부분이 무시되는 것이다.

<img src="https://weekwith.me/images/algorithms/programmers/sql/level-1/1-get-all-records/1.png">

비교를 위해 `EXPLAIN` 키워드를 통해서 서브쿼리 명령문을 독립적으로 확인해보면 `type` 부분이 `ALL`이 아닌 `index`인 것을 확인할 수 있다.

<img src="https://weekwith.me/images/algorithms/programmers/sql/level-1/1-get-all-records/2.png">

해결 방법은 아래와 같이 `LIMIT` 키워드를 사용하여 `LIMIT`의 최댓값을 작성하는 것이다. 이때 `LIMIT` 키워드의 최댓값은 $2^{64} - 1$ 이다.

```sql hl_lines="20"
{!../docs_src/algorithms/programmers/sql/level-1/1_get_all_records.sql[ln:15-22]!}
```

다시 `EXPLAIN` 명령을 사용해보면 아래와 같이 정상적으로 서브쿼리를 인덱스로 활용하는 걸 확인할 수 있다.

<img src="https://weekwith.me/images/algorithms/programmers/sql/level-1/1-get-all-records/3.png">

`ORDER BY` 명령을 사용하면 데이터베이스 테이블에 저장된 데이터의 순서 자체에는 영향을 미치지 않고 출력되는 결과만 바꾼다. 결국 데이터베이스 테이블은 데이터의 순서가 별다른 의미가 없기 때문에 서브쿼리를 실행해도 본질적인 값의 구조와 정렬이 변하지 않기 때문에 성능적인 부분을 고려하여 DBMS가 자동으로 `ORDER BY` 명령을 제거하는 것이다.

쉽게 말해 서브쿼리를 사용하여 임시 테이블을 생성하였는데 해당 테이블에서 계산 만을 위해 사용할 수 있는 메모리의 제한이 존재하기 때문에 `ORDER BY` 명령을 제거한다.

이때 `LIMIT` 키워드를 추가하면 기존에는 데이터의 본질적인 부분이라 할 수 있는 행의 수를 제한하기 때문에 서브쿼리의 결과가 원하는 대로 설정된다.