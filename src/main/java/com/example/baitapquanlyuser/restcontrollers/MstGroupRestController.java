package com.example.baitapquanlyuser.restcontrollers;

import java.util.List;

import com.example.baitapquanlyuser.exceptionhandling.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.services.MstGroupService;

@RestController
public class MstGroupRestController {

    @Autowired
    MstGroupService mstGroupService;

    @RequestMapping(value = "/listGroupRest", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<MstGroup>> getAllMstGroup() throws  IllegalStateException{
            List<MstGroup> mstGroupList = mstGroupService.getAllMstGroup();
            return ResponseEntity.ok(mstGroupList);
    }


    @RequestMapping(value = "/findGroup/{groupId}")
    public ResponseEntity<MstGroup> getGroup(@PathVariable("groupId") Integer birdId) throws EntityNotFoundException {
            return ResponseEntity.ok(mstGroupService.findById(birdId));
    }
}
