package com.alexnmat.exam.repositories;

import com.alexnmat.exam.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
@Author: MSN & AFC
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    Role findById(long id);
}
