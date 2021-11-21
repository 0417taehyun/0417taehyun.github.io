---
title: "SQLModel"
description: ""
---

# SQLModel

<p align="center">
  <a href="https://sqlmodel.tiangolo.com"><img src="https://sqlmodel.tiangolo.com/img/logo-margin/logo-margin-vector.svg" alt="SQLModel"></a>
</p>
<p align="center">
    <em>단순함, 호환성, 그리고 견고성을 위해 설계된, 파이썬 SQL 데이터베이스, SQLModel.</em>
</p>
<p align="center">
<a href="https://github.com/tiangolo/sqlmodel/actions?query=workflow%3ATest" target="_blank">
    <img src="https://github.com/tiangolo/sqlmodel/workflows/Test/badge.svg" alt="Test">
</a>
<a href="https://github.com/tiangolo/sqlmodel/actions?query=workflow%3APublish" target="_blank">
    <img src="https://github.com/tiangolo/sqlmodel/workflows/Publish/badge.svg" alt="Publish">
</a>
<a href="https://codecov.io/gh/tiangolo/sqlmodel" target="_blank">
    <img src="https://img.shields.io/codecov/c/github/tiangolo/sqlmodel?color=%2334D058" alt="Coverage">
</a>
<a href="https://pypi.org/project/sqlmodel" target="_blank">
    <img src="https://img.shields.io/pypi/v/sqlmodel?color=%2334D058&label=pypi%20package" alt="Package version">
</a>
</p>

---

**문서**: <a href="https://sqlmodel.tiangolo.com" target="_blank">https://sqlmodel.tiangolo.com</a>

**소스 코드**: <a href="https://github.com/tiangolo/sqlmodel" target="_blank">https://github.com/tiangolo/sqlmodel</a>

---

SQLModel은 파이썬 객체를 사용한, 파이썬 코드를 통해 <abbr title="관계형 데이터베이스라 불리는">SQL 데이터베이스</abbr>와 상호작용하는 라이브러리입니다. 이것인 직관적이고, 쉽게 사용할 수 있고, 호환성이 높고, 견고하게 설계되었습니다.

**SQLModel**은 파이썬의 자료형 어노테이션을 기반으로 하며, <a href="https://pydantic-docs.helpmanual.io/" class="external-link" target="_blank">Pydantic</a>과 <a href="https://sqlalchemy.org/" class="external-link" target="_blank">SQLAlchemy</a>를 통해 작동합니다.

중요한 기능은 다음과 같습니다:

* **직관적 사용**: 뛰어난 편집기 지원을 받을 수 있습니다. 모든 곳에서 <abbr title="자동-완성, 자동 완성 기능, 지능형 코드 완성으로 알려진">완성 기능</abbr>을 사용할 수 있습니다. 디버깅하는데 더 적은 시간이 소요됩니다. 사용하고 배우기 쉽게 설계되었습니다. 문서를 읽는데 더 적은 시간이 소요됩니다.
* **쉬운 사용**: 합리적인 기본값을 가지며 작성하는 코드를 단순화하기 위해 많은 작업을 밑단에서 수행합니다.
* **호환성**: **FastAPI**, Pydantic, 그리고 SQLAlchemy와 호환되게 설계되었습니다.
* **확장 가능성**:  SQLAlchemy와 Pydantic의 모든 기능을 기반으로 하여 사용 가능합니다.
* **단순함** : 코드 중복을 최소화합니다. 단일 자료형 어노테이션은 많은 작업을 수행합니다. SQLAlchemy와 Pydantic의 모델을 복제할 필요가 없습니다.

## FastAPI 속 SQL 데이터베이스

<a href="https://fastapi.tiangolo.com" target="_blank"><img src="https://fastapi.tiangolo.com/img/logo-margin/logo-teal.png" style="width: 20%;"></a>

**SQLModel**은 <a href="https://fastapi.tiangolo.com" class="external-link" target="_blank">FastAPI</a>와 동일한 <a href="https://tiangolo.com/" class="external-link" target="_blank">제작자</a>에 의해 만들어져, FastAPI 애플리케이션 속 SQL 데이터베이스와 쉽게 상호작용할 수 있게 설계되었습니다. 😁

SQLAlchemy와 Pydantic을 결합하여 작성한 **코드를 최대한 단순화**하며, 코드 중복을 최소한으로 줄여주면서도, **최고의 개발자 경험**을 가능하게 합니다.

**SQLModel**은, 사실, **Pydantic**과 **SQLAlchemy** 위에 얹어진 얇은 층으로, 그것들과 호환될 수 있게 신경써서 설계되었습니다.

## 요구 사항

최신 및 현재 지원되는 파이썬 버전 (지금, <a href="https://www.python.org/downloads/" class="external-link" target="_blank">파이썬은 3.6이상의 버전을 지원합니다</a>) 이 요구됩니다.

**SQLModel**이 **Pydantic**과 **SQLAlchemy**를 기반으로 하기 때문에, 그것들을 필요로 합니다. 둘 다 SQLModel을 설치할 때 자동으로 설치됩니다.

## 설치

<div class="termy">

```console
$ pip install sqlmodel
---> 100%
Successfully installed sqlmodel
```

</div>

## 예시

데이터베이스, SQL, 그리고 다른 모든 것에 대한 소개는 <a href="https://sqlmodel.tiangolo.com" target="_blank">SQLModel 문서</a>를 참고하시면 됩니다.

