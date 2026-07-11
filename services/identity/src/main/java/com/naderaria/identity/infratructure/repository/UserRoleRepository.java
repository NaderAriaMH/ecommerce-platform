package com.naderaria.identity.infratructure.repository;

import com.naderaria.identity.api.dto.permission.response.ResPermissionDto;
import com.naderaria.identity.infratructure.domin.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    Optional<List<UserRole>> findByUserId(@Param("userId") Long userId);

    @Query("""
        select new com.naderaria.identity.api.dto.permission.response.ResPermissionDto(rp.permission.operation,rp.permission.targetType)
        from UserRole as ur
        join RolePermission rp on rp.role.id = ur.role.id
        where ur.user.id = :userId
""")
    Optional<List<ResPermissionDto>> getAllPermissionsByUserId(@Param("userId") Long userId);

    @Query(value = """
        select ur.role.title from UserRole as ur
        where ur.user.id = :userId
""")
    Optional<List<String>> getUserRolesByUserId(@Param("userId") Long userId);
}
