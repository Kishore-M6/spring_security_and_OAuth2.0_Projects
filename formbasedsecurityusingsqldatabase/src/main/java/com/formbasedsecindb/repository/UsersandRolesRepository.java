package com.formbasedsecindb.repository;

import com.formbasedsecindb.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersandRolesRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
