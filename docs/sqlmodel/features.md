---
title: "특징"
---

# 특징

## **FastAPI**를 위한 설계

FastAPI와 동일한 <a href="https://tiangolo.com/" class="external-link" target="_blank">제작자</a>가 **SQLModel**을 만들었습니다.

<a href="https://fastapi.tiangolo.com" target="_blank"><img src="https://fastapi.tiangolo.com/img/logo-margin/logo-teal.png" style="width: 20%;"></a>

동일한 설계와 아이디어를 따르며, 가장 직관적인 방식으로 FastAPI 애플리케이션 속 SQL 데이터베이스와 상호작용할 수 있게 제작되었습니다.

그럼에도 불구하고, SQLModel은 FastAPI와 완전히 **독립적**이며 애플리케이션의 어떤 다른 자료형과도 함께 사용될 수 있습니다. 따라서 여전히 이 기능의 이점을 누릴 수 있습니다.

## 모던 파이썬

모든 것이 표준 <abbr title="3.6 이상의, 최근 지원된 파이썬 버전">모던 **파이썬**</abbr>의 자료형 어노테이션 기반입니다. 새로 배워야 할 문법은 없습니다. 단지 표준 모던 파이썬일 뿐입니다.

만약 (SQLModel 또는 FastAPI를 사용하지 않더라도) 파이썬 자료형 사용법에 대해 2분 정도의 복습 시간이 필요하다면, FastAPI 튜토리얼 부분을 확인하시기 바랍니다: <a href="https://fastapi.tiangolo.com/python-types/" class="external-link" target="_blank">파이썬 자료형 입문</a>.

[튜토리얼 - 사용자 지침서]((tutorial/index.md)): 첫 단계 부분에서 20초 정도 걸리는 복습을 해볼 수도 있습니다.

## 편집기 지원

**SQLModel**은 자동 완성을 어디에서나 사용할 수 있게 하여, 최고의 개발자 경험을 보장하기 위해 쉽고 직관적으로 사용할 수 있게 디자인 되었습니다.

아래를 통해 어떻게 편집기가 돕는지 확인할 수 있습니다:

* <a href="https://code.visualstudio.com/" class="external-link" target="_blank">Visual Studio Code</a>:

<img class="shadow" src="../images/index/autocompletion02.png">

* <a href="https://www.jetbrains.com/pycharm/" class="external-link" target="_blank">PyCharm</a>:

<img class="shadow" src="../images/features/autocompletion01.png">

**최소한**의 코드를 작성하면서 모든 작업을 완료할 수 있습니다.

모델 속 개별적인 어트리뷰트의 자료형을 `None` 등으로 계속 추론할 필요가 없습니다. **SQLModel**이 **표준 파이썬 자료형 어노테이션**을 기반으로 하기 때문에 편집기는 모든 것을 도울 수 있습니다.

**SQLModel**은 심지어 최고의 개발자 경험을 보장하기 위해 파이썬 자료형 어노테이션에 대한 최신의 <a href="https://github.com/microsoft/pyright/blob/main/specs/dataclass_transforms.md" class="external-link" target="_blank">개발 중인 표준</a>을 따르기 때문에, 새로운 모델 인스턴스를 생성하는 동안에도 인라인 에러 및 자동 완성이 발생합니다.

<img class="shadow" src="../images/index/autocompletion01.png">

!!! info "정보"
    걱정하지 마시기 바랍니다. 개발 중인 표준을 따르는 건 오로지 편집기의 지원에 대해서만 영향을 주거나 편집기의 지원 기능만을 개선합니다.

    성능이나 정확성에 영향을 미치지는 않습니다. 그리고 진행되는 표준이 더이상 사용되지 않을 경우 코드는 이에 영향을 받지 않습니다.

    한편, 다른 라이브러리에서는 얻을 수 없는 (자료형 검사와 같은) 인라인 에러 및 자동 완성 기능을 경험할 것입니다. 🎉


## 단순함

