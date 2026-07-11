package com.naderaria.identity.application.service;

import com.naderaria.common_core.dto.request.PaginationDto;
import com.naderaria.common_core.dto.response.MessageResponse;
import com.naderaria.common_core.dto.response.PageResponse;
import com.naderaria.common_core.exception.DataReferencedException;
import com.naderaria.common_core.exception.DuplicateDataException;
import com.naderaria.common_core.util.MessageService;
import com.naderaria.common_data.mapper.PageMapper;
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
import com.naderaria.identity.application.mapper.PermissionMapper;
import com.naderaria.identity.application.mapper.RoleMapper;
import com.naderaria.identity.application.mapper.RolePermissionMapper;
import com.naderaria.identity.infratructure.domin.Permission;
import com.naderaria.identity.infratructure.domin.Role;
import com.naderaria.identity.infratructure.domin.RolePermission;
import com.naderaria.identity.infratructure.repository.PermissionRepository;
import com.naderaria.identity.infratructure.repository.RolePermissionRepository;
import com.naderaria.identity.infratructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final MessageService messageService;
    private final PageMapper pageMapper;


    @Transactional
    @Override
    public PageResponse<ResPermissionPageItemDto> getAllPermissions(PaginationDto paginationDto, String targetType) {
        Pageable pageable = pageMapper.convertToPageable(paginationDto);
        Page<Permission> permissions;
        if (targetType != null && !targetType.isBlank()) {
            permissions = permissionRepository.findAll(PermissionRepository.searchByTargetType(targetType), pageable);
        } else {
            permissions = permissionRepository.findAll(pageable);
        }
        return permissionMapper.toResPermissionPageItemDto(permissions);
    }

    @Transactional
    @Override
    public PageResponse<ResPermissionPageItemDto> getAllPermissionsByRoleId(PaginationDto paginationDto, long roleId) {//todo this is not work;
        Pageable pageable = pageMapper.convertToPageable(paginationDto);
        Page<Permission> permissions = rolePermissionRepository.findAllPermission(RolePermissionRepository.searchByRoleId(roleId), pageable);
        return permissionMapper.toResPermissionPageItemDto(permissions);
    }

    @Transactional
    @Override
    public ResUpdatablePermissionDto getPermission(long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(NullPointerException::new);
        return permissionMapper.toResUpdatablePermissionDto(permission);
    }

    @Transactional
    @Override
    public URI savePermission(ReqPermissionDto reqPermissionDto) {
        Permission permission = permissionMapper.toPermission(reqPermissionDto);
        validateDuplicatePermission(permission);
        permissionRepository.save(permission);
        try {
            return new URI(ServletUriComponentsBuilder.fromCurrentContextPath().path("/permission/permission/").toUriString() + permission.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public ResUpdatablePermissionDto updatePermission(ReqUpdatablePermissionDto reqUpdatablePermissionDto) {
        Permission permission = permissionMapper.toPermission(reqUpdatablePermissionDto);
        validateDuplicatePermission(permission);
        permissionRepository.save(permission);
        return permissionMapper.toResUpdatablePermissionDto(permission);
    }

    private void validateDuplicatePermission(Permission permission) {
        if (permissionRepository.exists(PermissionRepository
                .duplicatePermission(permission.getOperation(), permission.getTargetType(), permission.getTargetScope())))
            throw new DuplicateDataException("Permission is already exists");
    }

    @Transactional
    @Override
    public MessageResponse deletePermission(long id) {
        checkRolePermissionDependOnIt(id);
        permissionRepository.deleteById(id);
        return new MessageResponse(messageService.getLocalizedMessage("group.deleteAll"));
    }

    private void checkRolePermissionDependOnIt(long permissionId) {
        if (rolePermissionRepository.findByPermissionId(permissionId) > 0)
            throw new DataReferencedException();
    }

    @Transactional
    @Override
    public MessageResponse deleteAllPermissionsByRoleId(long roleId) {
        rolePermissionRepository.deleteAllPermissionByRoleId(roleId);
        return new MessageResponse(messageService.getLocalizedMessage("permission.deleteAllPermissionsByRoleId"));
    }

    @Transactional
    @Override
    public MessageResponse deleteAllPermissions() {
        permissionRepository.deleteAll();
        return new MessageResponse(messageService.getLocalizedMessage("permission.deleteAllPermissions"));
    }

    @Transactional
    @Override
    public PageResponse<ResRolePageItemDto> getAllRoles(PaginationDto paginationDto, String title) {
        Pageable pageable = pageMapper.convertToPageable(paginationDto);
        Page<Role> roles;
        if (title != null && title.isBlank()) {
            roles = roleRepository.findAll(RoleRepository.searchByTitle(title), pageable);
        } else {
            roles = roleRepository.findAll(pageable);
        }
        return roleMapper.toResRolePageItemDto(roles);
    }

    @Transactional
    @Override
    public ResUpdatableRoleDto getRole(long id) {
        Role role = roleRepository.findById(id).orElseThrow(NullPointerException::new);
        return roleMapper.toResUpdatableRoleDto(role);
    }

    @Transactional
    @Override
    public URI saveRole(ReqRoleDto reqRoleDto) {
        Role role = roleMapper.toRole(reqRoleDto);
        validateDuplicateRole(role);
        roleRepository.save(role);
        try {
            return new URI(ServletUriComponentsBuilder.fromCurrentContextPath().path("/lab/permission/role/").toUriString() + role.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public ResUpdatableRoleDto updateRole(ReqUpdatableRoleDto reqUpdatableRoleDto) {
        Role role = roleMapper.toRole(reqUpdatableRoleDto);
        validateDuplicateRole(role);
        roleRepository.save(role);
        return roleMapper.toResUpdatableRoleDto(role);
    }

    private void validateDuplicateRole(Role role) {
        if (roleRepository.exists(RoleRepository.duplicateRole(role.getTitle())))
            throw new DuplicateDataException("Role is already exists");
    }

    @Transactional
    @Override
    public MessageResponse deleteRole(long id) {
        rolePermissionRepository.deleteAllPermissionByRoleId(id);
        rolePermissionRepository.flush();
        roleRepository.deleteRoleById(id);
        return new MessageResponse(messageService.getLocalizedMessage("role.deleteRole"));
    }

    @Transactional
    @Override
    public MessageResponse deleteAllRoles() {
        rolePermissionRepository.deleteAll();
        rolePermissionRepository.flush();
        roleRepository.deleteAll();
        return new MessageResponse(messageService.getLocalizedMessage("role.deleteAllRole"));
    }

    @Transactional
    @Override
    public PageResponse<ResRolePermissionPageItemDto> getAllRolePermissions(PaginationDto paginationDto) {
        Pageable pageable = pageMapper.convertToPageable(paginationDto);
        Page<RolePermission> rolePermissions = rolePermissionRepository.findAll(pageable);
        Map<Role, Permission> rolePermissionMap = new HashMap<>();//TODO can't put all rolePermissions into rolePermissionMap,becuase role key is duplicate*/
        rolePermissions.stream().forEach(rolePermission -> rolePermissionMap.put(rolePermission.getRole(), rolePermission.getPermission()));
        return rolePermissionMapper.toResRolePermissionPageItemDto(rolePermissions);//TODO can't convert map to page
    }

    @Transactional
    @Override
    public URI saveRolePermission(ReqRolePermissionDto reqRolePermissionDto) {
        RolePermission rolePermission = rolePermissionMapper.toRolePermission(reqRolePermissionDto);
        validateDuplicateRolePermission(rolePermission);
        rolePermissionRepository.save(rolePermission);
        try {
            return new URI(ServletUriComponentsBuilder.fromCurrentContextPath().path("/lab/permission/role/").toUriString() + rolePermission.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Transactional
    @Override
    public ResRolePermissionDto updateRolePermission(ReqUpdatableRolePermissionDto reqUpdatableRolePermissionDto) {
        RolePermission rolePermission = rolePermissionMapper.toRolePermission(reqUpdatableRolePermissionDto);
        validateDuplicateRolePermission(rolePermission);
        rolePermissionRepository.save(rolePermission);
        return rolePermissionMapper.toResRolePermissionDto(rolePermission);
    }


    private void validateDuplicateRolePermission(RolePermission rolePermission) {
        if (rolePermissionRepository
                .exists(RolePermissionRepository
                        .duplicateRolePermission(rolePermission.getRole().getId(), rolePermission.getPermission().getId())))
            throw new DuplicateDataException("RolePermission is already exists");
    }

    @Transactional
    @Override
    public MessageResponse deleteAllRolePermission(long id) {
        rolePermissionRepository.deleteById(id);
        return new MessageResponse(messageService.getLocalizedMessage("RolePermissions.deleteAllRolePermissionsByRoleId"));
    }

    @Transactional
    @Override
    public MessageResponse deleteAllRolePermissions() {
        rolePermissionRepository.deleteAll();
        return new MessageResponse(messageService.getLocalizedMessage("RolePermissions.deleteAllRolePermissions"));
    }
}
