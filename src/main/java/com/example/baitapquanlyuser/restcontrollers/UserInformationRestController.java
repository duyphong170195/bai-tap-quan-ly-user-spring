package com.example.baitapquanlyuser.restcontrollers;

import com.example.baitapquanlyuser.model.PageUserModel;
import com.example.baitapquanlyuser.model.SearchData;
import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.UserInformationService;
import com.example.baitapquanlyuser.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class UserInformationRestController {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    MstGroupService mstGroupService;

    @RequestMapping(value = "/listUserRest", method = RequestMethod.GET)
    public ResponseEntity<PageUserModel> getListUser(SearchData searchData, HttpServletRequest request) {
        PageUserModel pageUserModel = userInformationService.getListUsersInformation(searchData);
        return ResponseEntity.ok(pageUserModel);
    }

    @RequestMapping(value = "/getTotalUser", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalUser(
            @RequestParam(value = "full_name", defaultValue = "") String fullName,
            @RequestParam(value = "group_id", defaultValue = "0") String groupId) {
        Integer totalUsers = userInformationService.getTotalUsers(fullName, groupId);
        return ResponseEntity.ok(totalUsers);
    }
}
