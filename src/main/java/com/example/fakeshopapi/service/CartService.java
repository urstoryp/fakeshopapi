package com.example.fakeshopapi.service;

import com.example.fakeshopapi.domain.Cart;
import com.example.fakeshopapi.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public Cart addCart(Long memberId, String date) {
        Optional<Cart> cart = cartRepository.findByMemberIdAndDate(memberId, date);
        if(cart.isEmpty()) {
            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setDate(date);
            Cart saveCart = cartRepository.save(newCart);
            return saveCart;
        } else {
            return cart.get();
        }
    }
}
