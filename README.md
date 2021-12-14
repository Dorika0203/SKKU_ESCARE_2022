인터페이스 서버
==================================


## Package Management tool: Gradle

https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application

7번의 Running your Application with Gradle 파트 참고

Jar가 아닌 War로 배포해야 함. (JSP 및 WEB_INF 등 소스 디렉토리를 Jar가 인식하지 않음.)

## Docker compose로 실행하는 방법

### docker 설치 후 mariaDB docker image 설치
docker 설치 후 command 실행
```
> docker pull mariadb
```



### docker-compose 실행
> #### 1. Dockerfile
>> Docker용 본 프로젝트 이미지 생성 syntax임.
> #### 2. docker-compose.yml
>> 설치된 mariaDB docker 이미지와 본 프로젝트 이미지를 docker-compose로 실행하는 syntax임
> 
>> 프로젝트의 Dockerfile을 참고해서 프로젝트 이미지를 먼저 생성한 후, mariaDB 이미지와 프로젝트 이미지를 docker-compose로 같이 올리게 됨.

### 실행 방법
- root directory에서 다음 커맨드 실행

```> ./gradlew bootWar```

```> docker-compose up```
- DB 초기화 관련

> docker-compose.yml에서 init.sql을 mariaDB container의 특정 디렉토리로 이동시킴. 관련 내용은 https://hub.docker.com/_/mariadb 의 initialize 관련 참고.
> 
> Spring batch의 schema 생성이 현재 입력되어있음. 추가적인 초기화가 필요할 시 입력
