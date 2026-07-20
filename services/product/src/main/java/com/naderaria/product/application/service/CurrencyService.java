package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.product.api.dto.request.ReqCurrencyDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyPageItemDto;

public interface CurrencyService {

    PageResponse<ResCurrencyPageItemDto> getCurrencies(PaginationDto paginationDto);

    ResCurrencyDto getCurrency(Long id);

    ResCurrencyDto save(ReqCurrencyDto reqCurrencyDto);

    void update(ReqUpdatableCurrencyDto reqUpdatableCurrencyDto);

    void delete(Long id);

}
