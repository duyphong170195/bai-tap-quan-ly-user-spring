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
	public List<UserInformation> findAllUser(String fullName, int groupId, String sortType, String sortValue) {
//		Preconditions.checkNotNull(fullName, "fullName must not be null");
//		Preconditions.checkNotNull(sortType, "sortType must not be null");

		StringBuilder queryStatement = new StringBuilder();
		queryStatement.append(
				"SELECT new com.example.baitapquanlyuser.model.UserInformation(tblUser.userId, tblUser.fullName, tblUser.birthday, mstGroup.groupName, tblUser.email, tblUser.telephone, mstJapan.nameLevel, tblDetail.endDate, tblDetail.total) ");
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
		if(sortType.isEmpty() == false) {
			sortValue = Common.replaceWildcard(sortValue);
			switch (sortType){
				case "fullName":
					queryStatement.append("ORDER BY tblUser.fullName "+ sortValue +", ");
					queryStatement.append("mstJapan.nameLevel ASC, ");
					queryStatement.append("tblDetail.endDate DESC ");
					break;
				case "nameLevel":
					queryStatement.append("ORDER BY mstJapan.nameLevel "+ sortValue +", ");
					queryStatement.append("tblUser.fullName ASC, ");
					queryStatement.append("tblDetail.endDate DESC ");
					break;
				case "endDate":
					queryStatement.append("ORDER BY tblDetail.endDate "+ sortValue +", ");
					queryStatement.append("tblUser.fullName ASC, ");
					queryStatement.append("mstJapan.nameLevel ASC ");
					break;
				default:
					queryStatement.append("ORDER BY tblUser.fullName ASC, ");
					queryStatement.append("mstJapan.nameLevel ASC, ");
					queryStatement.append("tblDetail.endDate DESC ");
			}
		}
		
		Query query = entityManager.createQuery(queryStatement.toString());
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
	public List<UserInformation> findAllUser(String fullName, int groupId) {
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
		Query query = entityManager.createQuery(queryStatement.toString());
		if (fullName.isEmpty() == false) {
			fullName = Common.replaceWildcard(fullName);
			query.setParameter("fullName", "%" + fullName + "%");
		}
		if (groupId > 0) {
			query.setParameter("groupId", groupId);
		}

		return query.getResultList();
	}
}
