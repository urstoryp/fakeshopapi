package com.example.fakeshopapi.util;

import com.example.fakeshopapi.security.jwt.util.JwtTokenizer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class JwtTokenizerTest {
    @Autowired
    JwtTokenizer jwtTokenizer;

    @Value("${jwt.secretKey}") // application.yml파일의 jwt: secretKey: 값
    String accessSecret; // "12345678901234567890123456789012"
    public final Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 60 * 1000 * 30 // 30분

    @Test
    public void createToken() throws Exception{ // JWT 토큰을 생성.
        String email = "urstory@gmail.com";
        List<String> roles = List.of("ROLE_USER"); // [ "ROLE_USER" ]
        Long id = 1L;
        Claims claims = Jwts.claims().setSubject(email); // JWT 토큰의 payload에 들어갈 내용(claims)을 설정.
        // claims -- sub -- email
        //        -- roles -- [ "ROLE_USER" ]
        //        -- memberId -- 1L
        claims.put("roles", roles);
        claims.put("memberId", id);

        // application.yml파일의 jwt: secretKey: 값
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        // JWT를 생성하는 부분.
        String JwtToken = Jwts.builder() // builder는 JwtBuilder를 반환. Builder패턴.
                .setClaims(claims) // claims가 추가된 JwtBuilder를 리턴.
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.ACCESS_TOKEN_EXPIRE_COUNT)) // 현재시간으로부터 30분뒤에 만료.
                .signWith(Keys.hmacShaKeyFor(accessSecret)) // 결과에 서명까지 포함시킨 JwtBuilder리턴.
                .compact();

        System.out.println(JwtToken);
    }
    // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI1ODUxNiwiZXhwIjoxNjc1MjYwMzE2fQ.GPPOAo0KbH06UCI6F6EUqCPpF7ZiqxzHisXOZBhnDWA

    @Test
    public void parseToken() throws Exception{
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIkFETUlOX1VTRVIiXSwidXNlcklkIjoxLCJpYXQiOjE2NzUyNTg1MTYsImV4cCI6MTY3NTI2MDMxNn0.GPPOAo0KbH06UCI6F6EUqCPpF7ZiqxzHisXOZBhnDWA";

        Claims claims = Jwts.parserBuilder() // JwtParserBuilder를 반환.
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
//        System.out.println(claims.getSubject());
//        System.out.println(claims.get("roles"));
//        System.out.println(claims.get("memberId"));
//        System.out.println(claims.getIssuedAt());
//        System.out.println(claims.getExpiration());
    }

    @Test
    public void createJWT(){
        String jwtToken = jwtTokenizer.createAccessToken(1L, "urstory@gmail.com", "김성박", List.of("ROLE_USER"));
        System.out.println(jwtToken);
    }

    @Test
    public void parseJWT(){
        Claims claims = jwtTokenizer.parseAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0Mzg4NywiZXhwIjoxNjc1MjQ1Njg3fQ.dXO2M9nQM8YvHgjrCfhuaXKcYj3fSaCP5xYQTRH94yQ");
//        System.out.println(claims.getSubject());
//        System.out.println(claims.get("roles"));
//        System.out.println(claims.get("memberId"));
    }
}

// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0MzMyMiwiZXhwIjoxNjc1MjQ1MTIyfQ.navnqz5jWEEeUyUM7fzRN-0TUAikvkifJMxyJUKMohE
// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0MzM2NiwiZXhwIjoxNjc1MjQ1MTY2fQ.YFLaliKjidHvbnMiS9Kq7ecf4CA17mgB48tf1-g5u9g