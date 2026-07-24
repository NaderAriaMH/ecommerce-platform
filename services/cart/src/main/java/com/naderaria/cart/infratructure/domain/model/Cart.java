package com.naderaria.cart.infratructure.domain.model;

import com.naderaria.cart.application.exception.CartEmptyException;
import com.naderaria.cart.application.exception.CartItemNotFoundException;
import com.naderaria.common_data.domin.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tb_cart")
@SequenceGenerator(name = "sequence-generator", sequenceName = "cart_seq", allocationSize = 1)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Cart extends BaseEntity {

    @Column(name = "fk_user")
    private Long userId;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
    private CartStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> cartItems;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

   public void addItem(CartItem cartItem) {
       if(this.cartItems == null) {
           this.cartItems = new ArrayList<>();
       }
       cartItem.setCart(this);
       this.cartItems.add(cartItem);
   }

   public void removeItem(Long id) {
       if(this.cartItems != null) {
           Optional<CartItem> cartItemOptional = cartItems.stream()
                   .filter(cartItem -> cartItem.getId().equals(id)).findFirst();
           if(cartItemOptional.isPresent()) {
               CartItem cartItem = cartItemOptional.get();
               cartItem.setCart(null);
               cartItems.remove(cartItem);
           }
       }
   }

   public CartItem getCartItemById(Long id) {
       if(this.cartItems != null) {
           Optional<CartItem> cartItemOptional = cartItems.stream()
                   .filter(cartItem -> cartItem.getId().equals(id)).findFirst();
           return cartItemOptional.orElseThrow(CartItemNotFoundException::new);
       }
       throw new CartEmptyException();
   }


}
