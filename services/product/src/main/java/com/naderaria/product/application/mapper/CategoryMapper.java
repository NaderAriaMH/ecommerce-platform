package com.naderaria.product.application.mapper;

import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.api.dto.request.ReqCategoryDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryPageItemDto;
import com.naderaria.product.infratructure.domain.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {

    default PageResponse<ResCategoryPageItemDto> toReCategoriesPageItemDto(Page<Category> categoryPage) {
        return PageConvertor.toPageableDto(categoryPage, this::toResCategoryPageItemDto);
    }

    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "parentName", source = "parent.name")
    ResCategoryPageItemDto toResCategoryPageItemDto(Category category);

    ResCategoryDto toResCategoryDto(Category category);

    @Mapping(target = "parent", ignore = true)
    Category toCategory(ReqCategoryDto reqCategoryDto);

    Category toCategory(ReqUpdatableCategoryDto reqUpdatableCategoryDto);

    @Mapping(target = "parent", ignore = true)
    void update(ReqUpdatableCategoryDto reqUpdatableCategoryDto, @MappingTarget Category category);

}
