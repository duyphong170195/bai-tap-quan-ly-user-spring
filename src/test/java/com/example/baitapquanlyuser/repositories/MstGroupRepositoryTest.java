package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.entities.TblUser;
import com.example.baitapquanlyuser.entities.UserRole;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MstGroupRepositoryTest {

    @Autowired
    MstGroupRepository mstGroupRepository;
    @Autowired
    TblDetailUserJapanRepository tblDetailUserJapanRepository;
    @Autowired
    TblUserRepository tblUserRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void findAll(){
        String a = "";
        if(a.isEmpty()){
            System.out.print("aaaaaaaaaaaaaaaaaaaHello world");
        }
        assertEquals(3,4);
    }

    @Test
    public void findByLoginName(){

        TblUser tblUser = tblUserRepository.findByUserName("admin");
        tblUser.getLoginName();
     userRoleRepository.findAll();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String a = encoder.encode("123456");
    }
}
