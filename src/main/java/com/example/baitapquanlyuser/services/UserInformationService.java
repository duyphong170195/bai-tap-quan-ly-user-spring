package com.example.baitapquanlyuser.services;

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
    public PageUserModel

    getListUsersInformation(SearchData searchData) {
        PageUserModel pageUserModel = new PageUserModel();
        String action = searchData.getAction();
        String fullName = searchData.getFullName();
        String groupId = searchData.getGroupId();
        String sortType = searchData.getSortType();
        String sortValue = searchData.getSortValue();
        int currentPage = searchData.getCurrentPage();
        boolean nextPage = searchData.isNext();
		boolean previousPage = searchData.isPrevious();
		int limitPage = pageUserModel.getLimitPage();
		int totalUser = tblDetailUserJapanRepository.countTotalUsers(fullName, Common.toInteger(groupId));
//		Preconditions.checkNotNull(action, "action must not be null");
//		Preconditions.checkNotNull(fullName, "fullName must not be null");
//		Preconditions.checkNotNull(groupId, "groupId must not be null");
//		Preconditions.checkNotNull(sortType, "sortType must not be null");
//		Preconditions.checkNotNull(sortNameValue, "sortNameValue must not be null");
//		Preconditions.checkNotNull(sortLevelValue, "sortLevelValue must not be null");
//		Preconditions.checkNotNull(sortDateValue, "sortDateValue must not be null");
		boolean hiddenPrevious = true;
		boolean hiddenNext = true;
		switch (action){
			case "search":
				sortType = "";
				break;
			case "sort":
				sortValue = Common.replaceWildcard(sortValue);
				if(sortType.matches("fullName")){
					if(sortValue.toUpperCase().equals("ASC")){
						sortValue = "DESC";
					}else {
						sortValue = "ASC";
					}
				} else if(sortType.equals("nameLevel")){
					if(sortValue.toUpperCase().equals("ASC")){
						sortValue = "DESC";
					}else {
						sortValue = "ASC";
					}
				} else if(sortType.equals("endDate")) {
					if(sortValue.toUpperCase().equals("DESC")){
						sortValue = "ASC";
					}else {
						sortValue = "DESC";
					}
				}
				break;
			case "pagination":
				if(nextPage) {
					currentPage = Common.getFirstPage(currentPage + limitPage, limitPage);
				} else if(previousPage) {
					currentPage = Common.getFirstPage(currentPage - limitPage, limitPage);
				}
				break;
		}
		int offset = Common.getOffset(currentPage, pageUserModel.getLimitUser());
		int totalPage = Common.getTotalPage(totalUser, pageUserModel.getLimitUser());
		// handle displaying next, previous
        if(Common.getFirstPage(currentPage + limitPage, limitPage) < totalPage){
            hiddenNext = false;
        }
        if (currentPage - limitPage >= 1) {
            hiddenPrevious = false;
        }
		SearchData searchDataBuilder = new SearchData(action, fullName, groupId, sortType, sortValue, currentPage, nextPage, previousPage, hiddenPrevious, hiddenNext);
		List<UserInformation> userInformationList = tblDetailUserJapanRepository.findAllUser(fullName, Common.toInteger(groupId), sortType, sortValue, pageUserModel.getLimitUser(), offset);
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
