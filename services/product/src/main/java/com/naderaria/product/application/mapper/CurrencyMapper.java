package com.naderaria.product.application.mapper;

import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.api.dto.request.ReqCurrencyDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyPageItemDto;
import com.naderaria.product.infratructure.domain.model.Currency;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CurrencyMapper {

    default PageResponse<ResCurrencyPageItemDto> toResCurrencyPageItemDto(Page<Currency> currencyPage) {
        return PageConvertor.toPageableDto(currencyPage, this::toResCurrencyPageItemDto);
    }

    ResCurrencyPageItemDto toResCurrencyPageItemDto(Currency currency);

    ResCurrencyDto toResCurrencyDto(Currency currency);

    Currency toCurrency(ReqCurrencyDto reqCurrencyDto);

    void update(ReqUpdatableCurrencyDto reqUpdatableCurrencyDto,@MappingTarget Currency oldCurrency);
}
