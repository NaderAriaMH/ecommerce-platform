package com.naderaria.identity.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.MessageResponse;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.identity.api.dto.permission.request.ReqPermissionDto;
import com.naderaria.identity.api.dto.permission.request.ReqUpdatablePermissionDto;
import com.naderaria.identity.api.dto.permission.response.ResPermissionPageItemDto;
import com.naderaria.identity.api.dto.permission.response.ResUpdatablePermissionDto;
import com.naderaria.identity.api.dto.role.request.ReqRoleDto;
import com.naderaria.identity.api.dto.role.request.ReqUpdatableRoleDto;
import com.naderaria.identity.api.dto.role.response.ResRolePageItemDto;
import com.naderaria.identity.api.dto.role.response.ResUpdatableRoleDto;
import com.naderaria.identity.api.dto.role_permission.request.ReqRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.request.ReqUpdatableRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.response.ResRolePermissionDto;
import com.naderaria.identity.api.dto.role_permission.response.ResRolePermissionPageItemDto;

import java.net.URI;

public interface PermissionService {

    PageResponse<ResPermissionPageItemDto> getAllPermissions(PaginationDto paginationDto, String targetType);

    PageResponse<ResPermissionPageItemDto> getAllPermissionsByRoleId(PaginationDto paginationDto, long roleId);

    ResUpdatablePermissionDto getPermission(long id);

    URI savePermission(ReqPermissionDto reqPermissionDto);

    ResUpdatablePermissionDto updatePermission(ReqUpdatablePermissionDto reqUpdatablePermissionDto);

    MessageResponse deletePermission(long id);

    MessageResponse deleteAllPermissionsByRoleId(long roleId);

    MessageResponse deleteAllPermissions();

    PageResponse<ResRolePageItemDto> getAllRoles(PaginationDto paginationDto, String title);

    ResUpdatableRoleDto getRole(long id);

    URI saveRole(ReqRoleDto reqRoleDto);

    ResUpdatableRoleDto updateRole(ReqUpdatableRoleDto reqUpdatableRoleDto);

    MessageResponse deleteRole(long id);

    MessageResponse deleteAllRoles();

    PageResponse<ResRolePermissionPageItemDto> getAllRolePermissions(PaginationDto paginationDto);

    URI saveRolePermission(ReqRolePermissionDto reqRolePermissionDto);

    ResRolePermissionDto updateRolePermission(ReqUpdatableRolePermissionDto reqUpdatableRolePermissionDto);

    MessageResponse deleteAllRolePermission(long id);

    MessageResponse deleteAllRolePermissions();

}
