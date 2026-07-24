package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.product.web.controller.dto.intrernal.ProductPriceDto;
import com.naderaria.product.web.controller.dto.request.ReqProductDto;
import com.naderaria.product.web.controller.dto.request.ReqUpdatableProductDto;
import com.naderaria.product.web.controller.dto.response.ResProductDto;
import com.naderaria.product.web.controller.dto.response.ResProductPageItemDto;

import java.math.BigDecimal;

public interface ProductService {

    PageResponse<ResProductPageItemDto> getProducts(PaginationDto paginationDto);

    ResProductDto getProduct(long id);

    ResProductDto save(ReqProductDto reqProductDto);

    void update(ReqUpdatableProductDto reqUpdatableProductDto);

    void delete(long id);

    ProductPriceDto getFinalPrice(Long id);
}
