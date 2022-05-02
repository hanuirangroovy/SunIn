# Gitlab 소스 클론 이후 빌드 및 배포할 수 있도록 정리한 문서

## 1. 사용한 JVM, 웹서버, WAS 제품 등의 종류와 설정값, 버전(IDE 버전 포함) 기재

- Server : AWS EC2

  - AWS S3
  - Ubuntu
  - Docker
  - Jenkins
  - Nginx

- Backend

  - Java : Java 11.0.13
  - Framework : SpringBoot 2.6.2
  - ORM : JPA(Hibernate), QueryDSL
  - IDE : Intellij 2021.3.1
  - Dependency tool : gradle-7.3.2
  - Database : MySQL-8.0.27, MongoDB-5.0.5

- Frontend

  - HTML5, CSS3, Javascript(Es6)
  - React : 17.0.2
  - Next: 12.0.10
  - IDE : Visual Studio Code 1.62.3

  

## 2. 빌드 시 사용되는 환경 변수 등의 주요 내용 상세 기재

- 환경 변수 : JAVA_HOME

- 변수 값 : C:\Program Files\Java\jdk-11.0.13\bin

  

- Backend

  ```
     - src
        - main
           -resource
           	- application.yml
             	- application-aws.yml
              - application-credentials.yml
              - application-oauth.yml
  ```



- Frontend

  ```
    - next-env.d.ts   
    - next.config.js   
    - package.json   
    - tsconfig.json   
  ```

- next.config.js(Nextjs 설정)

- next-env.d.ts(Typescript 설정)

- package.json (Frontend 프로젝트 설정)

- tsconfig.json (Typescript 컴파일러 옵션 설정)

- application.yml (Backend 프로젝트 설정, DB 설정)

- application-aws.yml (AWS S3 설정)

- application-credentials.yml (AWS S3 `secret-key` 설정)

- application-oauth.yml (소셜로그인 Oauth2 설정)



## 3. 배포 시 특이사항 기재

- 서버 : AWS EC2 ubuntu 사용



### Docker 설정

- Hostname : [16c210.p.ssafy.io](http://16c210.p.ssafy.io)
- Port : 27017
- Username : Sunin



- 서로 다른 도커 이미지로 저장되어 있어 각각의 이미지를 실행

- 필요한 이미지들의 설정은 docker-compose.yml 파일에 작성

- docker 내부는 같은 네트워크로 묶어주기 위해 docker-compose로 실행



### How to

- Backend
  - 백엔드 root directory에서 빌드
  - ./gradlew build
- Frontend
  - 터미널 창 open
  - ```$npm install```
  - ```$npm run dev```



### DevOps

- Jenkins로 CI/CD를 구축하여 develop 브랜치에 merge 이벤트 발생 시 자동으로 빌드되도록 구성



## 4. DB 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록

### **DB 접속 계정 정보**

- ID : sunin
- PW : `BUKQ@3rbfM=+NY7sGmP^`

### **Jenkins 접속 계정 정보**

- ID : sunin
- PW : sunin
- Jenkins URL
  - http://i6c210.p.ssafy.io:9090/