package com.naderaria.product.application.mapper;

import com.naderaria.product.api.dto.request.ReqPriceDto;
import com.naderaria.product.infratructure.domain.model.Price;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceMapper {

    @Mapping(target = "currency.id", source = "currencyId")
    Price toPrice(ReqPriceDto reqPriceDto);
}
