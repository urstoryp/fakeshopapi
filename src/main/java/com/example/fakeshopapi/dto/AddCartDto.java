package com.example.fakeshopapi.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCartDto {
    private Long memberId;
}