여기 간단한 예시가 있습니다. ✨

### SQL 테이블

다음과 같은 필드를 가진 `hero`라는 이름의 SQL 테이블이 있다고 상상해봅시다:

* `id`
* `name`
* `secret_name`
* `age`


그리고 다음과 같은 데이터를 저장하기를 원합니다:

| id | name | secret_name | age |
-----|------|-------------|------|
| 1  | Deadpond | Dive Wilson | null |
| 2  | Spider-Boy | Pedro Parqueador | null |
| 3  | Rusty-Man | Tommy Sharp | 48 |

### SQLModel의 모델 생성

이제 다음과 같이 **SQLModel**의 모델을 생성할 수 있습니다:

```Python
from typing import Optional

from sqlmodel import Field, SQLModel


class Hero(SQLModel, table=True):
    id: Optional[int] = Field(default=None, primary_key=True)
    name: str
    secret_name: str
    age: Optional[int] = None
```

`Hero`라는 클래스는 파이썬 코드로 작성된 SQL 테이블과 동일한 **SQLModel**의 모델입니다.

그리고 각각의 클래스 어트리뷰트는 각각의 **테이블 열**과 동일합니다.

### 행 생성

이제 테이블 **각각의 행**을 모델의 **인스턴스**로써 **생성**합니다:

```Python
hero_1 = Hero(name="Deadpond", secret_name="Dive Wilson")
hero_2 = Hero(name="Spider-Boy", secret_name="Pedro Parqueador")
hero_3 = Hero(name="Rusty-Man", secret_name="Tommy Sharp", age=48)
```

이렇게 하면, **테이블**과 **행**을 나타내는 **클래스** 및 **인스턴스**와 함께 기존의 파이썬 코드를 사용할 수 있으며, 그런 방식으로 **SQL 데이터베이스**와 통신할 수 있습니다.

### 편집기의 지원

모든 것은 최고의 편집기 지원과 함께, 최고의 개발자 경험이 가능하게 설계 되었습니다.

여기에는 **자동 완성**이 포함됩니다:

<img class="shadow" src="https://sqlmodel.tiangolo.com/img/index/autocompletion01.png">

그리고 **인라인 에러**도 포함됩니다:

<img class="shadow" src="https://sqlmodel.tiangolo.com/img/index/inline-errors01.png">

### 데이터베이스 저장

빠르게 **튜토리얼**를 따라하며 **SQLModel**에 관한 더 많은 것들을 배울 수 있지만, 지금 당장 어떻게 이 모든 것들을 작성하고 데이터베이스에 저장하는지 맛보고 싶다면, 아래와 같이 작성할 수 있습니다:

```Python hl_lines="18  21  23-27"
from typing import Optional

from sqlmodel import Field, Session, SQLModel, create_engine


class Hero(SQLModel, table=True):
    id: Optional[int] = Field(default=None, primary_key=True)
    name: str
    secret_name: str
    age: Optional[int] = None


hero_1 = Hero(name="Deadpond", secret_name="Dive Wilson")
hero_2 = Hero(name="Spider-Boy", secret_name="Pedro Parqueador")
hero_3 = Hero(name="Rusty-Man", secret_name="Tommy Sharp", age=48)


engine = create_engine("sqlite:///database.db")


SQLModel.metadata.create_all(engine)

with Session(engine) as session:
    session.add(hero_1)
    session.add(hero_2)
    session.add(hero_3)
    session.commit()
```

이것은 3명의 영웅과 함께 **SQLite** 데이터베이스를 저장합니다.

### 데이터베이스에서 조회

이제 아래 예시와 같이, 동일한 데이터베이스에서 조회를 위한 쿼리를 작성할 수 있습니다:

```Python hl_lines="15-18"
from typing import Optional

from sqlmodel import Field, Session, SQLModel, create_engine, select


class Hero(SQLModel, table=True):
    id: Optional[int] = Field(default=None, primary_key=True)
    name: str
    secret_name: str
    age: Optional[int] = None


engine = create_engine("sqlite:///database.db")

with Session(engine) as session:
    statement = select(Hero).where(Hero.name == "Spider-Boy")
    hero = session.exec(statement).first()
    print(hero)
```

## 모든 곳에서의 편집기 지원

**SQLModel**은 **심지어 데이터베이스에서 데이터를 조회한 뒤에도**, 당신에게 최고의 개발자 경험과 편집기의 지원을 제공하게 신중히 설계되었습니다.

<img class="shadow" src="https://sqlmodel.tiangolo.com/img/index/autocompletion02.png">

## SQLAlchemy와 Pydantic

위 `Hero` 클래스는 **SQLModel**의 모델입니다.

그러나 동시에, ✨ **SQLAlchemy** 모델이기도 합니다 ✨. 따라서, 이를 결합하여 다른 SQLAlchemy 모델과 함께 사용하거나, SQLAlchemy를 사용한 애플리케이션을 쉽게 **SQLModel**로 마이그레이션 할 수 있습니다.

또한 동시에, ✨ **Pydantic** 모델이기도 합니다 ✨. 코드 중복을 피하면서 모든 **데이터 모델**을 정의하기 위해 상속을 사용할 수 있습니다. 따라서 **FastAPI**와 함께 사용하기 매우 쉽습니다.

## 라이선스

이 프로젝트는 MIT 라이선스 조건을 바탕으로 라이선스가 부여됩니다.
