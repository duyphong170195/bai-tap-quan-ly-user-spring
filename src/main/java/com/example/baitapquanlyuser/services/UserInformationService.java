package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.repositories.TblDetailUserJapanRepository;
import com.example.baitapquanlyuser.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInformationService {
	
	@Autowired
	TblDetailUserJapanRepository tblDetailUserJapanRepository;
	
	
	public List<UserInformation> getListUsersInformation(String fullName, String groupId) {
		
		if (Common.isNumber(groupId) == false) {
			groupId = "0";
		}
		
		return tblDetailUserJapanRepository.findAllUser(fullName, Common.toInteger(groupId));
	}
}
