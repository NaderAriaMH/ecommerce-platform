package com.naderaria.identity.infratructure.repository;

import com.naderaria.identity.api.dto.permission.response.ResPermissionDto;
import com.naderaria.identity.infratructure.domin.Permission;
import com.naderaria.identity.infratructure.domin.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> , JpaSpecificationExecutor<RolePermission> {




    @Query(value = """
        select p
        from RolePermission rp
        join Permission as p on p.id = rp.permission.id
    """)
    Page<Permission> findAllPermission(Specification<RolePermission> rolePermissionSpecification, Pageable pageable);




    void deleteAllPermissionByRoleId(@Param("roleId") long roleId);

    @Query(value = """
        select count(rp.id) from RolePermission as rp where rp.permission.id = :permissionId
""")
    int findByPermissionId(long permissionId);



    @Query(value = """
        select rp from RolePermission as rp
        where rp.role.id in(:roleIds)
""")
    Optional<List<ResPermissionDto>> getAllResPermissionDtoByRoleId(@Param("roleIds") List<Long> roleIds);


    static Specification<RolePermission> searchByRoleId(Long roleId) {
        return (root, query, cb) ->
                cb.equal(root.get("role").get("id"), roleId);
    }

    static Specification<RolePermission> duplicateRolePermission(Long roleId, Long permissionId) {
        return (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("role").get("id"), roleId),
                        cb.equal(root.get("permission").get("id"), permissionId)
                );
    }
}
