package com.example.baitapquanlyuser.repositories.impl;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.repositories.TblDetailUserJapanRepository;
import com.example.baitapquanlyuser.repositorycustom.TblDetailUserJapanRepositoryCustom;
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
	public List<UserInformation> findAllUser(String fullName, int groupId) {
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
		
		Query query = entityManager.createQuery(queryStatement.toString());
		if (fullName.isEmpty() == false) {
			query.setParameter("fullName", "%" + fullName + "%");
		}
		if (groupId > 0) {
			query.setParameter("groupId", groupId);
		}
		return query.getResultList();
	}
}
