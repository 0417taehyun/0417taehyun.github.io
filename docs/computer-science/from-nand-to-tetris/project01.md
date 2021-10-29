---
title: "불 함수와 게이트 로직"
date: "2021-10-31"
tags:
    - Computer Science
---

# 불 함수와 게이트 로직

!!! note "참고"
    Coursera 강의 [From Nand to Tetris](https://www.coursera.org/learn/build-a-computer) 및 [밑바닥부터 만드는 컴퓨팅 시스템](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788966262427)을 참고로 공부한 내용입니다.


## 불 게이트 (Boolean gate)
**불 게이트(Boolean gate)**는 **불 함수(Boolean function)**를 물리적으로 구현한 것이므로, 먼저 **불 대수(Boolean algebra)**부터 간략하게 살펴봐야 한다.


### 불 대수 (Boolean algebra)
**불 함수**는 2진수를 입력받아 2진수를 출력하는 함수다. 컴퓨터는 2진수를 표현하고 처리하는 하드웨어이므로, 불 함수는 하드웨어 아키텍처의 명세, 구성, 최적화에 중심적인 역할을 한다.  
따라서 불 함수를 정의하고 분석하는 것이 컴퓨터 아키텍처를 구축하는 첫 단계가 된다.

#### 진리표 표현 (Truth table)
어떤 불 함수를 정의하는 가장 쉬운 방법은 함수의 입력값들과 결괏값을 나란히 쓰는 방법이다. 이 방식을 함수의 **진리표(Truth table)**표현이라 한다.  
예를 들어 아래 표와 같다.

|x|y|z|f(x, y, z)|
|:-:|:-:|:-:|:-:|
|0|0|0|0|
|0|0|1|0|
|0|1|0|1|
|0|1|1|0|
|1|0|0|1|
|1|0|1|0|
|1|1|0|1|
|1|1|1|0|

#### 불 표현식
불 함수는 진리표 말고도 입력값에 대한 불 연산으로도 표현 가능하다. 위 