---
title: "설치"
---

# 설치

설치는 다음과 같이 간단합니다:

```bash
pip install pydantic
```

*pydantic*은 파이썬 3.6, 3.7, 3.8, 또는 3.9, [`typing-extensions`](https://pypi.org/project/typing-extensions/)), 그리고 파이썬 3.6을 위한 백포트 패키지인 [`dataclass`](https://pypi.org/project/dataclasses/)를 제외하고는 필수적으로 요구되는 의존성이 없습니다. 파이썬 3.6 이상 버전을 가지고 있고 `pip`가 설치되어 있다면, 바로 설치해도 좋습니다.

Pydantic은 또한 [conda-forge](https://conda-forge.org) 채널 아래의 [conda](https://www.anaconda.com)에서도 사용 가능합니다.

```bash
conda install pydantic -c conda-forge
```

## Cypthon을 통한 컴파일

*pydantic*은 선택적으로 30-50% 정도의 성능이 향상되는 cypthon을 통해 컴파일될 수 있습니다.

바이너리는 Linux, MacOS 그리고 64비트 Windows용 [PyPl](https://pypi.org/project/pydantic/#files)에서 사용할 수 있습니다. 만약 수동으로 설치한다면, pydantic을 설치하기 전에 `cypthon`을 설치해야 컴파일이 자동으로 수행됩니다.

*pydantic*이 컴파일되었는지 확인하기 위해서는 아래와 같이 실행하기 바랍니다:

```py
import pydantic
print('compiled:', pydantic.compiled)
```

*pydantic*은 세 가지 선택적 의존성을 가집니다:

* 만약 이메일 유효성 검사가 필요하다면 [email-validator](https://github.com/JoshData/python-email-validator)를 사용하기 바랍니다
* `Settings` 을 활용한 [dotenv 파일 지원](usage/settings.md#dotenv-env-support)은 [python-dotenv](https://pypi.org/project/python-dotenv)를 필요로 합니다.

이것들을 *pydantic*과 함께 설치할 수 있습니다:
```bash
pip install pydantic[email]
# or
pip install pydantic[dotenv]
# or just
pip install pydantic[email,dotenv]
```

물론, `pip install email-validator` 그리고/또는 `pip install`을 사용하여 수동으로 이 요구사항을 설치할 수 있습니다.

그리고 만약 저장소로부터 *pydantic*을 직접 설치하길 원한다면 아래와 같이 할 수 있습니다:
```bash
pip install git+git://github.com/samuelcolvin/pydantic@master#egg=pydantic
# or with extras
pip install git+git://github.com/samuelcolvin/pydantic@master#egg=pydantic[email,dotenv]
```
