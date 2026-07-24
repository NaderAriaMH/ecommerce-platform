package com.naderaria.cart.infratructure.repository;

import com.naderaria.cart.infratructure.domain.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Page<CartItem> findAllByCartId(Pageable pageable, @Param("cartId") Long cartId);
}
