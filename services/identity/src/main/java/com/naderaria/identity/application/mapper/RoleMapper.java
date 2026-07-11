package com.naderaria.identity.application.mapper;


import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_data.mapper.PageMapper;
import com.naderaria.identity.api.dto.role.request.ReqRoleDto;
import com.naderaria.identity.api.dto.role.request.ReqUpdatableRoleDto;
import com.naderaria.identity.api.dto.role.response.ResRolePageItemDto;
import com.naderaria.identity.api.dto.role.response.ResUpdatableRoleDto;
import com.naderaria.identity.infratructure.domin.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = PermissionMapper.class)
public interface RoleMapper {

    default PageResponse<ResRolePageItemDto> toResRolePageItemDto(Page<Role> roles) {
        return PageMapper.toPageableDto(roles, this::toResRolePageItemDto);
    }

    ResRolePageItemDto toResRolePageItemDto(Role role);

    ResUpdatableRoleDto toResUpdatableRoleDto(Role role);

    Role toRole(ReqRoleDto reqRoleDto);


    Role toRole(ReqUpdatableRoleDto reqUpdatableRoleDto);

}
