package com.example.baitapquanlyuser.services;

import java.util.List;

import com.example.baitapquanlyuser.entities.MstJapan;
import com.example.baitapquanlyuser.repositories.MstJapanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MstJapanService {

    @Autowired
    MstJapanRepository mstJapanRepository;

    public List<MstJapan> getAllMstJapan(){
        return mstJapanRepository.findAll();
    }

    public boolean checkExistedCodeLevel(String codeLevel) {
        return mstJapanRepository.existsById(codeLevel);
    }

    public MstJapan getMstJapanByCodeLevel(String codeLevel) {
        return mstJapanRepository.findById(codeLevel).orElse(null);
    }
}
