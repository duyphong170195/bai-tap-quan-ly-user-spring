package com.example.baitapquanlyuser.repositorycustom;

import com.example.baitapquanlyuser.entities.TblUser;

public interface TblUserCustom {

    TblUser findByUserName(String username);
}
