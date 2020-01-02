package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.exceptionhandling.EntityNotFoundException;
import com.example.baitapquanlyuser.exceptionhandling.NotFoundException;
import com.example.baitapquanlyuser.repositories.MstGroupRepository;
import com.example.baitapquanlyuser.repositories.TblUserRepository;
import com.example.baitapquanlyuser.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstGroupService {
	
	@Autowired
	MstGroupRepository mstGroupRepository;

	@Autowired
	TblUserRepository tblUserRepository;
	
	
	public List<MstGroup> getAllMstGroup() {
		List<MstGroup> mstGroupList = mstGroupRepository.findAll();
		if(mstGroupList.isEmpty()) {
			throw new IllegalStateException("list MstGroup must not be empty");
		}
		return mstGroupList;
	}

	public MstGroup findById(Integer groupId) {
		return mstGroupRepository.findById(groupId)
			.orElseThrow(() -> new NotFoundException("MstGroup", groupId));
	}
}
