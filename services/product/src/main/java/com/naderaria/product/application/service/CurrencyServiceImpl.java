package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.api.dto.request.ReqCurrencyDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyDto;
import com.naderaria.product.api.dto.response.ResCurrencyPageItemDto;
import com.naderaria.product.application.exception.CurrencyNotFoundException;
import com.naderaria.product.infratructure.domain.exception.CurrencyInUseException;
import com.naderaria.product.application.mapper.CurrencyMapper;
import com.naderaria.product.infratructure.domain.model.Currency;
import com.naderaria.product.infratructure.repository.CurrencyRepository;
import com.naderaria.product.infratructure.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final PriceRepository priceRepository;

    @Override
    @Transactional
    public PageResponse<ResCurrencyPageItemDto> getCurrencies(PaginationDto paginationDto) {
        Pageable pageable = PageConvertor.convertToPageable(paginationDto);
        Page<Currency> currencyPage = currencyRepository.findAll(pageable);
        return currencyMapper.toResCurrencyPageItemDto(currencyPage);
    }

    @Override
    @Transactional
    public ResCurrencyDto getCurrency(Long id) {
        return currencyMapper.toResCurrencyDto(
                currencyRepository.findById(id).orElseThrow(CurrencyNotFoundException::new)
        );
    }

    @Override
    @Transactional
    public ResCurrencyDto save(ReqCurrencyDto reqCurrencyDto) {
        Currency currency = currencyMapper.toCurrency(reqCurrencyDto);
        currencyRepository.save(currency);
        return currencyMapper.toResCurrencyDto(currency);
    }

    @Override
    @Transactional
    public void update(ReqUpdatableCurrencyDto reqUpdatableCurrencyDto) {
        Currency oldCurrency = currencyRepository.findById(reqUpdatableCurrencyDto.id())
                .orElseThrow(CurrencyNotFoundException::new);
        currencyMapper.update(reqUpdatableCurrencyDto, oldCurrency);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (priceRepository.existsByCurrencyId(id)) { throw new CurrencyInUseException(); }
        currencyRepository.deleteById(id);
    }
}
