---
title: "[ SQL 첫걸음 ] 제 8장 데이터베이스 설계"
description: "[ SQL 첫걸음 ] 제 8장 데이터베이스 설계"
tags:
    - "2021"
    - SQL
---

# [ SQL 첫걸음 ] 제 8장 데이터베이스 설계

!!! note "참고"

    [SQL 첫걸음](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&barcode=9788968482311)을 참고로 공부한 내용입니다. 따라서 아래 명령어를 통해 나오게 되는 결괏값은 전부 해당 책을 통해 다운로드한 파일의 결과물입니다.


## 데이터베이스 설계

### 데이터베이스 설계

**데이터베이스 설계**는 곧 데이터베이스 스키마 내의 테이블, 인덱스, 뷰 등의 **객체**를 정의하는 것을 의미합니다. 스키마 내에 정의하기 때문에 **스키마 설계**라 불리기도 합니다.

이때 설계의 주된 내용은 테이블의 이름이나 열, 자료형을 결정하는 것입니다. 그리고 테이블 간의 관계를 생각하면서 여러 테이블을 정의하고 작성하게 됩니다.

#### 논리명과 물리명

테이블을 설계할 때는 테이블 정의서나 설계도 등의 문서를 작성하는 경우가 많습니다. 일반적으로 그 양식은 `DESC` 명령을 수행한 것과 유사합니다.

```
+--------------+--------------+------+-----+---------+-------+
| Field        | Type         | Null | Key | Default | Extra |
+--------------+--------------+------+-----+---------+-------+
| product_no   | char(4)      | NO   | PRI | NULL    |       |
| product_name | varchar(32)  | NO   |     | NULL    |       |
| price        | decimal(6,2) | NO   |     | NULL    |       |
+--------------+--------------+------+-----+---------+-------+
```

이때 하나의 테이블에 두 개의 이름을 지정할 때도 있습니다.

하나는 **물리명(Physical Name)**입니다. 데이터베이스에서 사용될 이름으로 `CREATE TABLE` 명령을 통해 지정되는 이름을 의미합니다.

다른 하나는 **논리명(Logial Name)**입니다. 설계상 이름을 의미합니다.

이름을 이렇게 두 개로 지정하는 이유는 데이터베이스 내에서는 시스템 규칙에 따라 길이에 제한이 있거나 공백문자를 사용할 수 없는 등의 여러 제약이 존재하기 때문입니다. 따라서 일상에서 사용하는 단어로는 이름을 지정하는 데 한계가 있습니다.

따라서 `CREATE TABLE` 명령과 함께 사용할 **물리명**과 실제로 부를 때 사용할 **논리명**으로 테이블 이름을 지정합니다.

이러한 이유 때문에 설계도나 정의서에 논리명이 별도로 작성되어 있는 경우도 있습니다.

#### 자료형

테이블의 열에는 자료형을 지정해야 합니다. 데이터베이스에 따라 다르지만 무엇이든 저장할 수 있는 만능 자료형은 없습니다.

데이터에 따라서는 몇 개의 선택지 중에 하나를 선택해야 하는 경우도 있습니다. 이런 경우에는 데이터베이스 기능으로 `CHECK` 키워드로 제약조건을 걸어 구현할 수 있습니다. 데이터의 **정합성**이 중요한 부분에는 이런 기능을 적극 사용해야 합니다.

