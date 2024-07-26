## Team: 난대학시절현대차를전공했단사실👨‍🎓

## Project Name: 캐스퍼가캐리해(Caecae)

## 팀원 소개

| 서창교                      | 조민환                      | 오익준                      | 박진우                      |
| --------------------------- | --------------------------- | --------------------------- | --------------------------- |
| FE                          | FE                          | BE                          | BE                          |
| [@dunkkk](https://github.com/dunkkk)       | [@minani-0621](https://github.com/minani-0621)       | [@putdata](https://github.com/putdata)                    | [@j2noo](https://github.com/j2noo)                |

## **💻Tech - Backend 개발 목표**

### 1. Redis 도입을 통한 대용량 트래픽 및 동시성 처리

- 메모리에서 동작하기 때문에 빠른 처리 속도 기대
- 싱글 스레드 동작 방식으로 인해 동시성 제어 가능

### 2. 부하테스트를 통한 병목 지점 파악 및 성능개선

- 서버의 부하 테스트를 할 수 있는 툴을 사용하여 대용량 트래픽 유발
- 모니터링 툴을 사용하여 자원 사용량 파악 및 성능 개선

### 3. 적극적인 테스트 코드 작성

- 주요 메서드들에 대한 단위테스트 및 통합테스트 작성
- 목표 테스트 커버리지 50%

---

## 📣 이슈 관리 로직

<img width="720" alt="image" src="https://github.com/user-attachments/assets/73bf4f5c-90d0-4752-8a7f-62972adb3fa0">


- EPIC : 최상위 작업
- STORY : 사용자의 사용 흐름
- TASK : 개발자 입장에서의 기능 분류
- SUB-TASK : 세부적인 작업 단위

---

## **📘Backend 기술 스택**

### Server

- Spring boot 3.3.2
- Spring Data Jpa
- Java 17
- MySQL 8.0
- Redis
- AssertJ

### Infra

- AWS EC2
- AWS S3
- Github-actions
- docker

### Monitoring Server

- exporter
- grafana
- prometheus

---

## 🖥️Server Architecture

<img width="817" alt="image" src="https://github.com/user-attachments/assets/2c3d4f76-c298-4163-91b0-7a9e83628ceb">


---

## 📄ERD

<img width="720" alt="image" src="https://github.com/user-attachments/assets/5286d18f-7deb-44e8-9083-7a69ea2d70e1">

---

## **🔖Naming Rules**

- **Packages**
    - 항상 소문자로 생성하기
- **Classes**
    - 명사여야 한다.
    - 복합 단어의 경우 각 단어의 첫글자는 대문자.
    - 완전한 단어를 사용하고, 두 문자어와 약어는 피한다.
- **Interfaces**
    - 인터페이스 이름도 클래스 이름과 같은 대문자 규칙을 적용한다.
- **Methods**
    - 동사여야 한다.
    - 복합 단어의 경우 첫 단어는 소문자로 시작한다.
- **Constants**
    - 클래스 상수로 선언된 변수들과 상수들의 이름은 모두 대문자로 쓰고 각 단어는 언더바 ("_")로 분리한다. -**Variables**
    - 변수 이름의 첫번째 문자는 소문자여야 한다.
    - 언더바 또는 달러 표시 문자로 시작하는 것이 허용 되기는 하지만, 사용하지 말자.
    - 짧지만 의미있게 짓는다.
    - 변수의 사용 의도를 알 수 있도록 의미적으로 짓는다.
    - 한문자로만 이루어진 변수는 암시적으로만 사용하고 버릴 변수를 제외하고는 피한다.
    - 임시 변수의 이름은 integer는 i,j,k,m,n 을 사용하고 character는 c,d,e를 사용한다.
- **ETC**
    - DB 테이블: **lower_snake_case**
    - ENUM, 상수: **Upper_snake_case**
    - 컬렉션(Collection): **복수형**을 사용하거나 **컬렉션을 명시한다**. (Ex. userList, users, userMap)
    - LocalDateTime: 접미사에 **Date**를 붙인다.

---

## **🗂️ Commit Convention**

`git commit -m "feat : 전체 등수 조회 api 추가 (CC-83)"`

- `feat` : 새로운 기능 추가
- `fix` : 버그 수정
- `chore` : 빌드 업무, 패키지 매니저, 라이브러리, dependencies 설정
- `docs` : 문서 수정 - *README.md, .github, ..etc*
- `design` : 사용자 UI 디자인 변경 - *CSS*
- `style` : 기능 수정 없는 코드 스타일 변경
- `refactor` : 코드 리팩터링
- `test` : 테스트 코드, 리펙토링 테스트 코드 추가
- `ci` : ci 설정 파일 수정
- `perf` : 성능 개선
- `rename` : 파일 혹은 폴더명 변경

---

## **🐬 Git Flow**

브랜치 네이밍 : `CC-83`

- `main` : 출시 가능한 프로덕션 코드의 브랜치
- `develop` : 개발 내용 통합 브랜치
- `feature` : 기능을 개발하는 브랜치
- `hotfix` : 출시 버전에서 발생한 버그를 수정하는 브랜치
    
    

---

## 📂 폴더 구조

```
ai.softeer.caecae
├── domain(racingGame..)
│   ├── api(controller)
│   ├── service
│   ├── domain
│   │   ├── dto
│   │   │    ├── request
│   │   │    └── response
│   │   ├── entity
│   │   └── mapper
│   └── repository
└── global
     ├── utils
     ├── config
     ├── error
     └── ...
```
