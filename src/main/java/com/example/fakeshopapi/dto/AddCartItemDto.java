package com.example.fakeshopapi.dto;

import com.example.fakeshopapi.domain.Cart;
import lombok.Data;

@Data
public class AddCartItemDto {
    private Long cartId;
    private Long productId;
    private String productTitle;
    private Double productPrice;
    private String productDescription;
    private int quantity;
}
