package com.naderaria.product.application.mapper;

import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.api.dto.request.ReqProductDto;
import com.naderaria.product.api.dto.request.ReqUpdatableProductDto;
import com.naderaria.product.api.dto.response.ResInventoryDto;
import com.naderaria.product.api.dto.response.ResPriceDto;
import com.naderaria.product.api.dto.response.ResProductDto;
import com.naderaria.product.api.dto.response.ResProductPageItemDto;
import com.naderaria.product.infratructure.domain.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = PriceMapper.class)
public interface ProductMapper {

    default PageResponse<ResProductPageItemDto> toResProductPageItemDto(Page<ResProductPageItemDto> productPage) {
        return PageConvertor.pageDtoListToPageableDto(productPage);
    }
    /*default PageResponse<ResProductPageItemDto> toResProductPageItemDto(Page<Product> productPage) {
        return PageConvertor.toPageableDto(productPage, this::toResProductPageItemDto);
    }*/

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "statusType", source = "statusType")
  /*  @Mapping(target = "stockQuantity", source = "")//todo how fill it???
    @Mapping(target = "price", source = "")
//todo how fill it???*/
    ResProductPageItemDto toResProductPageItemDto(Product product);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryTitle", source = "category.id")
    ResProductDto toResProductDto(Product product);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "statusType", source = "statusType")
    Product toProduct(ReqProductDto reqProductDto);

    void update(ReqUpdatableProductDto reqUpdatableProductDto, @MappingTarget Product product);
}

