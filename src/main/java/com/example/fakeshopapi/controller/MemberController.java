package com.example.fakeshopapi.controller;

import com.example.fakeshopapi.dto.MemberLoginDto;
import com.example.fakeshopapi.dto.MemberLoginResponseDto;
import com.example.fakeshopapi.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final JwtTokenizer jwtTokenizer;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto) {

        // TODO email에 해당하는 사용자 정보를 읽어와서 암호가 맞는지 검사하는 코드가 있어야 한다.
        Long memberId = 1L;
        String email = loginDto.getEmail();
        List<String> roles = List.of("ROLE_USER");

        // JWT토큰을 생성하였다. jwt라이브러리를 이용하여 생성.
        String accessToken = jwtTokenizer.createAccessToken(memberId, email, roles);
        String refreshToken = jwtTokenizer.createRefreshToken(memberId, email, roles);

        MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(memberId)
                .nickname("nickname")
                .build();
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        // token repository에서 refresh Token에 해당하는 값을 삭제한다.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
