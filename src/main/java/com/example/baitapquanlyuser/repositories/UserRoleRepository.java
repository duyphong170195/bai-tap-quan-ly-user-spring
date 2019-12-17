package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
}
