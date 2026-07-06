package com.naderaria.common_core.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PaginationDto {

    private int pageNumber;

    private int pageSize;

    private boolean sortAscending;

    private String[] sortParams;

    public String[] getSortParams() {
        if(sortParams == null || sortParams.length == 0)
            sortParams = new String[] {"id"};
        return sortParams;
    }

}