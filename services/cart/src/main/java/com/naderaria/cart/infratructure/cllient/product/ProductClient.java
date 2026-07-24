package com.naderaria.cart.infratructure.cllient.product;

import java.math.BigDecimal;

public interface ProductClient {

    void checkProductQuantity(Long productId, Integer quantity);

    BigDecimal getProductPrice(Long productId);

    void decreaseProductQuantity(Long productId, Integer quantity);

    void increaseProductQuantity(Long productId, Integer quantity);
}
