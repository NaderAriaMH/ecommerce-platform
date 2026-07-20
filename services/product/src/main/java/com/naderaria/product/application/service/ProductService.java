package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.product.api.dto.request.ReqProductDto;
import com.naderaria.product.api.dto.request.ReqUpdatableProductDto;
import com.naderaria.product.api.dto.response.ResProductDto;
import com.naderaria.product.api.dto.response.ResProductPageItemDto;

public interface ProductService {

    PageResponse<ResProductPageItemDto> getProducts(PaginationDto paginationDto);

    ResProductDto getProduct(long id);

    ResProductDto save(ReqProductDto reqProductDto);

    void update(ReqUpdatableProductDto reqUpdatableProductDto);

    void delete(long id);
}
