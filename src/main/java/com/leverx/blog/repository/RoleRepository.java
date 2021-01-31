package com.leverx.blog.repository;

import com.leverx.blog.model.Role;
import com.leverx.blog.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRole(Role role);
}
