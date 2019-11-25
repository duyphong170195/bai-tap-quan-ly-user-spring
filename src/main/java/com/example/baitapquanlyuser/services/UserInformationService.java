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
	
	
	public List<UserInformation> getListUsersInformation(String fullName, String groupId,
			String sortType, String sortValue, int currentPage, int limitUser) {

		int offset = Common.getOffset(currentPage, limitUser);
//		Preconditions.checkNotNull(action, "action must not be null");
//		Preconditions.checkNotNull(fullName, "fullName must not be null");
//		Preconditions.checkNotNull(groupId, "groupId must not be null");
//		Preconditions.checkNotNull(sortType, "sortType must not be null");
//		Preconditions.checkNotNull(sortNameValue, "sortNameValue must not be null");
//		Preconditions.checkNotNull(sortLevelValue, "sortLevelValue must not be null");
//		Preconditions.checkNotNull(sortDateValue, "sortDateValue must not be null");
//			case "sort":
//				if(sortType.matches("fullName")){
//					if(sortNameValue.toUpperCase().equals("ASC")){
//						sortValue = "DESC";
//					}else {
//						sortValue = "ASC";
//					}
//				} else if(sortType.equals("nameLevel")){
//					if(sortLevelValue.toUpperCase().equals("ASC")){
//						sortValue = "DESC";
//					}else {
//						sortValue = "ASC";
//					}
//				} else if(sortType.equals("endDate")) {
//					if(sortDateValue.toUpperCase().equals("DESC")){
//						sortValue = "ASC";
//					}else {
//						sortValue = "DESC";
//					}
//				}
//				break;
//			case "pagination":
//				break;
//		}
		return tblDetailUserJapanRepository.findAllUser(fullName, Common.toInteger(groupId), sortType, sortValue, limitUser, offset);
	}

	public Integer getTotalUsers(String fullName, String groupId) {
		return tblDetailUserJapanRepository.countTotalUsers(fullName, Common.toInteger(groupId));
	}
}
