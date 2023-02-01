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

    @Value("${jwt.secretKey}") String accessSecret;
    public final Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L;

    @Test
    public void createToken() throws Exception{
        String email = "urstory@gmail.com";
        List<String> roles = List.of("ROLE_USER");
        Long id = 1L;
        Claims claims = Jwts.claims().setSubject(email);

        claims.put("roles", roles);
        claims.put("userId", id);

        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        String JwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.ACCESS_TOKEN_EXPIRE_COUNT))
                .signWith(Keys.hmacShaKeyFor(accessSecret))
                .compact();

        System.out.println(JwtToken);
    }

    @Test
    public void parseToken() throws Exception{
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0NDE4NywiZXhwIjoxNjc1MjQ1OTg3fQ.aXyyJNbTHfjAeSow8Go2Li_C_1c6fl7zjwrSSLQQnVA";

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        System.out.println(claims.getSubject());
        System.out.println(claims.get("roles"));
        System.out.println(claims.get("userId"));
    }

    @Test
    public void createJWT(){
        String jwtToken = jwtTokenizer.createAccessToken(1L, "urstory@gmail.com", List.of("ROLE_USER"));
        System.out.println(jwtToken);
    }

    @Test
    public void parseJWT(){
        Claims claims = jwtTokenizer.parseAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0Mzg4NywiZXhwIjoxNjc1MjQ1Njg3fQ.dXO2M9nQM8YvHgjrCfhuaXKcYj3fSaCP5xYQTRH94yQ");
        System.out.println(claims.getSubject());
        System.out.println(claims.get("roles"));
        System.out.println(claims.get("userId"));
    }
}

// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0MzMyMiwiZXhwIjoxNjc1MjQ1MTIyfQ.navnqz5jWEEeUyUM7fzRN-0TUAikvkifJMxyJUKMohE
// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cnN0b3J5QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTY3NTI0MzM2NiwiZXhwIjoxNjc1MjQ1MTY2fQ.YFLaliKjidHvbnMiS9Kq7ecf4CA17mgB48tf1-g5u9g