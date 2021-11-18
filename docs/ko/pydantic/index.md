---
title: "개요"
---

# 개요

[![CI](https://github.com/samuelcolvin/pydantic/workflows/CI/badge.svg?event=push)](https://github.com/samuelcolvin/pydantic/actions?query=event%3Apush+branch%3Amaster+workflow%3ACI)
[![Coverage](https://coverage-badge.samuelcolvin.workers.dev/samuelcolvin/pydantic.svg)](https://github.com/samuelcolvin/pydantic/actions?query=event%3Apush+branch%3Amaster+workflow%3ACI)
[![pypi](https://img.shields.io/pypi/v/pydantic.svg)](https://pypi.python.org/pypi/pydantic)
[![CondaForge](https://img.shields.io/conda/v/conda-forge/pydantic.svg)](https://anaconda.org/conda-forge/pydantic)
[![downloads](https://pepy.tech/badge/pydantic/month)](https://pepy.tech/project/pydantic)
[![license](https://img.shields.io/github/license/samuelcolvin/pydantic.svg)](https://github.com/samuelcolvin/pydantic/blob/master/LICENSE)

문서 속 버전: v1.8.2

파이썬 자료형 어노테이션을 사용한 데이터 검증와 설정 관리.

*pydantic*은 실행시 자료형 힌트를 강제하고, 데이터가 유효하지 않을 때 사용자 친화적인 오류를 제공합니다.

데이터의 순수성과, 표준적인 파이썬으로 있어야 하는 방법; *pydantic*을 통해 검증을 진행하여 정의합니다.

## 예시

```py
{!../docs_src/pydantic/index_main.py!}
```

_(이 스크립트는 완전하므로, "있는 그대로" 실행되어야 합니다.)_

다음과 같은 일이 발생합니다:

* `id` 는 정수 자료형입니다; 어노테이션-전용 선언은 *pydantic*에게 이 필드가 필수적으로 요구된다는 걸 알려줍니다. 문자열, 바이트 또는 실수는 가능한 경우 정수로 강제 변환됩니다; 그렇지 않은 경우 예외가 발생합니다. 
* `name` 은 기본 값을 가지고 있고, 필수적으로 요구되지 않기 때문에; 제공되는 기본 값으로인해 문자열로 추론됩니다.
* `signup_ts` 은 필수적으로 요구되지 않는 (그리고 값이 제공되지 않았을 때 ``None`` 값을 가지는) 시간날짜 필드입니다. *pydantic*은 유닉스 타임스탬프 정수 (예를 들어 `1496498400` ) 또는 날짜와 시간을 나타내는 문자열을 처리합니다.
* `friends` 는 파이썬 타이핑 시스템을 사용하며, 정수들의 리스트를 요구합니다. id 와 마찬가지로, 정수형 객체는 정수로 변환됩니다.


만약 검증에 실패하면 *pydantic*은 무엇이 잘못되었는지에 대한 분석과 함께 오류를 발생시킵니다:

출력물은 아래와 같습니다:

```py
{!../docs_src/pydantic/index_error.py!}
```
outputs:
```json
{!../docs_src/pydantic/index_error.json!}
```

## 근거

따라서 *pydantic*은 몇가지 멋진 새 언어의 기능을 사용하지만, 사용해야하는 이유는 무엇입니까?

**IDE/linter/brain와 잘 어울림**
: 새로 배워야 할 스키마 정의 마이크로-언어가 없습니다. 만약 파이썬 타입 힌트를 어떻게 사용하는지 알고 있다면, *pydantic*을 사용하는 방법을 알고 있는 것입니다. 자료 구조는 자료형 어노테이션을 사용하여 정의한 클래스의 인스턴스일 뿐이고, 따라서 자동-완성, linting, [mypy](usage/mypy.md), (특히 [PyCharm](pycharm_plugin.md)과 같은) IDE, 그리고 직관은 모두 검증된 데이터를 통해 올바르게 작동할 것입니다.

**이중 사용**
: *pydantic*의 [BaseSettings](usage/settings.md) 클래스를 통해 *pydantic*이 "요청 데이터에 대한 검증" 컨텍스트 및 "내 시스템 설정 로드" 컨텍스트에 사용될 수 있게 할 수 있습니다. 주요한 차이점은 시스템 세팅은 환경 변수로부터 읽힐 수 있고, DSN과 파이썬 객체와 같이 더 복잡한 객체가 종종 필수적으로 요구된다는 것입니다.

**빠른 속도**
: [벤치마크](benchmarks.md)를 보면 모든 다른 테스트된 라이브러리보다 *pydantic*이 더 빠릅니다.

**복잡한 구조에 대한 검증** 
: [재귀적 *pydantic* 모델](usage/models.md#recursive-models), `typing` 의 [표준 자료형](usage/types.md#standard-library-types) (예를 들어 `List`, `Tuple`, `Dict` 기타 등등) 그리고 [검증기](usage/validators.md)를 사용하여 복잡한 데이터 스키마를 깔끔하고 쉽게 정의하고, 검증을 진행하고, 구문 분석할 수 있습니다.

**확장성**
: *pydantic*을 통해 [사용자 정의 데이터 자료형](usage/types.md#custom-data-types)을 정의하거나 [`validator`](/usage/validators.md) 데코레이터로 데코레이트된 모델의 메서드를 통해 검증을 확장할 수 있습니다.

**데이터클래스 통합**
: `BaseModel`과 마찬가지로, *pydantic*은 입력 데이터 구문 분석 및 검증을 사용하는 (대부분의) 바닐라 파이썬 데이터클래스를 만드는 [`dataclass`](usage/dataclasses.md) 데코레이터를 제공합니다.

## Pydantic 사용처

다음 조직 및 패키지를 포함하여, 몇 백개의 조직과 패키지가 *pydantic*을 사용중입니다:

[FastAPI](https://fastapi.tiangolo.com/)
: *pydantic* 및 Starlette을 기반으로 한 배우기 쉽고, 코드를 작성하고 제품을 준비하는데 빠른, 높은 성능의 API 프레임워크입니다.

[Project Jupyter](https://jupyter.org/)
: [서브프로젝트](https://github.com/samuelcolvin/pydantic/issues/773)를 위해 Jupyter notebook의 개발자들은 *pydantic*을 사용 중입니다.

**Microsoft**
: "핵심 Windows 제품 및 일부 Office 제품에 통합"되는 것 같이, [많은 서비스](https://github.com/tiangolo/fastapi/pull/26#issuecomment-463768795))에 (FastAPI를 통한) *pydantic*을 사용 중입니다.

**Amazon Web Services**
: 확률적 시계열 모델링 라이브러리 오픈 소스인, [gluon-ts](https://github.com/awslabs/gluon-ts)에 *pydantic*을 사용 중입니다.

**The NSA**
: 자동 프레임워크 오픈 소스인, [WALKOFF](https://github.com/nsacyber/WALKOFF)에 *pydantic*을 사용 중입니다.

**Uber**
: TensorFlow 래퍼 오픈 소스인, [Ludwig](https://github.com/uber/ludwig)에 *pydantic*을 사용 중입니다.

**Cuenca**
: (API 문서를 포함한) 여러 내부 도구와 멕시코에서 실시간, 매일, 은행 간 송금을 처리하는 데 사용되는, [stpmex](https://github.com/cuenca-mx/stpmex-python)와 같은 오픈 소스 프로젝트에 *pydantic*을 사용하는 멕시코 네오뱅크입니다.

[The Molecular Sciences Software Institute](https://molssi.org)
: 양자 화학을 위한 대규모 분산 컴퓨팅 프레임워크, [QCFractal](https://github.com/MolSSI/QCFractal)에 *pydantic*을 사용합니다.

[Reach](https://www.reach.vote)
다중 미션-핵심 마이크로서비스을 안정적으로 작동시키기 위해 (FastAPI을 통한) *pydantic* 및 (Samuel의 우수한 비동기 작업 큐) [*arq*](https://github.com/samuelcolvin/arq)을 신뢰합니다.

*pydantic*을 사용한 오픈 소스 프로젝트에 관한 더 포괄적인 목록은 [github에 기재된 의존성 목록](https://github.com/samuelcolvin/pydantic/network/dependents)을 확인하시기 바랍니다.

## Pydantic 관련 논의

아래는 팟캐스트와 비디오에서 행해지는 pydantic 관련 논의들입니다.

[Talk Python To Me](https://talkpython.fm/episodes/show/313/automate-your-data-exchange-with-pydantic){target=_blank}
: *pydantic*의 창시자, Michael Kennedy 그리고 Samuel Colvin이 pydantic의 역사 및 그 사용성과 이점을 깊게 설명합니다.

[Podcast.\_\_init_\_\](https://www.pythonpodcast.com/pydantic-data-validation-episode-263/){target=_blank}
: *pydantic*의 등장 배경과 다음 단계로 향할 아이디어에 관해 pydantic 창시자 Samuel Colvin과 함께 논의하는 영상입니다.

[Python Bytes 팟캐스트](https://pythonbytes.fm/episodes/show/157/oh-hai-pandas-hold-my-hand){target=_blank}
: "*이것은 매우 정교한 문제들을 해결해주는 단순한 프레임워크입니다... 파이썬 타입 어노테이션을 사용한 데이터 검증과 설정 관리, 그리고 행복을 안겨다주는 파이썬 어노테이션까지... 이미 당신이 가지고 있는 모든 IDE와 자동으로 잘 작동합니다.*" --Michael Kennedy

[Python pydantic 소개 - 당신의 데이터 클래스에 강력한 힘 제공](https://www.youtube.com/watch?v=WJmqgJn9TXg){target=_blank}
: 파이썬 피자 컨퍼런스의 Alexander Hultner가 새로운 사용자에게 pydantic을 소개하고 pydantic의 핵심 기능을 살펴보기 위한 영상입니다.