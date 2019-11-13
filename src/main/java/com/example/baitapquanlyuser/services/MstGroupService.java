package com.example.baitapquanlyuser.services;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.repositories.MstGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstGroupService {
	
	@Autowired
	MstGroupRepository mstGroupRepository;
	
	
	public List<MstGroup> getAllMstGroup() {
		return mstGroupRepository.findAll();
	}
}
