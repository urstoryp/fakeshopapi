# JWT, Spring Security, Spring Data JPA, Spring MVC를 이용한 Fake Shop API만들기


## role테이블에 다음의 정보를 미리 insert해야 합니다.

```
insert into role(name) values('ROLE_USER');
insert into role(name) values('ROLE_ADMIN');
```

## 회원 가입

```
curl --location --request POST 'localhost:8080/members/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"이름",
"email":"이메일",
"password":"암호"
}'
```

---

## 로그인

```
curl --location --request POST 'localhost:8080/members/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"email",
    "password":"암호"
}'
```

응답 형식

```
{
    "accessToken": "JWT토큰",
    "refreshToken": "JWT토큰",
    "memberId": memberId,
    "nickname": "이름"
}
```

---

## 로그아웃

```
curl --location --request DELETE 'http://localhost:8080/members/logout' \
--header 'Authorization: Bearer accessToken' \
--header 'Content-Type: application/json' \
--data '{
    "refreshToken" : "리프래시토큰"
}'
```
---

## 리프래시 토큰

```
curl --location 'http://localhost:8080/members/refreshToken' \
--header 'Content-Type: application/json' \
--data '{
"refreshToken" : "리프래시토큰"
}'
```

응답값

```
{
    "accessToken": "JWT토큰",
    "refreshToken": "JWT토큰",
    "memberId": memberId,
    "nickname": "이름"
}
```