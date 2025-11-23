## 레이싱 게임 웹 서버 구현하기

### 구현 목표
- 2주차 미션에서 만들었던 자동차 경주를 위한 웹 애플리케이션 만들어보기
- 순수 자바를 활용하여 HTTP 서버 구현하기

### 구현 내용
- java.net의 ServerSocket을 활용하여 서버 구현
- HttpRequestParser를 통해 서버로 들어온 Http 요청을 파싱
- HashMap을 활용하여 요청을 적절한 Controller에 위임
- HttpResponseBuilder를 통해 Controller에서 생성된 HttpResponse를 반환할 수 있는 형태로 변형


### 파일 구조
```
📦 java-web-application
├── 📂 .idea
├── 📂 src
│   ├── 📂 controller
│   ├── 📂 domain
│   ├── 📂 exception
│   ├── 📂 http
│   ├── 📂 resources
│   └── 📄 WebServerLauncher.java
├── 📄 .gitignore
├── 📄 README.md
└── 📄 java-web-application.iml
```

### 시작 가이드

1. 프로젝트 클론
```
git clone https://github.com/YUNJAEGOONER/java-web-application.git
cd java-web-application
```

2. 프로젝트 빌드 및 실행
```
mkdir -p out
javac -d out src/**/*.java
jar --create --file out/webserver.jar --main-class=WebServerLauncher -C out .
java -jar out/webserver.jar
```