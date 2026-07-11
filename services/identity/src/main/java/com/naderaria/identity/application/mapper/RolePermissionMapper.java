package com.naderaria.identity.application.mapper;

import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.mapper.PageMapper;
import com.naderaria.identity.api.dto.role_permission.request.ReqRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.request.ReqUpdatableRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.response.ResRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.response.ResRolePermissionPageItemDto;
import com.naderaria.identity.infratructure.domin.RolePermission;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {RoleMapper.class, RolePermissionMapper.class})
public interface RolePermissionMapper {

    @Mapping(target = "role.id", source = "roleId")
    @Mapping(target = "permission.id", source = "permissionId")
    RolePermission toRolePermission(ReqRolePermissionDto reqRolePermissionDto);

    @Mapping(target = "role.id", source = "roleId")
    @Mapping(target = "permission.id", source = "permissionId")
    RolePermission toRolePermission(ReqUpdatableRolePermissionDto reqUpdatableRolePermissionDto);


    default PageResponse<ResRolePermissionPageItemDto> toResRolePermissionPageItemDto(Page<RolePermission> rolePermissions) {
        return PageMapper.toPageableDto(rolePermissions, this::toResRolePermissionPageItemDto);
    }


    @Mapping(target = "roleTitle", source = "role.title")
    @Mapping(target = "permissionTitle", source = "permission.title")
    ResRolePermissionPageItemDto toResRolePermissionPageItemDto(RolePermission rolePermission);

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleTitle", source = "role.title")
    @Mapping(target = "permissionDto", source = "permission")
    ResRolePermissionDto toResRolePermissionDto(RolePermission rolePermission);
}