!!! info "정보"

    **정합성(Consistency)**이란 데이터의 값들이 서로 모순 없이 일관되게 일치하는 걸 의미합니다. 예를 들어 중복 데이터를 많이 사용할 때 해당 데이터끼리 오타 등으로 값이 일치하지 않다면 이는 정합성이 깨진 상태입니다. 또한 비정규형을 사용하여 **이상현상(Anomaly)**이 발생해도 정합성이 깨집니다.

    정합성과 비슷한 개념으로 **무결성(Integrity)**도 존재합니다. 무결성이란 데이터의 값이 정확한 상태를 의미합니다. 무결성의 종류는 총 네 가지가 있습니다.

    * **개체 무결성(Entity Integrity)**
        * **의미** : 모든 인스턴스(Instance)는 고유한 값이거나 인스턴스를 대표하는 속성, 다시 말해 기본키(Primary Key)는 `NULL`값을 가지면 안 된다.
        * **예시** : 특정 회원의 아이디가 다른 회원의 아이디와 중복되어 저장되는 경우가 개체 무결성이 깨지는 걸 의미한다.
    * **참조 무결성(Referential Integrity)**
        * **의미** : 참조되는 개체의 주 식별자 값과 일치하거나 `NULL`값이어야 한다. 외래키 제약에 의해 지켜진다.
        * **예시** : 특정 회원이 어떤 제품을 주문했을 때 해당 주문은 그 회원을 외래키로 연결한다. 그런데 회원이 탈퇴했을 때 주문과 외래키가 그대로 유지되어 있으면 참조 무결성이 깨진다. 참조 무결성을 지키기 위해서는 회원이 탈퇴할 때 해당 회원이 주문한 주문 내역도 함께 삭제하거나 아니면 외래키 부분의 값이 `NULL`값이어야 한다.
    * **도메인 무결성(Domain Integrity)**
        * **의미** : 같은 속성에 사용되는 값들은 같은 성격의 값이어야 한다. 개체의 특정 속성 값은 동일한 데이터 자료형, 길이, `NULL` 허용 여부 등 동일한 범주의 값만이 존재해야 한다.
        * **예시** : 이메일의 경우 반드시 `@`가 값에 들어가 있어야 하는데 이에 대한 형식 검사를 진행해야 도메인 무결성을 지킬 수 있다.
    * **업무 무결성(Business Integrity)**
        * **의미** : 기업에서 업무를 수행하는 방법이나 데이터를 처리하는 규칙을 의미한다. 넓게 보면 개체 무결성, 참조 무결성, 도메인 무결성 모두 업무 무결성에 포함될 수 있다. 업무 무결성을 물리적으로 강제하는 방법으로 **트리거(Trigger)**가 존재한다.
        * **예시** : 주문 금액이 특정 금액 이상이면 배달미를 무료로 해주는 경우가 있다.

    데이터의 정합성을 지키더라도 무결성이 깨지는 경우가 있습니다. 예를 들어 고객정보 테이블의 고객번호가 `10`으로 존재하고 주문 내역 테이블에서도 해당 고객의 고객번호가 `10`으로 저장되어 있을 때 중복된 데이터의 값이 모순 없이 일관되기 때문에 정합성은 지켜져있지만 만약 VIP 제도가 존재하여 최대 9명의 고객만 고객번호를 `1`번부터 `9`번까지로 유지해야 하는 서비스인 경우 업무 무결성이 깨졌기 때문에 결론적으로 무결성이 깨진 경우입니다.

    결론적으로 관계형 데이터베이스의 목표는 **데이터 무결성**을 높이는 데 있습니다.

몇 개의 선택지 중에 하나를 선택하는 열의 경우 `COMMENT` 키워드를 활용하여 주석을 달아놓을 수 있습니다. `CHECK` 키워드를 사용한 선택사항 제약조건 및 `COMMENT` 키워드를 사용한 주석 입력, 그리고 해당 주석을 확인하는 방법은 아래와 같습니다.

<div class="termy">
    ```sh
    $ mysql > CREATE TABLE sample123 (
        -> grade INTEGER NOT NULL CHECK (grade IN (1, 2, 3)) COMMENT '1번부터 3번까지 차례로 상, 중, 하를 의미하는 성적 컬럼'
        -> );

    Query OK, 0 rows affected (0.02 sec)

    $ mysql > SELECT table_name, column_name, column_comment FROM information_schema.columns WHERE table_schema = 'sample' AND table_name = 'sample123';

    +------------+-------------+-----------------------------------------------------------------------------+
    | TABLE_NAME | COLUMN_NAME | COLUMN_COMMENT                                                              |
    +------------+-------------+-----------------------------------------------------------------------------+
    | sample123  | grade       | 1번부터 3번까지 차례로 상, 중, 하를 의미하는 성적 컬럼                      |
    +------------+-------------+-----------------------------------------------------------------------------+
    1 row in set (0.00 sec)
    ```

</div>

#### 고정길이와 가변길이

문자열의 자료형에는 **고정길이**와 **가변길이**가 있습니다. 어떤 것을 선택하는 지는 저장할 데이터를 고려해 결정해야 합니다.

