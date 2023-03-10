package com.example.fakeshopapi.repository;

import com.example.fakeshopapi.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsByCart_memberIdAndCart_idAndProductId(Long memberId, Long cartId, Long productId);
    Optional<CartItem> findByCart_memberIdAndCart_idAndProductId(Long memberId, Long cartId, Long productId);

    boolean existsByCart_memberIdAndId(Long memberId, Long cartItemId);
    void deleteByCart_memberIdAndId(Long memberId, Long cartItemId);

    List<CartItem> findByCart_memberIdAndCart_id(Long memberId, Long cartId);

    List<CartItem> findByCart_memberId(Long memberId);
}
