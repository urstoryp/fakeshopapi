package com.example.fakeshopapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC 에 대한 설정파일. 웹에대한 설정파일
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS는 프론트엔드, 백엔드개발자라면 반드시 이해!
    // CORS에 대한 설정. CORS는 Cross Origin Resource Sharing의 약자.
    // 프론트 엔드, 백 엔드 개발
    // 프론트 엔드는 3000번 포트 (React.js), 백 엔드는 8080번 포트
    // http://localhost:3000 ---> 8080 api를 호출할 수 있도록 설정.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
//                .allowedOrigins("https://www.fakeshop.com")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS", "DELETE");
//                .allowCredentials(true);
    }
}