예를 들어 제조번호처럼 자리수가 정해져 있는 경우에는 데이터의 최대 길이를 해당 제조번호의 자릿수의 맞춰 고정길이 문자열로 지정하는 게 좋습니다. 반대로 게시글처럼 문자열 길이의 변동폭이 클 경우 가변길이 문자열이 더 적합합니다.

데이터베이스의 열에 저장할 수 있는 크기는 꽤 작습니다. 예를 들어 VARCHAR형으로 지정할 수 있는 최대 크기는 수천 바이트 정도입니다. 이러한 경우에는 **LOB(Large Object)**를 사용합니다. LOB는 큰 데이터를 다루는 자료형이지만 인덱스를 지정할 수 없습니다. Oracle의 경우 LOB의 종류는 아래와 같습니다.

* 내부 : 테이블에 LOB 형식의 열을 생성하고 해당 열에 데이터의 실제 위치를 가리키는 **위치자(Locator)**를 저장합니다. 왜냐하면 데이터가 테이블이 아닌 LOB에 별도로 저장되어 있기 때문에 해당 세그먼트에 알맞게 접근할 수 있게 위치를 알아야 하기 때문입니다. 데이터베이스 내부에 존재하는 데이터입니다.
    * **CLOB(Character Large Object)** : 문자 대형 객체를 의미합니다. Oracle은 CLOB와 VARCHAR2 사이에 암시적 변환을 수행합니다. VARCAHR2는 VARCHAR와 동일한 자료형으로 VARCHAR의 경우 Oracle은 다른 형태로 바꾸려 노력 중이기 때문에 가변길이의 경우 VARCHAR2를 사용할 것을 추천하고 있습니다.
    * **BLOB(Binary Large Object)** : 이진 대형 객체를 의미합니다. 이미지 파일, 동영상 파일, 음원 파일 등이 이에 해당합니다.
    * **NCLOB(National Character Large Object)** : Oracle에서 정의되는 NCS(National Character Set)를 따르는 문자 대형 객체를 의미합니다. 이때 NCS는 유니코드 문자열 집합이 아닌 데이터베이스에 유니코드 문자열 데이터를 저장하기 위해 사용하는 대체 문자열 집합을 의미합니다.
* 외부 : 데이터베이스 외부에 존재하는 데이터입니다.
    * **BFILE(Binary File)** : 운영체제에 저장되는 이진 파일의 이름과 위치를 저장합니다. 읽기 전용 모드로만 접근할 수 있습니다.

!!! info "정보"

    **세그먼트(Segment)**란 객체(Object) 중에서 저장공간을 갖고 있는 것으로 대표적으로 인덱스(Index)와 테이블(Table)이 이에 해당합니다.

LOB를 사용하는 가장 큰 이유는 **데이터의 안전성**과 **데이터 저장의 일관성** 때문입니다. 예전에는 사이즈가 큰 파일의 경우 운영체제 차원에서 애플리케이션이 접근할 수 있게 디렉토리를 생성하고 그곳에 저장하여 접근하는 방식으로 관리했습니다. 그러나 해당 디렉토리를 실수로 삭제하면 데이터를 완전히 잃어버리기 때문에 부담이 있습니다.

이러한 문제로 안전성을 확보하기 위해 데이터베이스에 저장하는 방식을 선택하였고 이를 위해서 LOB가 등장하였습니다. 또한 어떤 데이터는 운영체제에서 관리하고 어떤 데이터는 데이터베이스에서 관리하여 일관성이 없을 경우 접근 권한이 꼬이거나 했을 때 문제가 발생할 수 있기 때문에 저장을 일관성 있게 하기 위하여 데이터베이스에 저장하는 방식을 선택하게 되었습니다.

가변 길이의 문자열형 중에 최대 2GB의 데이터를 저장할 수 있는 LONG형 또한 존재합니다. 그리고 이진 데이터를 저장할 수 있는 LONG RAW형도 존재합니다. 그러나 LOB는 최대 4GB의 데이터를 저장할 수 있으며 하나의 행에 여러 열이 존재할 수 있고 무작위 접근이 가능하기 때문에 LONG형보다는 LOB를 사용하도록 권장하고 있습니다. 물론 LOB의 경우 `GROUP BY`, `ORDER BY` 등의 명령을 사용할 수 없는 등의 여러 제약사항이 존재하기 때문에 상황에 따라 유동적으로 결정해야 합니다.

