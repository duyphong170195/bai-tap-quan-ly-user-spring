package com.example.baitapquanlyuser.repositorycustom;

import com.example.baitapquanlyuser.model.UserInformation;

import java.util.List;

public interface TblDetailUserJapanRepositoryCustom {
	
	List<UserInformation> findAllUser(String fullName, int groupId);
}
