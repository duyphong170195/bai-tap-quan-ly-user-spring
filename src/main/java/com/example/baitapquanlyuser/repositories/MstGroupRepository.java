package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.MstGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MstGroupRepository extends JpaRepository<MstGroup, Integer> {
}
