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
### News API
- News 생성
  - > `/api/news` (POST)
    ``` json
    {
        "title" : "NEWS TITLE", // 필수
        "content" : "NEWS CONTENT", // 필수
        "categoryId" : 1, // 필수
        "topicIds" : [1, 2],
        "tagIds" : [1],
        "link" : "https://snack-link.com"
    }
    ```
- 단일 News 조회
  - > `/api/news/{id}` (GET)
- 조건에 맞는 News 리스트 조회
  - > `/api/news` (POST)
    ``` json
    {
        "startDateTime" : "2019-07-01T00:00",
        "endDateTime" : "2019-08-31T00:00",
        "categoryId" : 1,
        "topicIds" : [1, 2],
        "tagIds" : [1]
    }
    ```
### Category API
- 전체 Category 리스트 조회
  - > `/api/category` (GET)

### Topic API
- Topic 생성
  - > `/api/topic` (POST)
    ``` json
    {
        "name" : "쿠팡", // 필수
        "type" : "CORP" // 입력 권장 (기본값 NONE)
    }
    ```
- Topic 리스트 조회
  - > `/api/topic/{type}` (GET)
    > `/api/topic/{type}&sort={ordering}` (GET)
  - {type}: `corp`, `person`, `field`...
  - {ordering}: `name`(기본값), `id`.
- Topic 수정
  - > `/api/topic` (PUT)
    ``` json
        {
            "id" : 4, // 필수
            "name" : "딥러닝", // 필수
            "type" : "FIELD" // 필수
        }
    ```

### Tag API
- Tag 생성
  - > `/api/tag` (POST)
    ``` json
        {
            "title" : "WEEKLY" // 필수
        }
    ```
- Tag 리스트 조회
  - > `/api/tag` (GET)
- Tag 수정
  - > `/api/tag` (PUT)
    ``` json
        {
            "id" : 3, // 필수
            "title" : "RECOMMEND" // 필수
        }
    ```
