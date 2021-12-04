---
title: "의존성 - 첫 걸음"
description: ""
---

# 의존성 - 첫 걸음

**FastAPI**는 매우 강력하지만 직관적인 **<abbr title="컴포넌트, 자원, 제공자, 서비스, 인젝터블로 알려진">의존성 주입</abbr>** 시스템을 갖고 있습니다.

이것은 사용하기 매우 단순하게, 그리고 어떤 개발자라도 **FastAPI**를 사용하는 다른 컴포넌트와 이것을 쉽게 통합할 수 있게 설계되어 있습니다.

## "의존성 주입"이란 무엇인가

**"의존성 주입"**은, 프로그래밍에서, 작동을 위해 필요로 하는 걸 코드 (이 경우, *경로 동작 함수*) 에 선언하거나 "의존성"을 사용하는 방법을 의미합니다.

그리고, 그 시스템 (이 경우 **FastAPI**) 은 (의존성을 "주입"하여) 코드에 필요한 의존성을 제공하는 데 필요한 모든 일을 처리합니다.

이것은 다음과 같은 상황에서 매우 유용합니다:

* 로직 공유 (동일한 코드 로직의 반복).
* 데이터베이스 연결 공유.
* 보안, 인증, 역할 요구 사항, 기타 등등 강요.
* 그리고 기타 등등...

코드 반복을 최소화하는 동시에, 이 모든 상황에 사용될 수 있습니다.

## 첫 단계

매우 간단한 예제를 한 번 살펴봅시다. 지금 당장은, 유용하지 않을 정도로 간단합니다.

그러나 이 방법을 통해 **의존성 주입** 시스템이 어떻게 작동하는 지 살펴볼 수 있습니다.

### 의존성 또는, "의존 가능한 것" 생성

의존성에 집중해봅시다.

이것은 단지 *경로 동작 함수*가 가질 수 있는 것과 동일한 모든 매개변수를 가질 수 있는 함수일 뿐입니다:

```Python hl_lines="8-9"
{!../docs_src/fastapi/dependencies/tutorial001.py!}
```

이게 전부입니다.

**두 줄이 끝입니다**.

그리고 이것은 *경로 동작 함수*와 동일한 모양과 구조를 가집니다.

이것은 "데코레이터"가 없는 (`@app.get*("/some-path")` 가 없는) *경로 동작 함수*라 생각하면 됩니다.

그리고 원하는 모든 걸 반환할 수 있습니다.

이번 사례에서, 의존성은 다음과 같은 매개변수를 받을 것이라 예상합니다:

* `str`인 선택적 쿼리 매개변수 `q`.
* 기본 값이 `0`이고, `int`인 선택적 쿼리 매개변수 `skip`.
* 기본 값이 `100`이고, `int`인 선택적 쿼리 매개변수 `limit`.

그리고 위 값을 포함한 `dict`을 반환할 뿐입니다.

### `Depends` 임포트

```Python hl_lines="3"
{!../docs_src/fastapi/dependencies/tutorial001.py!}
```

### "의존" 내부, 의존성 선언

*경로 동작 함수* 매개변수와 함께 `Body`, `Query`, 기타 등등을 사용한 것과 동일한 방법으로, 새로운 매개변수와 함께 `Depends`를 사용합니다:

```Python hl_lines="13  18"
{!../docs_src/fastapi/dependencies/tutorial001.py!}
```

`Body`, `Query`, 기타 등등을 사용한 것과 동일한 방법으로 함수의 매개변수에 `Depends`를 사용했지만, `Depends`는 이것과는 조금 다른 방식으로 작동합니다.

오직 단일 매개변수에만 `Depends`를 사용할 수 있습니다.

그리고 그 함수는 *경로 동작 함수*와 동일한 방법으로 매개변수를 가집니다.

!!! 팁
    다음 챕터에서 함수 외에도, 의존성으로 사용되는, 다른 "것"을 확인할 수 있습니다.

새로운 요청이 도착했을 때, **FastAPI**는 다음과 같이 처리합니다:

* 알맞은 매개변수와 함께 의존성 ("의존 가능한") 함수 호출.
* 함수로부터 결과 획득.
* *경로 동작 함수* 내부 매개변수에 해당 결과를 할당.

```mermaid
graph TB
common_parameters(["common_parameters"])
read_items["/items/"]
read_users["/users/"]
common_parameters --> read_items
common_parameters --> read_users
```

공유된 코드를 단 한 번만 이러한 방법으로 작성하면 **FastAPI**는 *경로 동작*을 위해 이를 호출합니다.

!!! check "확인"
    특별한 클래스를 생성하고 **FastAPI**에 전달하여 어딘가에 "등록"하거나 이와 유사한 작업을 할 필요가 없다는 걸 명심하기 바립니다.

    단지 `Depends`에 전달하면 **FastAPI**는 나머지를 어떻게 해야하는 지 알고 있습니다.

## `async`를 사용하는 경우 또는 `async`를 사용하지 않는 경우

