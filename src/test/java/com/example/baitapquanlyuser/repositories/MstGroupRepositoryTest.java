package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.MstGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MstGroupRepositoryTest {

    @Autowired
    MstGroupRepository mstGroupRepository;

    @Test
    public void findAll(){
        List<MstGroup> mstGroupList = mstGroupRepository.findAll();
        mstGroupList.forEach(mstGroup -> {
            System.out.print(mstGroup.getGroupId());
        });
    }
}
