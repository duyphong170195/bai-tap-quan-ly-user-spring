package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.repositorycustom.TblUserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TblUserRepository extends JpaRepository<TblUser,Integer>, TblUserCustom {

    TblUser findByLoginName(String loginName);

    TblUser findByEmail(String email);

    @Query("SELECT tblUser FROM TblUser tblUser where tblUser.userId <> :userId AND tblUser.email = :email")
    TblUser checkExistedEmailExceptEmailOfUserId(@Param("userId")int userId, @Param("email")String email);
}
