package com.example.baitapquanlyuser.restcontrollers;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.UserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserInformationRestController {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    MstGroupService mstGroupService;

    @RequestMapping(value = "/listUserRest", method = RequestMethod.GET)
    public ResponseEntity<List<UserInformation>> getListUser(
            @RequestParam(value = "action", defaultValue = "") String action,
            @RequestParam(value = "full_name", defaultValue = "") String fullName,
            @RequestParam(value = "group_id", defaultValue = "0") String groupId,
            @RequestParam(value = "sort_type", defaultValue = "") String sortType,
            @RequestParam(value = "sort_value", defaultValue = "ASC") String sortNameValue) {
        List<UserInformation> userInformationList =
                userInformationService.getListUsersInformation(action, fullName, groupId, sortType, sortNameValue, "", "");

        return ResponseEntity.ok(userInformationList);
    }
}
