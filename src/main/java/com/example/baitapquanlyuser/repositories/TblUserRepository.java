package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.repositorycustom.TblUserCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblUserRepository extends JpaRepository<TblUser,Integer>, TblUserCustom {

}
