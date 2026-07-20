package com.naderaria.product.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.utils.PageConvertor;
import com.naderaria.product.api.dto.request.ReqCategoryDto;
import com.naderaria.product.api.dto.request.ReqUpdatableCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryDto;
import com.naderaria.product.api.dto.response.ResCategoryPageItemDto;
import com.naderaria.product.application.exception.CategoryNotFoundException;
import com.naderaria.product.infratructure.domain.exception.CategoryInUseException;
import com.naderaria.product.application.mapper.CategoryMapper;
import com.naderaria.product.infratructure.domain.model.Category;
import com.naderaria.product.infratructure.repository.CategoryRepository;
import com.naderaria.product.infratructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public PageResponse<ResCategoryPageItemDto> getCategories(PaginationDto reqBasePaginationDto) {
        Pageable pageable = PageConvertor.convertToPageable(reqBasePaginationDto);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryMapper.toReCategoriesPageItemDto(categoryPage);
    }

    @Override
    @Transactional
    public ResCategoryDto getCategory(Long id) {
        return categoryMapper.toResCategoryDto(
                categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new)
        );
    }

    @Override
    @Transactional
    public ResCategoryDto save(ReqCategoryDto reqCategoryDto) {
        Category category = categoryMapper.toCategory(reqCategoryDto);
        if(reqCategoryDto.parentId() != null) {
            Category parent = categoryRepository.findById(reqCategoryDto.parentId()).orElseThrow(CategoryNotFoundException::new);
            category.changeParent(parent);
        }
        categoryRepository.save(category);
        return categoryMapper.toResCategoryDto(category);
    }

    @Override
    @Transactional
    public void update(ReqUpdatableCategoryDto reqUpdatableCategoryDto) {
        Category oldCategory = categoryRepository.findById(reqUpdatableCategoryDto.id())
                .orElseThrow(CategoryNotFoundException::new);
        categoryMapper.update(reqUpdatableCategoryDto, oldCategory);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(productRepository.existsByCategoryId(id)) throw new CategoryInUseException();
        categoryRepository.deleteById(id);
    }
}
