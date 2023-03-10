package com.example.fakeshopapi.controller;

import com.example.fakeshopapi.domain.Cart;
import com.example.fakeshopapi.domain.CartItem;
import com.example.fakeshopapi.domain.Member;
import com.example.fakeshopapi.dto.AddCartDto;
import com.example.fakeshopapi.security.jwt.util.IfLogin;
import com.example.fakeshopapi.security.jwt.util.LoginUserDto;
import com.example.fakeshopapi.service.CartItemService;
import com.example.fakeshopapi.service.CartService;
import com.example.fakeshopapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts") // http://localhost:8080/carts
@RequiredArgsConstructor
public class CartApiController {
    private final CartService cartService;
    @PostMapping
    public Cart addCart(@IfLogin LoginUserDto loginUserDto) {
        LocalDate localDate = LocalDate.now();
        localDate.getYear();
        localDate.getDayOfMonth();
        localDate.getMonthValue();
        String date = String.valueOf(localDate.getYear()) + (localDate.getMonthValue() < 10 ? "0" :"") + String.valueOf(localDate.getMonthValue()) + (localDate.getDayOfMonth() < 10 ? "0" :"") +String.valueOf(localDate.getDayOfMonth());
        Cart cart = cartService.addCart(loginUserDto.getMemberId(), date);
        return cart;
    }


}
