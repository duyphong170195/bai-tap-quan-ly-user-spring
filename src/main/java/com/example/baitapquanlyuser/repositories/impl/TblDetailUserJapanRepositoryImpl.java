package com.example.baitapquanlyuser.repositories.impl;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.repositories.TblDetailUserJapanRepository;
import com.example.baitapquanlyuser.repositorycustom.TblDetailUserJapanRepositoryCustom;
import com.example.baitapquanlyuser.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class TblDetailUserJapanRepositoryImpl implements TblDetailUserJapanRepositoryCustom {
	
	@Autowired
	EntityManager entityManager;
	
	
	@Override
	public List<UserInformation> findAllUser(String fullName, int groupId, String sortType, String sortNameValue,
			String sortLevelValue, String sortEndDateValue, int limitUser, int offset) {
//		Preconditions.checkNotNull(fullName, "fullName must not be null");
//		Preconditions.checkNotNull(sortType, "sortType must not be null");
		StringBuilder queryStatement = new StringBuilder();
		queryStatement.append(
				"SELECT new com.example.baitapquanlyuser.model.UserInformation(tblUser.userId, tblUser.fullName, DATE_FORMAT(tblUser.birthday, '%Y-%m-%d'), mstGroup.groupName, tblUser.email, tblUser.telephone, coalesce(mstJapan.nameLevel,''), coalesce(DATE_FORMAT(tblDetail.endDate, '%Y-%m-%d'),''), coalesce(tblDetail.total, 0)   ) ");
		queryStatement.append("FROM TblDetailUserJapan tblDetail ");
		queryStatement.append("RIGHT JOIN tblDetail.tblUser tblUser ");
		queryStatement.append("LEFT JOIN tblDetail.mstJapan mstJapan ");
		queryStatement.append("INNER JOIN tblUser.mstGroup mstGroup ");
		
		queryStatement.append("WHERE (1=1) ");
		if (fullName.isEmpty() == false) {
			queryStatement.append("AND tblUser.fullName LIKE :fullName ");
		}
		if (groupId > 0) {
			queryStatement.append("AND mstGroup.groupId = :groupId ");
		}
		switch (sortType) {
			case "sortFullName":
				queryStatement.append("ORDER BY tblUser.fullName " + sortNameValue + ", ");
				queryStatement.append("mstJapan.nameLevel " + sortLevelValue + ", ");
				queryStatement.append("tblDetail.endDate " + sortEndDateValue + " ");
				break;
			case "sortNameLevel":
				queryStatement.append("ORDER BY mstJapan.nameLevel " + sortLevelValue + ", ");
				queryStatement.append("tblUser.fullName " + sortNameValue + ", ");
				queryStatement.append("tblDetail.endDate " + sortEndDateValue + " ");
				break;
			case "sortEndDate":
				queryStatement.append("ORDER BY tblDetail.endDate " + sortEndDateValue + ", ");
				queryStatement.append("tblUser.fullName " + sortNameValue + ", ");
				queryStatement.append("mstJapan.nameLevel " + sortLevelValue + " ");
				break;
			default:
				queryStatement.append("ORDER BY tblUser.fullName " + sortNameValue + ", ");
				queryStatement.append("mstJapan.nameLevel " + sortLevelValue + ", ");
				queryStatement.append("tblDetail.endDate " + sortEndDateValue + " ");
				break;
		}
		
		Query query = entityManager.createQuery(queryStatement.toString());
		query.setFirstResult(offset);
		query.setMaxResults(limitUser);
		if (fullName.isEmpty() == false) {
			fullName = Common.replaceWildcard(fullName);
			query.setParameter("fullName", "%" + fullName + "%");
		}
		if (groupId > 0) {
			query.setParameter("groupId", groupId);
		}
		
		return query.getResultList();
	}
	
	@Override
	public int countTotalUsers(String fullName, int groupId) {
		StringBuilder queryStatement = new StringBuilder();
		queryStatement.append("SELECT tblDetail ");
		queryStatement.append("FROM TblDetailUserJapan tblDetail ");
		queryStatement.append("RIGHT JOIN tblDetail.tblUser tblUser ");
		queryStatement.append("LEFT JOIN tblDetail.mstJapan mstJapan ");
		queryStatement.append("INNER JOIN tblUser.mstGroup mstGroup ");
		
		queryStatement.append("WHERE (1=1) ");
		if (fullName.isEmpty() == false) {
			queryStatement.append("AND tblUser.fullName LIKE :fullName ");
		}
		if (groupId > 0) {
			queryStatement.append("AND mstGroup.groupId = :groupId ");
		}
		Query query = entityManager.createQuery(queryStatement.toString());
		if (fullName.isEmpty() == false) {
			fullName = Common.replaceWildcard(fullName);
			query.setParameter("fullName", "%" + fullName + "%");
		}
		if (groupId > 0) {
			query.setParameter("groupId", groupId);
		}
		return query.getResultList().size();
	}
}
