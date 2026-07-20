package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.product.api.dto.request.ReqCategoryDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryPageItemDto;

public interface CategoryService {

    PageResponse<ResCategoryPageItemDto> getCategories(PaginationDto paginationDto);

    ResCategoryDto getCategory(Long id);

    ResCategoryDto save(ReqCategoryDto reqCategoryDto);

    void update(ReqUpdatableCategoryDto reqUpdatableCategoryDto);

    void delete(long id);

}
