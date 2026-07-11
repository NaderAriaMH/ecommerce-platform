package com.naderaria.identity.application.service;

import com.naderaria.identity.api.dto.permission.response.ResPermissionDto;
import com.naderaria.identity.application.internal.IntUserAuthenticationDto;
import com.naderaria.identity.infratructure.domin.Role;
import com.naderaria.identity.infratructure.repository.RolePermissionRepository;
import com.naderaria.identity.infratructure.repository.UserRepository;
import com.naderaria.identity.infratructure.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public UserDetailsService userDetailService() {
        return username -> {
            IntUserAuthenticationDto intUserAuthenticationDto = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " not found "));
            List<String> roleNames = userRoleRepository.getUserRolesByUserId(intUserAuthenticationDto.getId()).orElseThrow(() -> new UsernameNotFoundException(username + " not found "));
            List<ResPermissionDto> resPermissionDtoList = userRoleRepository.getAllPermissionsByUserId(intUserAuthenticationDto.getId()).orElseThrow(NullPointerException::new);
            intUserAuthenticationDto.setRoleNames(roleNames);
            intUserAuthenticationDto.setPermissions(resPermissionDtoList);
            return intUserAuthenticationDto;
        };
    }

    private List<ResPermissionDto> getCurrentPermissions(List<Long> roleIds) {
        return rolePermissionRepository.getAllResPermissionDtoByRoleId(roleIds).orElseThrow(NullPointerException::new);
    }
}
