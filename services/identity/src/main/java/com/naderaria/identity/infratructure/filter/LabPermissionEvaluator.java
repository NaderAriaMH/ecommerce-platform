package com.naderaria.identity.infratructure.filter;

import com.naderaria.identity.api.dto.permission.response.ResPermissionDto;
import com.naderaria.identity.application.internal.IntUserAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LabPermissionEvaluator implements PermissionEvaluator {

    //private final List<PermissionStrategy<?>> permissionStrategies;

    //private final UserService userService;


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        //The access check method works based on different strategies, but it is not needed now
/*
        Optional<PermissionStrategy<?>> permissionStrategyOptional = permissionStrategies.stream()
                .filter(ps -> ps.support(targetDomainObject, permission.toString())).findAny();

        return permissionStrategyOptional
                .filter(permissionStrategy ->
                        invokeWithCast(permissionStrategy, authentication, targetDomainObject, permission))
                .isPresent();
*/


        return getPermissions(authentication)
                .anyMatch(p -> p.operation().equals(permission.toString()) &&
                                               p.targetType().equals(targetDomainObject)
                );


    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return getPermissions(authentication)
                .anyMatch(p -> p.operation().equals(permission.toString()));


    }

    private Stream<ResPermissionDto> getPermissions(Authentication authentication) {
        Objects.requireNonNull(authentication.getPrincipal());
        IntUserAuthenticationDto principal = (IntUserAuthenticationDto) authentication.getPrincipal();
        return principal.getPermissions().stream();

    }
}
