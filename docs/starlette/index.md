<p align="center">
  <img width="420px" src="/img/starlette.png" alt='starlette'>
</p>
<p align="center">
    <em>✨ The little ASGI framework that shines. ✨</em>
</p>
<p align="center">
<a href="https://github.com/encode/starlette/actions">
    <img src="https://github.com/encode/starlette/workflows/Test%20Suite/badge.svg" alt="Build Status">
</a>
<a href="https://pypi.org/project/starlette/">
    <img src="https://badge.fury.io/py/starlette.svg" alt="Package version">
</a>
</p>

---

# 소개

Starlette은 고성능 비동기 서비스를 구축하는 데 이상적인, 가벼운 [ASGI](https://asgi.readthedocs.io/en/latest/) 프레임워크/툴킷입니다.  

제품-준비 상태이며, 다음과 같은 기능들을 제공합니다:  

* 매우 인상적인 성능.
* 웹소켓 지원.
* 인-프로세스 백그라운드 작업.
* 시스템 시작 및 시스템 종료 이벤트.
* `requests` 위에 빌드된 테스트 클라이언트.
* CORS, GZip, 정적 파일, 스트리밍 응답.
* 세션 및 쿠키 지원.
* 100% 테스트 커버리지.
* 100% 코드 기반 어노테이트된 자료형.
* 거의 없는 강한 종속성.


## 요구사항
파이썬 3.6버전 이상  

## 설치

```shell
$ pip3 install starlette
```

또한 [uvicorn](http://www.uvicorn.org/), [daphne](https://github.com/django/daphne/), 또는 [hypercorn](https://pgjones.gitlab.io/hypercorn/). 같은, ASGI 서버를 설치하고 싶을 수 있습니다.  

```shell
$ pip3 install uvicorn
```

## 예시
**example.py**:

```python
from starlette.applications import Starlette
from starlette.responses import JSONResponse
from starlette.routing import Route


async def homepage(request):
    return JSONResponse({'hello': 'world'})


app = Starlette(debug=True, routes=[
    Route('/', homepage),
])
```

그러면 아래와 같이 애플리케이션을 실행시킬 수 있습니다...  

```shell
$ uvicorn example:app
```

더 다양하고 복잡한 예제에 대해서는, [이곳]((https://github.com/encode/starlette-example))을 확인하시기 바랍니다.  

## 의존성
Starlette은 오로지 `asyncio` 만 필요로 하고, 아래 의존성들은 선택사항입니다:  

* [`requests`][requests] - `TestClient` 사용을 원할 경우 요구됩니다.
* [`jinja2`][jinja2] - `J`inja2Templates` 사용을 원할 경우 요구됩니다.
* [`python-multipart`][python-multipart] - `request.form()`인, 폼 구문 분석 지원을 원할 경우 요구됩니다.
* [`itsdangerous`][itsdangerous] - `SessionMiddleware` 지원을 위해 요구됩니다.
* [`pyyaml`][pyyaml] - `SchemaGenerator` 지원을 위해 요구됩니다.

`pip3 install starlette[full]` 을 사용하여 이 모든 걸 설치할 수 있습니다.  

## 프레임워크 또는 툴킷
Starlette은 완전한 프레임워크, 또는 ASGI 툴킷으로 사용되게 설계되었습니다. 어떤 컴포넌트라도 독립적으로 사용할 수 있습니다.  

```python
from starlette.responses import PlainTextResponse


async def app(scope, receive, send):
    assert scope['type'] == 'http'
    response = PlainTextResponse('Hello, world!')
    await response(scope, receive, send)
```

`example.py` 에 있는 `app` 애플리케이션을 실행해보시기 바랍니다:  

```shell
$ uvicorn example:app
INFO: Started server process [11509]
INFO: Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
```

`--reload` 와 함께 uvicorn을 실행하여 코드가 변경될 때마다 자동으로 리로드되게 하시기 바랍니다.

## 모듈성
Starlette이 설계된 모듈성은 모든 ASGI 프레임워크끼리 공유할 수 있는 재사용 가능한 컴포넌트 구축을 향상시킵니다. 이를 통해 공유된 미들웨어 및 마운트 가능한 애플리케이션 생태계를 가능하게 해야합니다.  

깔금한 API 분리는 각 컴포넌트를 독립적으로 쉽게 이해할 수 있다는 걸 의미합니다.  

## 성능
독립적인 TechEmpower 벤치마크는 Uvicorn을 통해 실행시킨 Starlette 애플리케이션이 가능한 가장 빠른 파이썬 프레임워크라는 사실을 보여줍니다.  

높은 처리량을 위해:  

* `uvicorn` worker 클래스를 사용하여 Gunicorn 실행합니다.
* CPU 코어당 하나 또는 두 개의 워커를 사용합니다. (이것을 실험해봐야 할 수 있습니다.)
* 액세스 로그 비활성화합니다.

예를 들어 아래와 같습니다:  

```shell
gunicorn -w 4 -k uvicorn.workers.UvicornWorker --log-level warning example:app
```

일부 ASGI 서버는 순수한 파이썬 구현도 사용할 수 있기 때문에, 애플리케이션 코드에 CPU 제약이 있을 경우 `PyPy`에서 실행시킬 수도 있습니다.

코드를 통해 구현할 수 있습니다:  


```python
uvicorn.run(..., http='h11', loop='asyncio')
```

또는 Gunicorn을 사용할 수 있습니다:

```shell
gunicorn -k uvicorn.workers.UvicornH11Worker ...
```

<p align="center">&mdash; ⭐️ &mdash;</p>
<p align="center"><i>Staelette은 <a href="https://github.com/encode/starlette/blob/master/LICENSE.md">BSD 라이센스</a> 코드입니다. 영국의 브릿튼에서 설계 & 구축되었습니다. </i></p>

[requests]: http://docs.python-requests.org/en/master/
[jinja2]: http://jinja.pocoo.org/
[python-multipart]: https://andrew-d.github.io/python-multipart/
[itsdangerous]: https://pythonhosted.org/itsdangerous/
[sqlalchemy]: https://www.sqlalchemy.org
[pyyaml]: https://pyyaml.org/wiki/PyYAMLDocumentation