의존성이 (*경로 동작 함수*와 동일한 방법으로) **FastAPI**에 의해 호출되기 때문에, 함수를 정의하는 것과 동일한 규칙이 적용됩니다.

`async def` 또는 평범한 `def`를 사용할 수 있습니다.

그리고 평범한 `def` *경로 동작 함수* 내부에 `async def`를 사용한 의존성 선언, 또는 `async def` *경로 동작 함수* 내부에 `def`를 사용한 의존성 선언, 기타 등등의 선언이 가능합니다.

무엇이든 상관 없습니다. **FastAPI**는 무엇을 해야할 지 알고 있습니다.

!!! note "참고"
    만약 이에 관해 모른다면, [Async: *"빠르게 훑어보기"*](../../async.md){.internal-link target=_blank} 문서 내부의 `async` 및 `await` 부분을 확인하기 바랍니다.

## OpenAPI와의 통합

모든 의존성 (그리고 하위-의존성) 의 요청 선언, 검증, 그리고 요구사항은 동일한 OpenAPI 스키마에 통합됩니다.

따라서, 마찬가지로 대화형 문서는 이 의존성들로부터 모든 정보를 받게 됩니다:

<img src="/images/fastapi/tutorial/dependencies/image01.png">


## 간단한 사용법

살펴보면, *경로*와 *동작*이 일치할 때마다 사용되도록 *경로 동작 함수*가 선언되고, **FastAPI**는 요청으로부터 데이터를 추출하여, 올바른 매개변수를 사용하여 함수를 호출합니다.

사실, 모든 (또는 거의 대부분) 의 웹 프레임워크가 이것과 동일한 방법으로 작동합니다.

절대 이 함수들을 직접 호출하지 않습니다. 프레임워크 (이번 사례에서, **FastAPI**) 에 의해 호출됩니다.

의존성 주입 시스템을 사용하면, *경로 동작 함수*가 *경로 동작 함수*보다 먼저 실행되어야 할 다른 것에 "의존"한다는 것을 **FastAPI**에 알릴 수 있으며, **FastAPI**는 이를 실행하고 결과를 "주입"합니다.

"의존성 주입"과 동일한 개념을 다루는 일반적인 용어는 다음과 같습니다:

* 자원
* 제공자
* 서비스
* 인젝터블
* 컴포넌트

## **FastAPI** 플러그-인

**의존성 주입 시스템**을 사용하여 통합 및 "플러그-인"이 구축될 수 있습니다. 그러나 사실, **"플러그-"인을 설치할 필요 없이**, 의존성을 사용하여 경로 동작 함수에 사용 가능한 무수히 많은 통합 및 상호작용을 선언할 수 있습니다.

그리고 의존성은 매우 단순하고 직관적인 방법으로 생성될 수 있으므로 단지 필요한 파이썬 패키지를 임포트하고, *문자 그대로*, 몇 줄의 코드만으로 API 함수와 통합될 수 있습니다.

다음 챕터에서 이것과 관련된, 관계형 및 NoSQL 데이터베이스, 보안, 기타 등등에 관한 예시를 볼 수 있습니다.

## **FastAPI** 호환성

의존성 주입 시스템의 단순함은 **FastAPI**가 다음 요소들과 호환될 수 있게 만듭니다:

* 모든 관계형 데이터베이스
* NoSQL 데이터베이스
* 외부 패키지
* 외부 API
* 인증 및 인가 시스템
* API 사용 모니터링 시스템
* 응답 데이터 주입 시스템
* 기타 등등.

## 단순함과 강력함

계층적 의존성 주입 시스템을 정의하고 사용하는 데 매우 단순하지만, 여전히 매우 강력합니다.

의존성 자체를 정의할 수 있는 의존성을 정의할 수 있습니다.

결국에는, 계층적 의존성 트리가 구축되고, **의존성 주입** 시스템은 이러한 모든 의존성 (그리고 그들의 하위-의존성) 을 해결하고 각 단계에서 결과를 제공 (주입) 합니다.

예를 들어, 4개의 API 엔드포인트 (*경로 동작*) 가 있다고 가정해봅시다:

* `/items/public/`
* `/items/private/`
* `/users/{user_id}/activate`
* `/items/pro/`

그러면 단지 의존성 및 하위-의존성을 사용하여 각각에 대해 서로 다른 권한 요구사항을 추가할 수 있습니다:

```mermaid
graph TB
current_user(["current_user"])
active_user(["active_user"])
admin_user(["admin_user"])
paying_user(["paying_user"])
public["/items/public/"]
private["/items/private/"]
activate_user["/users/{user_id}/activate"]
pro_items["/items/pro/"]
current_user --> active_user
active_user --> admin_user
active_user --> paying_user
current_user --> public
active_user --> private
admin_user --> activate_user
paying_user --> pro_items
```

## **OpenAPI**와의 통합

이 모든 의존성들은, 요구사항을 선언하면서, 매개변수, 검증, 기타 등등을 *경로 동작*에 추가합니다.

**FastAPI**는 이모든 것들을 OpenAPI 스키마에 추가하기 때문에, 대화형 문서 시스템에 보여집니다.