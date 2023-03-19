# JWT, Spring Security, Spring Data JPA, Spring MVC를 이용한 Fake Shop API만들기


## role테이블에 다음의 정보를 미리 insert해야 합니다. (Spring Boot 애플리케이션이 실행될때 자동으로 추가되도록 수정됨)

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


## sample data (직접 입력하세요.)

insert into category (id, name) values (1, '상의');
insert into category (id, name) values (2, '하의');
insert into category (id, name) values (3, '아우터');
insert into category (id, name) values (4, '신발');
insert into category (id, name) values (5, '가방');
insert into category (id, name) values (6, '악세서리');
insert into category (id, name) values (7, '언더웨어');
insert into category (id, name) values (8, '수영복');
insert into category (id, name) values (9, '잠옷');
insert into category (id, name) values (10, '스포츠웨어');

-- 상의
INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('면 반팔 티셔츠', 10000, '편안한 면 소재의 반팔 티셔츠', 1, 'https://via.placeholder.com/400x600', 4.5, 100);

INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('린넨 셔츠', 20000, '시원한 린넨 소재의 셔츠', 1, 'https://via.placeholder.com/400x600', 4.0, 50);

-- 하의
INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('청바지', 30000, '편안한 착용감의 청바지', 2, 'https://via.placeholder.com/400x600', 4.2, 80);

INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('면 슬랙스', 25000, '면 소재의 슬랙스', 2, 'https://via.placeholder.com/400x600', 4.5, 60);

-- 아우터
INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('트렌치코트', 90000, '고급스러운 트렌치코트', 3, 'https://via.placeholder.com/400x600', 4.7, 30);

INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('가디건', 40000, '편안한 가디건', 3, 'https://via.placeholder.com/400x600', 4.0, 40);

-- 신발
INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('런닝화', 60000, '편안한 착용감의 런닝화', 4, 'https://via.placeholder.com/400x600', 4.3, 120);

INSERT INTO product (title, price, description, category_id, imageUrl, rate, count)
VALUES ('로퍼', 80000, '멋스러운 로퍼', 4, 'https://via.placeholder.com/400x600', 4.6, 70);
