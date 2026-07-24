package com.naderaria.cart.application.service;

import com.naderaria.cart.infratructure.cllient.product.ProductClient;
import com.naderaria.cart.web.dto.request.ReqCartItemDto;
import com.naderaria.cart.web.dto.response.ResCartItemDto;
import com.naderaria.cart.web.dto.response.ResCartItemPageItemDto;
import com.naderaria.cart.application.mapper.CartMapper;
import com.naderaria.cart.infratructure.domain.model.Cart;
import com.naderaria.cart.infratructure.domain.model.CartItem;
import com.naderaria.cart.infratructure.repository.CartItemRepository;
import com.naderaria.cart.infratructure.repository.CartRepository;
import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final ProductClient productClient;
    private final CartMapper cartMapper;



    @Override
    @Transactional
    public PageResponse<ResCartItemPageItemDto> getCartItems(PaginationDto paginationDto) {
        Pageable pageable = PageConvertor.convertToPageable(paginationDto);
        Page<CartItem> cartItemPage = cartItemRepository.findAllByCartId(pageable, findCurrentCart().getId());
        return cartMapper.toResCartItemPageItemDto(cartItemPage);
    }


    @Override
    @Transactional
    public ResCartItemDto getCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(NullPointerException::new);
        return cartMapper.toResCartItemDto(cartItem);
    }

    @Override
    @Transactional
    public ResCartItemDto addItem(ReqCartItemDto reqCartItemDto) {
        CartItem cartItem = cartMapper.toCartItem(reqCartItemDto);
        productClient.checkProductQuantity(cartItem.getProductId(),cartItem.getQuantity());
        cartItem.setUnitPrice(productClient.getProductPrice(cartItem.getProductId()));
        Cart currentCart = findCurrentCart();
        currentCart.addItem(cartItem);
        if(currentCart.getId() != null){
            currentCart.setUpdatedAt(LocalDateTime.now());
        }
        cartRepository.save(currentCart);
        productClient.decreaseProductQuantity(cartItem.getProductId(), cartItem.getQuantity());
        return cartMapper.toResCartItemDto(cartItem);
    }


    @Override
    @Transactional
    public void deleteItem(Long id) {
        Cart currentCart = findCurrentCart();
        CartItem currentCartItem = currentCart.getCartItemById(id);
        currentCart.removeItem(id);
        cartRepository.save(currentCart);
        productClient.increaseProductQuantity(currentCartItem.getProductId(), currentCartItem.getQuantity());
    }


    private Cart findCurrentCart() {
       return null;//todo find current user id
    }


}
