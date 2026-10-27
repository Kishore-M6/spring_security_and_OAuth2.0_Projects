package com.boot.repository;


import com.boot.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersandRolesRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
