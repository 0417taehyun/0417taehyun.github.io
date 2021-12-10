---
title: "[ 프로그래머스 ] SQL LEVEL 1 : 여러 기준으로 정렬하기"
desciprtion: "[ 프로그래머스 ] SQL LEVEL 1 : 여러 기준으로 정렬하기"
tags:
    - "2021"
    - 알고리즘
    - 프로그래머스
    - SQL
---

# [ 프로그래머스 ] SQL LEVEL 1 : 여러 기준으로 정렬하기

!!! info "정보"

    프로그래머스에서 제공하는 SQL 문제 [여러 기준으로 정렬하기](https://programmers.co.kr/learn/courses/30/lessons/59404)입니다.


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

동물 보호소에 들어온 모든 동물의 아이디와 이름, 보호 시작일을 이름 순으로 조회하는 SQL문을 작성해주세요. 단, 이름이 같은 동물 중에서는 보호를 나중에 시작한 동물을 먼저 보여줘야 합니다.

## 풀이

아래와 같이 `ORDER BY` 구에 여러 열을 넘겨서 간단하게 풀이할 수 있다.

```sql
{!../docs_src/algorithms/programmers/sql/level-1/9_ordering_with_multiple_options.sql[ln:3-5]!}
```