!!! info "정보"

    AWS S3와 같은 클라우드 스토리지 서비스를 사용하여 대용량 데이터를 별도로 저장하고 해당 데이터에 접근할 수 있는 주소를 데이터베이스에 저장하는 것도 하나의 방법입니다.

#### 기본키

기본키로 지정할 열이 생각나지 않을 경우 자동증가 열을 사용하여 기본키로 지정하는 것도 하나의 해결책입니다. **자동증가(Auto Increment)**는 `INSERT` 명령이 발생할 때 자동으로 증가됩니다.

MySQL의 경우 열을 정의할 때 `AUTO_INCREMENT` 키워드를 지정하여 설정할 수 있습니다. 이때 해당 열에 `PRIMARY KEY` 또는 `UNIQUE` 키워드를 사용하여 유일성을 지정해줘야 합니다.

기본키와 관련된 설계 요소로 [정규화](#정규화)도 있는데 이는 뒤이어 서술하도록 하겠습니다.

### ER다이어그램

이전에 관계에 대해서 살펴봤던 것처럼 테이블을 설계할 때는 테이블 간의 관계도 무척 중요합니다. 이런 테이블 간의 관계에 관한 설계도를 작성하기 위해 사용하는 게 **ER다이어그램(Entity Relational Diagram)**입니다. 풀어쓴 단어의 의미 그대로 **개체(Entity)** 간의 **관계(Relation)**를 표현한 다이어그램입니다. 참고로 여기서의 개체는 **테이블(Table)** 또는 **뷰(View)**를 의미합니다.

!!! info "정보"

    **ER다이어그램**은 보통 간단하게 **ERD**라 줄여서 부릅니다.

    ERD를 그릴 수 있는 대표적인 사이트는 [ERDCloud](https://www.erdcloud.com/)가 있습니다.


ERD의 예시는 아래 이미지와 같습니다.

<img src="https://weekwith.me/images/sql-first-step/8/1.png">


## 정규화

**정규화(Normalization)**는 데이터베이스의 테이블을 규정된 올바른 형태로 개선해나가는 걸 의미합니다. 보통 설계 단계에서 행해지지만 기존 시스템을 바꿔야할 때도 정규화하는 경우가 있습니다.

정규화에는 순서가 존재하는데 이를 참고하여 관계형 데이터베이스가 효율적으로 동작하도록 만들 수 있습니다. 물론 최적의 설계란 상황에 따라 다르기 때문에 반드시 모든 걸 따라야하는 것은 아닙니다.

### 정규화

예를 들어 아래와 같은 주문 데이터가 저장된 `주문` 테이블이 존재한다고 가정해봅시다.

|주문번호|날짜|성명|연락처|주문상품|
|---:|:-:|:-:|:--:|:----|
|1|1/1|박준용|010-xxxx|0001 연필 1개, 0002 지우개 10개|
|2|2/1|김재진|010-xxxx|0001 연필 2개, 0003 공책 3개|
|3|2/5|박준용|010-xxxx|0001 연필 3개, 0003 공책 2개|


### 제1정규형

관계형 데이터베이스의 테이블에는 하나의 셀에 하나의 값만 저장할 수 있다는 제약이 있습니다. 따라서 아래와 같이 기존 `주문상품`열을 아래와 같이 `상품코드`, `상품명`, `개수`라는 열로 가지고 있는 `주문상품`이라는 테이블로 따로 분리하는 게 좋습니다. 이때 유의할 점은 `주문번호`라는 열을 기본키로 지정하여 사용자가 주문한 내역과 관계를 형성하여 중복된 부분을 제거할 수 있다는 것입니다.

|주문번호|상품코드|상품명|개수|
|---:|:---:|:---:|---:|
|1|0001|연필|1|
|1|0002|지우개|10|
|2|0001|연필|2|
|2|0003|공책|3|
|3|0001|연필|3|
|3|0003|공책|2|

이처럼 제1정규형은 반복되는 부분을 찾아내서 테이블을 분할하고 기본키가 될 열을 작성하는 것입니다.

### 제2정규형

이때 `주문상품`이라는 테이블 속 `상품명`은 `상품코드`에 종속적입니다. 따라서 `상품코드`, `상품명`을 열로 가지고 있는 `상품`이라는 테이블을 따로 분리하는 게 좋습니다. 이때 유의할 점은 `상품코드`라는 열을 기본키로 지정하여 두 테이블 간의 관계를 형성해야 한다는 것입니다.

|상품코드|상품명|
|----:|:---:|
|0001|연필|
|0002|지우개|
|0003|공책|

이렇게 되었을 때 `상품코드` 혹은 `상품명`에 수정사항이 생길 때 더 간편하게, 실수없이 기존의 데이터를 변경할 수 있습니다.

이처럼 제2정규형은 부분 함수종속성을 찾아내서 테이블을 분할하는 것입니다. 여기서 **함수종속성**이란 키 값을 이용해 데이터를 특정지을 수 있는 것을 가리킵니다. 위 예시에서는 `상품코드`라는 키 값을 통해 상품이라는 데이터를 특정지을 수 있었습니다.

### 제3정규형

앞서 `주문` 테이블에도 고객 정보가 중복되어 있었습니다. 이를 아래와 같이 `고객번호`, `성명`, `연락처`라는 열을 가진 `고객` 테이블로 분리할 수 있습니다. 이때 `고객번호`를 기본키로 설정하여 `주문` 테이블의 외래키로 연결해 관계를 형성할 수 있습니다.

|고객번호|성명|연락처|
|---:|:---:|:---|
|1|박준용|010-xxxx|
|2|김재진|010-xxxx|

제3정규형은 기본키 이외의 부분에서 중복이 없는 지를 조사하여 테이블을 분할하는 것입니다.

실제로 정규형은 제5규형까지 존재하지만 보통 제3정규형까지의 정규화를 채택합니다.

### 정규화의 목적

정규화의 목적은 결국 데이터를 쉽게 관리하는 데 있습니다. 이를 조금 더 구체적으로 설명하자면 정규화를 통해 하나의 데이터를 무조건 한 곳에만 위치하게 하고 테이블 간의 관계는 키를 통해 형성하게 합니다. 이를 통해 갱신 등의 작업을 행할 때 저장된 한 곳만 변경하여 데이터의 정합성을 유지할 수 있습니다.

!!! info "정보"

    정규화의 종류는 앞서 이야기 한 것처럼 4정규형, 5정규형은 물론 원래는 3정규형 다음에 BCNF(Boyce-Codd Normal Form) 또한 존재합니다.

    더욱이 정규화의 목적 또한 조금 더 구체적으로는 **이상현상(Anomarly)**을 위한 것으로 이상현상의 종류에 대해서도 한 번 살펴볼 필요가 있습니다.

    따라서 이상현상과 이에 따른 정규형의 모든 종류는 추후에 더 자세히 살펴볼 예정입니다.


## 트랜잭션

데이터베이스는 **트랜잭션(Transaction)**이라는 기능을 제공합니다. `INSERT`, `UPDATE` 명령에도 트랜잭션 기능을 사용하는 데 별도로 신경쓰지 않았던 이유는 **자동 커밋(Auto Commit)**이라 불리는 기능이 동작했기 때문입니다.

### 트랜잭션

앞서 정규화에서 사용했던 테이블 `주문`과 `주문상품`의 관계를 생각해보겠습니다. 

아래는 테이블 `주문`입니다.

|주문번호|날짜|고객번호|
|---:|:-:|----:|
|1|1/1|1|
|2|2/1|2|
|3|2/5|1|

아래는 테이블 `주문상품`입니다.

|주문번호|상품코드|개수|
|---:|:---:|---:|
|1|0001|1|
|1|0002|10|
|2|0001|2|
|2|0003|3|
|3|0001|3|
|3|0003|2|

이때 테이블 `주문`과 `주문상품` 사이에는 의존관계가 존재합니다. `주문` 테이블에 행이 존재한다면 반드시 `주문상품` 테이블에도 행이 존재해야 합니다. 그렇지 않으면 주문한 상품이 없는데 주문이 된 상태이기 때문입니다. 따라서 `주문` 테이블에 행을 추가할 때 `주문상품` 테이블에도 행이 추가되어야 합니다.

#### 발주처리

주문이 발생하면 기존 주문과 구분되는 주문번호를 발행해야 합니다. 보통은 `AUTO_INCREMENT`와 같은 키워드를 통해 자동으로 증가하는 열을 기본키로 하여 중복되지 않게 합니다.

만약 두 개의 상품을 구매하면 아래와 같이 삽입(`INSERT`) 명령이 실행됩니다.

```sql
INSERT INTO 주문 VALUES(4, 3/1, 3);
INSERT INTO 주문상품 VALUES(4, 0001, 2);
INSERT INTO 주문상품 VALUES(4, 0002, 4);
```

이를 통해 적어도 두 번 이상의 `INSERT` 명령이 실행되어야 한다는 것을 알 수 있습니다. 만약 어떤 사건이 발생하여 오류가 발생한다면 해당 명령 이전에 정상적으로 실행된 `INSERT` 명령은 `DELETE` 명령을 통해 삭제해야 합니다. 예를 들어 `주문` 테이블에 정상적으로 데이터를 삽입했더라도 `주문상품` 테이블에 데이터를 삽입하는 과정에서 오류가 발생하면 `주문` 테이블에 삽입된 데이터를 삭제해야 하는 것입니다.

### 롤백과 커밋

위와 가팅 몇 단계로 처리를 나누어 SQL 명령을 실행하는 경우 트랜잭션을 사용합니다. 오류가 발생해도 트랜잭션을 **롤백(Rollback)**하여 종료할 수 있고 정상적으로 모든 작업이 처리가 되면 **커밋(Commit)**을 통해 변경사항을 적용하고 트랜잭션을 종료합니다.

#### 자동커밋

트랜잭션을 사용해서 데이터를 추가할 때는 자동 커밋을 꺼야합니다. 암묵적으로 자동커밋 상태로 되어 있기 때문에 `START TRANSACTION` 명령을 사용하여 명시적으로 트랜잭션의 시작을 선언해야 합니다.

만약 정상적으로 작동하여 트랜잭션 내에서 실행한 명령을 적용한 후 종료하고 싶으면 `COMMIT` 명령을 사용합니다. 그러나 오류가 발생하여 실행한 명령을 파기하고 종료하고 싶으면 `ROLLBACK` 명령을 사용합니다.

트랜잭션 내에서 실행된 SQL 명령은 임시 데이터 영역에서 수행되다가 `COMMIT` 명령을 통해 정식 데이터 영역으로 변경이 적영되거나 `ROLLBACK` 명령을 통해 정식 데이터 영역으로 가지 않고 임시 데이터 영역에서 그대로 삭제됩니다.

앞서 두 개의 상품을 구매하여 삽입(`INSERT`)하는 경우를 트랜잭션을 통해 구현하면 아래와 같습니다.

```sql
START TRANSACTION;
INSERT INTO 주문 VALUES(4, 3/1, 3);
INSERT INTO 주문상품 VALUES(4, 0001, 2);
INSERT INTO 주문상품 VALUES(4, 0002, 4);
COMMIT;
```

이와 같이 트랜잭션을 통한 일련의 처리방법을 *트랜잭션을 걸어서 실행한다* 혹은 *트랜잭션 내에서 실행한다*라고 말합니다.

### 트랜잭션 사용법

트랜잭션을 사용할 때 MySQL의 경우 `START TRANSACTION` 또는 `BEGIN TRANSCTION` 명령을 사용하며 SQL Server나 PostgreSQL의 경우 `BEGIN TRANSACTION` 명령만 유효합니다. 그리고 Oracle과 DB2의 경우 트랜잭션을 시작하는 명령이 별도로 존재하지 않습니다. 아직 트랜잭션 부분에 대한 표준화가 진행되지 못했기 때문입니다.

자동 커밋은 클라이언트 도구의 기능입니다. 따라서 트랜잭션을 사용할 경우에는 클라이언트 도구의 자동 커밋 사용 여부 등을 확인해야 합니다. 예를 들면 MySQL는 mysql 클라이언트의 자동 커밋 여부를 확인해야 합니다.

!!! info "정보"

    보통 **트랜잭션(Transaction)**은 데이터베이스의 상태를 변화시키기 위해서 수행하는 작업의 **단위**를 의미합니다.

    따라서 하나의 명령에 트랜잭션을 걸어 실행하는 것보다 여러 명령을 묶어 하나의 단위로 실행하는 경우에 많이 사용됩니다.