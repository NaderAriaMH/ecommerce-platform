package com.naderaria.cart.application.mapper;

import com.naderaria.cart.web.dto.request.ReqCartItemDto;
import com.naderaria.cart.web.dto.response.ResCartItemDto;
import com.naderaria.cart.web.dto.response.ResCartItemPageItemDto;
import com.naderaria.cart.infratructure.domain.model.CartItem;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    default PageResponse<ResCartItemPageItemDto> toResCartItemPageItemDto(Page<CartItem> cartItems) {
        return PageConvertor.toPageableDto(cartItems,this::toResCartItemPageItemDto);
    }

    ResCartItemPageItemDto toResCartItemPageItemDto(CartItem cartItem);


    ResCartItemDto toResCartItemDto(CartItem cartItem);

    CartItem toCartItem(ReqCartItemDto reqCartItemDto);

   // IntCartDto toIntCartDto(Cart cart);
}
