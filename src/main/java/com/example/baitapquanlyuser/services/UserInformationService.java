package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.exceptionhandling.NotFoundException;
import com.example.baitapquanlyuser.model.PageUserModel;
import com.example.baitapquanlyuser.model.SearchData;
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
	
	
	//	public PageUserModel getListUsersInformation(String fullName, int groFupId, String sortType, String sortValue, int currentPage) {
	public PageUserModel getListUsersInformation(SearchData searchData) {
		PageUserModel pageUserModel = new PageUserModel();
		String action = searchData.getAction();
		String fullName = searchData.getFullName();
		String groupId = searchData.getGroupId();
		String sortType = searchData.getSortType();
		String sortNameValue = searchData.getSortNameValue();
		String sortLevelValue = searchData.getSortLevelValue();
		String sortEndDateValue = searchData.getSortEndDateValue();
		int currentPage = searchData.getCurrentPage();
		boolean nextPage = searchData.isNext();
		boolean previousPage = searchData.isPrevious();
		int limitPage = pageUserModel.getLimitPage();
		int totalUser = tblDetailUserJapanRepository.countTotalUsers(fullName, Common.toInteger(groupId));
		if(totalUser == 0) {
			throw new NotFoundException("TblUser", 3);
		}
//		Preconditions.checkNotNull(action, "action must not be null");
//		Preconditions.checkNotNull(fullName, "fullName must not be null");
//		Preconditions.checkNotNull(groupId, "groupId must not be null");
//		Preconditions.checkNotNull(sortType, "sortType must not be null");
//		Preconditions.checkNotNull(sortValue, "sortNameValue must not be null");
		boolean hiddenPrevious = true;
		boolean hiddenNext = true;
		switch (action) {
			case "search":
				sortType = "";
				currentPage = 1;
				sortNameValue = "ASC";
				sortLevelValue = "ASC";
				sortEndDateValue = "DESC";
				break;
			case "sort":
				if (sortType.matches("sortFullName")) {
					sortLevelValue = "ASC";
					sortEndDateValue = "DESC";
					if (sortNameValue.toUpperCase().equals("ASC")) {
						sortNameValue = "DESC";
					} else {
						sortNameValue = "ASC";
					}
				} else if (sortType.equals("sortNameLevel")) {
					sortNameValue = "ASC";
					sortEndDateValue = "DESC";
					if (sortLevelValue.toUpperCase().equals("ASC")) {
						sortLevelValue = "DESC";
					} else {
						sortLevelValue = "ASC";
					}
				} else if (sortType.equals("sortEndDate")) {
					sortNameValue = "ASC";
					sortLevelValue = "ASC";
					if (sortEndDateValue.toUpperCase().equals("DESC")) {
						sortEndDateValue = "ASC";
					} else {
						sortEndDateValue = "DESC";
					}
				}
				break;
			case "pagination":
				if (nextPage) {
					currentPage = Common.getFirstPage(currentPage + limitPage, limitPage);
				} else if (previousPage) {
					currentPage = Common.getFirstPage(currentPage - limitPage, limitPage);
				}
				break;
			default:
				sortType = "";
				currentPage = 1;
				sortNameValue = "ASC";
				sortLevelValue = "ASC";
				sortEndDateValue = "DESC";
				break;
			
		}
		int offset = Common.getOffset(currentPage, pageUserModel.getLimitUser());
		int totalPage = Common.getTotalPage(totalUser, pageUserModel.getLimitUser());
		// handle displaying next, previous
		if (Common.getFirstPage(currentPage + limitPage, limitPage) < totalPage) {
			hiddenNext = false;
		}
		if (currentPage - limitPage >= 1) {
			hiddenPrevious = false;
		}
		SearchData searchDataBuilder = new SearchData(action, fullName, groupId, sortType, sortNameValue,
				sortLevelValue, sortEndDateValue, currentPage, nextPage, previousPage, hiddenPrevious, hiddenNext);
		List<UserInformation> userInformationList =
				tblDetailUserJapanRepository.findAllUser(fullName, Common.toInteger(groupId), sortType,
						Common.replaceWildcard(sortNameValue),
						Common.replaceWildcard(sortLevelValue), Common.replaceWildcard(sortEndDateValue),
						pageUserModel.getLimitUser(), offset);
		pageUserModel.setSearchData(searchDataBuilder);
		pageUserModel.setListUser(userInformationList);
		pageUserModel.setTotalUser(totalUser);
		pageUserModel.setListPage(pageUserModel.getListPaging());
		return pageUserModel;
	}
	
	public Integer getTotalUsers(String fullName, String groupId) {
		return tblDetailUserJapanRepository.countTotalUsers(fullName, Common.toInteger(groupId));
	}
}
