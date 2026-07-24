package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.web.controller.dto.intrernal.ProductPriceDto;
import com.naderaria.product.web.controller.dto.request.ReqProductDto;
import com.naderaria.product.web.controller.dto.request.ReqUpdatableProductDto;
import com.naderaria.product.web.controller.dto.response.ResProductDto;
import com.naderaria.product.web.controller.dto.response.ResProductPageItemDto;
import com.naderaria.product.application.exception.ProductNotFoundException;
import com.naderaria.product.application.mapper.ProductMapper;
import com.naderaria.product.infratructure.domain.model.Product;
import com.naderaria.product.infratructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public PageResponse<ResProductPageItemDto> getProducts(PaginationDto paginationDto) {
        Pageable pageable = PageConvertor.convertToPageable(paginationDto);
        Page<ResProductPageItemDto> resProductPageItemDtoList = productRepository.findAllProducts(pageable);
        return productMapper.toResProductPageItemDto(resProductPageItemDtoList);
    }

    @Override
    @Transactional
    public ResProductDto getProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.toResProductDto(product);
    }

    @Override
    @Transactional
    public ResProductDto save(ReqProductDto reqProductDto) {
        Product product = productMapper.toProduct(reqProductDto);
        productRepository.save(product);
        return productMapper.toResProductDto(product);
    }

    @Override
    @Transactional
    public void update(ReqUpdatableProductDto reqUpdatableProductDto) {
        Product oldProduct = productRepository.findById(reqUpdatableProductDto.id()).orElseThrow(ProductNotFoundException::new);
        productMapper.update(reqUpdatableProductDto, oldProduct);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.unavailableProduct();//todo test this line
    }

    @Override
    @Transactional
    public ProductPriceDto getFinalPrice(Long id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return new ProductPriceDto(product.getId(),product.getFinalPrice());
    }

}
