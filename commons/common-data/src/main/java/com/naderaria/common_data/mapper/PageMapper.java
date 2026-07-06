package com.naderaria.common_data.mapper;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.PageItem;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.domin.BaseEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PageMapper {

    default Pageable convertToPageable(PaginationDto paginationDto) {

        Sort sort = Sort.unsorted();
        Sort.Direction direction = (paginationDto.isSortAscending() ? Sort.Direction.ASC : Sort.Direction.DESC);

        if (paginationDto.getSortParams() != null && paginationDto.getSortParams().length != 0) {
            sort = Sort.by(direction, paginationDto.getSortParams());
        }

        return PageRequest.of((paginationDto.getPageNumber() <= 0 ? 0 : paginationDto.getPageNumber() - 1),
                paginationDto.getPageSize(), sort);
    }


    static <R extends PageItem,E extends BaseEntity> PageResponse<R>
            toPageableDto(Page<E> pageEntities, Function<E,R> convertor){

        List<R> pageItems =  pageEntities.getContent().stream().map(convertor).toList();
        return new PageResponse<>(
                pageItems,
                pageEntities.getPageable().getPageNumber(),
                pageEntities.getTotalPages(),
                pageEntities.getTotalElements()
        );
    }
}
