---
title: "[ 리액트 : React 완벽가이드 ] "
description: "[ 리액트 : React 완벽가이드 ] "
tags:
    - "2021"
    - ReactJS
---

# [ 리액트 : React 완벽가이드 ] 

!!! note "참고"

    [리액트 : React 완벽가이드 Hooks, React Router, Redux 포함 ](https://www.udemy.com/course/best-react/)을 참고로 공부한 내용입니다.


## 도입

### 기존 웹 페이지

기존 웹 페이지는 페이지를 이동할 때마다 해당 페이지 전체를 서버에 요청하여 응답을 받아 다시 새로운 페이지 전체를 보여줘야 했습니다. 이는 곧 하나의 페이지 내에서 단순히 글자 하나가 바뀌어야 하는 상황에서도 전체 페이지 내용을 다시 받는 것을 의미하며 결국 비효율적인 통신으로 인해 속도가 느려 불쾌한 사용자 경험(UX_User Experience)을 제공합니다.

### JavaScript를 사용하는 이유

JavaScript는 이러한 기존 웹 페이지를 보완하기 위해 등장했습니다. JavaScript를 통해 브라우저에서 로직을 실행할 수 있기 때문에 문서 객체 모델이라 불리는 **DOM(Document Object Model)**을 통해 HTML 문서에 접근하여 사용자 요청에 따라 변경해야 할 부분만 변경할 수 있게 됩니다. 

결국 이를 통해서 기존 새로운 HTML 페이지를 가져왔던 방식과는 다르게 효율적인 페이지 랜더링이 가능해졌습니다.

### ReactJS를 사용하는 이유