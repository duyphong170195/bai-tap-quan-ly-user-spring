package com.example.baitapquanlyuser.restcontrollers;

import com.example.baitapquanlyuser.model.PageUserModel;
import com.example.baitapquanlyuser.model.SearchData;
import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.UserInformationService;
import com.example.baitapquanlyuser.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class UserInformationRestController {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    MstGroupService mstGroupService;

    @Autowired
    MessageByLocaleService messageErrorProperties;

    @RequestMapping(value = "/listUserRest", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<PageUserModel> getListUser(SearchData searchData, HttpServletRequest request) {
        PageUserModel pageUserModel = userInformationService.getListUsersInformation(searchData);
        return ResponseEntity.ok(pageUserModel);
    }

}
