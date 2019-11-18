package com.example.baitapquanlyuser.controllers;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.services.MstGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MstGroupRestController {

    @Autowired
    MstGroupService mstGroupService;

    @RequestMapping(value = "/listGroup", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMstGroup() {
        return ResponseEntity.ok(new MstGroup(6, "Group 7"));
    }
}
