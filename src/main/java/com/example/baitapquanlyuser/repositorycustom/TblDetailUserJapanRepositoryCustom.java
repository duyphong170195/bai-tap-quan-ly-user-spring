package com.example.baitapquanlyuser.repositorycustom;

import com.example.baitapquanlyuser.model.UserInformation;

import java.util.List;

public interface TblDetailUserJapanRepositoryCustom {
	
	List<UserInformation> findAllUser(String fullName, int groupId, String sortType, String sortValue, int limitUser, int offset);
	int countTotalUsers(String fullName, int groupId);
}
