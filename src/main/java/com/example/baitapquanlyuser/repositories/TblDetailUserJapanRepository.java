package com.example.baitapquanlyuser.repositories;

import com.example.baitapquanlyuser.entities.TblDetailUserJapan;
import com.example.baitapquanlyuser.repositorycustom.TblDetailUserJapanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblDetailUserJapanRepository extends JpaRepository<TblDetailUserJapan, Integer>, TblDetailUserJapanRepositoryCustom {

}
