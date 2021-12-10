---
title: "[ 프로그래머스 ] SQL LEVEL 1 : 아픈 동물 찾기"
desciprtion: "[ 프로그래머스 ] SQL LEVEL 1 : 아픈 동물 찾기"
tags:
    - "2021"
    - 알고리즘
    - 프로그래머스
    - SQL
---

# [ 프로그래머스 ] SQL LEVEL 1 : 아픈 동물 찾기

!!! info "정보"

    프로그래머스에서 제공하는 SQL 문제 [아픈 동물 찾기](https://programmers.co.kr/learn/courses/30/lessons/59036#fn1)입니다.


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

동물 보호소에 들어온 동물 중 아픈 동물의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요.

## 풀이

아래와 같이 간단하게 풀이할 수 있다.

```sql
{!../docs_src/algorithms/programmers/sql/level-1/6_find_sick_animal.sql[ln:3-6]!}
```