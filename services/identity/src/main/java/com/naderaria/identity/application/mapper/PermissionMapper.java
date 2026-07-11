package com.naderaria.identity.application.mapper;

import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.mapper.PageMapper;
import com.naderaria.identity.api.dto.permission.request.ReqPermissionDto;
import com.naderaria.identity.api.dto.permission.request.ReqUpdatablePermissionDto;
import com.naderaria.identity.api.dto.permission.response.ResPermissionPageItemDto;
import com.naderaria.identity.api.dto.permission.response.ResUpdatablePermissionDto;
import com.naderaria.identity.infratructure.domin.Permission;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PermissionMapper {

    default PageResponse<ResPermissionPageItemDto> toResPermissionPageItemDto(Page<Permission> permissions){
        return PageMapper.toPageableDto(permissions, this::toResPermissionPageItemDto);
    }

    ResPermissionPageItemDto toResPermissionPageItemDto(Permission permission);

    ResUpdatablePermissionDto toResUpdatablePermissionDto(Permission permission);

    Permission toPermission(ReqPermissionDto reqPermissionDto);

    Permission toPermission(ReqUpdatablePermissionDto reqUpdatablePermissionDto);
}
