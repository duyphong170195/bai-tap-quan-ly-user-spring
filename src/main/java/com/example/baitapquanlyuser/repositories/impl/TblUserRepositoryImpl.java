package com.example.baitapquanlyuser.repositories.impl;

import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.repositories.TblUserRepository;
import com.example.baitapquanlyuser.repositorycustom.TblUserCustom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TblUserRepositoryImpl implements TblUserCustom {

    @Autowired
    EntityManager entityManager;


    @Override
    public TblUser findByUserName(String username) {
        StringBuilder queryStatement = new StringBuilder();
        queryStatement.append("SELECT tblUser FROM TblUser tblUser WHERE tblUser.loginName = :loginName");
        Query query = entityManager.createQuery(queryStatement.toString());
        query.setParameter("loginName", username);
        List<TblUser> tblUserList = query.getResultList();
        if(tblUserList.size() == 0){
            return null;
        }
        return tblUserList.get(0);
    }
}
