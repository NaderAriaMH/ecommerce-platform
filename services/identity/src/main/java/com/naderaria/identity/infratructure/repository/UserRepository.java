package com.naderaria.identity.infratructure.repository;

import com.naderaria.common_security.dto.CurrentUserDto;
import com.naderaria.identity.infratructure.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
                        select
                            new com.naderaria.common_security.dto.CurrentUserDto
                                        (u.id,u.username,u.password,u.accountNonExpired,u.accountNonLocked,
                                                    u.credentialsNonExpired,u.enabled)
                        from User as u
                        where u.username = :username
            """)
    Optional<CurrentUserDto> findByUsername(@Param("username") String username);
}