**SQLModel**은 모든 것에 **선택 사항**이 존재하는, **합리적인 기본값**이 있습니다. 

그러나 기본적으로, 모두 **"작동"**합니다.

데이터에 대해 가장 단순한 (그러면서 가장 직관적인) 자료형 어노테이션을 사용해 시작할 수 있습니다.

그리고 이후, SQLAlchemy와 Pydantic의 모든 기능을 사용하여 모든 것을 미세 조정 할 수 있습니다.

## Pydantic 기반

**SQLModel**은 Pydantic을 기반으로 하며 그것과 동일한 설계, 문법 그리고 아이디어를 유지합니다.

밑단에서, ✨ **SQLModel**의 모델은 또한 **Pydantic**의 모델입니다. ✨

그렇게 되기까지 많은 연구와 노력이 있었습니다.

그것은 자동 데이터 **검증**, **직렬화**, 그리고 **문서화**를 포함한 모든 **Pydantic 기능**을 사용할 수 있다는 걸 의미합니다. Pydantic을 사용하는 것과 동일한 방식으로 SQLModel을 사용할 수 있습니다.

심지어는 SQL 테이블을 의미하지 *않는* SQLModel의 모델을 생성할 수 있습니다. 이런 경우, 그것은 **Pydantic 모델과 동일합니다**.

이것은, 특히, 다른 non-SQL 모델을 *상속* 받는 SQL 데이터베이스 모델을 만들 수 있기 때문에 유용합니다. 이를 사용하여 **코드 중복을 많이 줄일 수 있습니다**. 코드 일관성을 높이고, 편집기 지원 등을 개선합니다.

따라서 **FastAPI** 애플리케이션에서의 SQL 데이터베이스 작업을 위한 완벽한 조합입니다. 🚀

튜토리얼 뒷 부분에서 다양한 모델을 결합하는 방법에 관해 더 자세히 배우게 됩니다.

## SQLAlchemy 기반

**SQLModel**은 또한 SQLAlchemy를 기반으로 하며 모든 것에 그것을 사용합니다.

밑단에서, ✨ **SQLModel**의 모델은 또한 **SQLAlchemy**의 모델입니다. ✨

그렇게 되기까지 **많은** 연구와 노력이 있었습니다. 특히, 단일 모델을 **동시에 SQLAlchemy 모델과 Pydantic 모델**에 만들기 위해 많은 노력과 실험이 있었습니다.

이것은 <a href="https://www.jetbrains.com/lp/python-developers-survey-2020/" class="external-link" target="_blank">파이썬에서 가장 널리 사용되는 데이터베이스 라이브러리</a>, SQLAlchemy의 모든 기능, 견고함, 그리고 확실성을 얻을 수 있다는 걸 의미합니다.

**SQLModel**은 <abbr title="자료형 완성, 자료형 확인, 기타 등등을 사용한">개발자 경험을 개선</abbr>하기 위해 자체 유틸리티를 제공하지만, 밑단에서는, SQLAlchemy의 모든 걸 사용합니다.

심지어 SQLModel의 모델을 SQLAlchemy 모델과 **결합**할 수 있습니다.

SQLModel은 **가장 일반적인 사용 사례**를 충족하고 이러한 사례들에 대해 가능한 단순하고 편리하도록 설계되어, 최고의 개발자 경험을 제공합니다.

그러나 더 복잡한 기능을 요구하는 특이한 사용 사례에 대해서는, 여전히 SQLModel에 SQLAlchemy를 직접 연결하고 코드에서 그 모든 기능을 사용할 수 있습니다.

## 테스트 기반

* 100% <abbr title="자동으로 테스트된 코드의 양">테스트 커버리지</abbr> (현재 97%, 앞으로 며칠/몇 주 안에 100%에 도달).
* 100% 코드 기반으로 <abbr title="파이썬 자료형 어노테이션, 이것을 사용하여 편집기와 외부 도두가 더 나은 지원을 제공하 수 있습니다.">어노테이트 된 자료형</abbr>.
