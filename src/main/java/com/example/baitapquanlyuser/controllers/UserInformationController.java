package com.example.baitapquanlyuser.controllers;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.MstJapanService;
import com.example.baitapquanlyuser.services.UserInformationService;
import com.example.baitapquanlyuser.utils.Common;
import com.example.baitapquanlyuser.utils.CommonConstant;
import com.example.baitapquanlyuser.validates.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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

	@Autowired
	ValidateUser validateUser;

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


	@RequestMapping(value = "/confirmUserForm", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public String confirmUserForm(Model model, @ModelAttribute("userInformation") UserInformation userInformation, HttpServletRequest req) throws ClassNotFoundException, SQLException {
		List<String> listError;
		listError = validateUser.validateUserInfor(userInformation);
		if(listError.size() == 0){
			//Khởi tạo session
			HttpSession session = req.getSession();
			//Khởi tạo keyUserInfor cộng vào userInfor trong session để phân biệt được userInfor nào đang được add khi bật nhiều tab Adduser
			int keySessionAddUser = Calendar.getInstance().get(Calendar.MILLISECOND);
			//Sét session cho userInfor
			session.setAttribute("userInfor"+keySessionAddUser, userInformation);
			//Chuyển hướng đến servlet AddUserConfirm
			return "redirect:" + CommonConstant.URL_PATTERN_ADD_USER_CONFIRM+"?keySessionAddUser="+keySessionAddUser;
		}
		else{//Nếu listError có phần tử lỗi
			//Sét dữ liệu mặc định cho các ô input ở màn hình ADM003
			setDataLogic(model);
			//Gửi danh sách lỗi lên màn hình ADM003
			model.addAttribute("listError", listError);
			//Gửi dữ liệu userInfor lên màn hình ADM003 để hiển thị những dữ liệu đã nhập trước.
			model.addAttribute("userInfor", userInformation);
			//Chuyển hướng đến màn hình ADM003
		}
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
		int currentYear = Common.getYearNow();
		int currentMonth = Common.getMonthNow();
		int currentDay = Common.getDayNow();
		UserInformation userInformation = new UserInformation();
		userInformation.setIBirthday(currentDay);
		userInformation.setIBirthMonth(currentMonth);
		userInformation.setIBirthYear(currentYear);
		userInformation.setIStartDay(currentDay);
		userInformation.setIStartMonth(currentMonth);
		userInformation.setIStartYear(currentYear - 1);
		userInformation.setIEndDay(currentDay);
		userInformation.setIEndMonth(currentMonth);
		userInformation.setIEndYear(currentYear);
		model.addAttribute("userInformation", userInformation);
	}
}
