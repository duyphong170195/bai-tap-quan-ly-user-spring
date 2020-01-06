package com.example.baitapquanlyuser.controllers;

import com.example.baitapquanlyuser.entities.MstGroup;
import com.example.baitapquanlyuser.model.PagerModel;
import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.MstJapanService;
import com.example.baitapquanlyuser.services.UserInformationService;
import com.example.baitapquanlyuser.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserInformationController {
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	MstGroupService mstGroupService;

	@Autowired
	MessageByLocaleService messageErrorProperties;

	@Autowired
	MstJapanService mstJapanService;
	
	
	@RequestMapping(value = "/listUser1111111", method = RequestMethod.GET)
	public String getListUser(Model model, HttpServletRequest req,
		  	@RequestParam(value = "action", defaultValue ="search" ) String action,
			@RequestParam(value = "fullName", defaultValue = "") String fullName,
			@RequestParam(value = "groupId", defaultValue = "0") String groupId,
			@RequestParam(value = "sortType", defaultValue = "") String sortType,
			@RequestParam(value = "sortNameValue", defaultValue = "ASC") String sortNameValue,
			@RequestParam(value = "sortLevelValue", defaultValue = "ASC") String sortLevelValue,
			@RequestParam(value = "sortDateValue", defaultValue = "DESC") String sortDateValue) {
		return "ADM002";
	}

	@RequestMapping(value = {"/listUser", "/"}, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public String getListUser() {
		return "ADM0002";
	}

	@RequestMapping(value = {"/addingUserForm"}, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public String addingUserForm(Model model) throws ClassNotFoundException, SQLException {
		setDefaultValue(model);
		setDataLogic(model);
        return "ADM003";
	}


	private void setDataLogic(Model model) throws ClassNotFoundException, SQLException {
		int fromYear = 1900;
		int toYear = Calendar.getInstance().get(Calendar.YEAR);
		// setAttribute các dữ liệu để gửi lên ADM003
		model.addAttribute("listYear", Common.getListYear(fromYear, toYear + 2));
		model.addAttribute("listMonth", Common.getListMonth());
		model.addAttribute("listDay", Common.getListDay());
		model.addAttribute("listGroup", mstGroupService.getAllMstGroup());
		model.addAttribute("listLevel", mstJapanService.getAllMstJapan());
	}

	private  void setDefaultValue(Model model) {
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		UserInformation userInformation = new UserInformation();
		userInformation.setBirthday(todayAsString);
		userInformation.setStartDate(todayAsString);
		userInformation.setEndDate(todayAsString);
		model.addAttribute("userInfor", userInformation);
	}
}
