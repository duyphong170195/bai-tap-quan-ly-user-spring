package com.example.baitapquanlyuser.controllers;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.model.PagerModel;
import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.UserInformationService;
import com.example.baitapquanlyuser.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserInformationController {
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	MstGroupService mstGroupService;
	
	
	@RequestMapping(value = "/listUser1111111", method = RequestMethod.GET)
	public String getListUser(Model model, HttpServletRequest req,
		  	@RequestParam(value = "action", defaultValue ="search" ) String action,
			@RequestParam(value = "fullName", defaultValue = "") String fullName,
			@RequestParam(value = "groupId", defaultValue = "0") String groupId,
			@RequestParam(value = "sortType", defaultValue = "") String sortType,
			@RequestParam(value = "sortNameValue", defaultValue = "ASC") String sortNameValue,
			@RequestParam(value = "sortLevelValue", defaultValue = "ASC") String sortLevelValue,
			@RequestParam(value = "sortDateValue", defaultValue = "DESC") String sortDateValue) {
		try {
			HttpSession session = req.getSession();
			List<UserInformation> userInformationList =
					userInformationService.getListUsersInformation(action, fullName, groupId, sortType, sortNameValue, sortLevelValue, sortDateValue);
			List<MstGroup> mstGroupList = mstGroupService.getAllMstGroup();
			model.addAttribute("userInformationList", userInformationList);
			model.addAttribute("mstGroupList", mstGroupList);
			model.addAttribute("groupId", Common.toInteger(groupId));
			model.addAttribute("fullName", fullName);
			model.addAttribute("sortType", sortType);
			model.addAttribute("sortNameValue", sortNameValue);
			model.addAttribute("sortLevelValue", sortLevelValue);
			model.addAttribute("sortDateValue", sortDateValue);
			return "ADM002";
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public String getListUser(){


		return "ADM0002";
	}

}
