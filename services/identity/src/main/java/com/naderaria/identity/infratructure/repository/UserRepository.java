package com.naderaria.identity.infratructure.repository;

import com.naderaria.identity.infratructure.domin.User;
import com.naderaria.identity.application.internal.IntUserAuthenticationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
                        select
                            new com.naderaria.identity.application.internal
                            .IntUserAuthenticationDto(u.id,u.username,u.password,u.accountNonExpired,u.accountNonLocked,u.credentialsNonExpired,u.enabled)
                        from User as u
                        where u.username = :username
            """)
    Optional<IntUserAuthenticationDto> findByUsername(@Param("username") String username);
}
