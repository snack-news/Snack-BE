## Snack News - BE

[![Build Status](https://travis-ci.org/snack-news/Snack-BE.svg?branch=master)](https://travis-ci.org/snack-news/Snack-BE) [![codecov](https://codecov.io/gh/snack-news/Snack-BE/branch/master/graph/badge.svg)](https://codecov.io/gh/snack-news/Snack-BE)

## WIKI
- <https://github.com/snack-news/Snack-BE/wiki>

## How to Run? 🚀
```shell
SPRING_PROFILES_ACTIVE=dev ./gradlew clean bootRun  # DEV
SPRING_PROFILES_ACTIVE=release ./gradlew clean bootRun # RELEASE
```

## API spec
### Topic API
#### 생성
URL|Method|
---|---
/api/topic|POST
- **Request body**
  - **`name`** -  토픽 이름 (필수)
  - `type` - 토픽 타입
    - 예) CORP, PERSON, FIELD, NONE 등
    - 디폴트는 NONE

#### 조회
URL|Method
---|---
/api/topic/{type}|GET
- **URL endpoint**
  - `type`: 토픽의 타입(대분류)
    - 예) CORP, PERSON, FIELD, NONE 등
    - 디폴트는 NONE
- **Query prarmeter**
  - `sort`: 토픽 정렬 방법
    - 예) NAME, ID
    - 디폴트는 NAME

#### 수정
URL|Method
---|---
/api/topic|PUT
- **Request body**
  - **`id`** - 토픽 ID (필수)
  - **`name`** -  토픽 이름 (필수)
  - `type` - 토픽 타입
    - 예) CORP, PERSON, FIELD, NONE 등
    - 디폴트는 NONE
