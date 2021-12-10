---
title: "[ 프로그래머스 ] SQL LEVEL 1 : 최댓값 구하기"
desciprtion: "[ 프로그래머스 ] SQL LEVEL 1 : 최댓값 구하기"
tags:
    - "2021"
    - 알고리즘
    - 프로그래머스
    - SQL
---

# [ 프로그래머스 ] SQL LEVEL 1 : 최댓값 구하기

!!! info "정보"

    프로그래머스에서 제공하는 SQL 문제 [최댓값 구하기](https://programmers.co.kr/learn/courses/30/lessons/59415)입니다.

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

가장 최근에 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.

## 풀이

아래와 같이 `MAX` 키워드를 통해 날짜시간형 데이터 중에서 가장 큰 값을 반환하면 된다.

```sql
{!../docs_src/algorithms/programmers/sql/level-1/2_get_max_number.sql[ln:3]!}
```

또는 아래와 같이 `ORDER BY` 키워드 및 `DESC` 키워드를 통해 `DATETIME` 열을 기준으로 내림차순 정렬한 뒤 `LIMIT` 키워드를 사용하여 첫 번째 행만 반환하는 방법도 있다.

```sql hl_lines="9"
{!../docs_src/algorithms/programmers/sql/level-1/2_get_max_number.sql[ln:6-9]!}
```