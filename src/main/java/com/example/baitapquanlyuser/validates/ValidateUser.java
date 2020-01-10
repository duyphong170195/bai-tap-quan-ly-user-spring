/**
 * Copyright(C) 2018 Luvina Software
 * ValidateUser.java, 20/03/2018 NguyenDuyPhong
 */

package com.example.baitapquanlyuser.validates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.baitapquanlyuser.model.UserInformation;
import com.example.baitapquanlyuser.properties.MessageByLocaleService;
import com.example.baitapquanlyuser.services.MstGroupService;
import com.example.baitapquanlyuser.services.MstJapanService;
import com.example.baitapquanlyuser.services.TblUserService;
import com.example.baitapquanlyuser.utils.Common;
import com.example.baitapquanlyuser.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * ValidateUser form ADM003 (chức năng add và update)
 * @author NguyenDuyPhong
 *
 */
@Component
public class ValidateUser {

	@Autowired
	TblUserService tblUserService;
	@Autowired
	MstGroupService mstGroupService;
	@Autowired
	MstJapanService mstJapanService;

	@Autowired
	MessageByLocaleService messageErrorProperties;

	/**
	 * Validate user lấy ra dach sách lỗi
	 * @param userInformation
	 * @return danh sách lỗi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> validateUserInfor(UserInformation userInformation) throws ClassNotFoundException, SQLException{
		ArrayList<String> listError = new ArrayList<String>();
		//Khởi tạo các lớp logic
		if(userInformation.getUserId() == 0){
			/*Trường hợp lỗi cho userName*/
			listError.addAll(validateUserName(userInformation.getUserName()));
			/*Trường hợp lỗi cho groupName*/
			listError.addAll(validateGroupName(userInformation.getGroupId()));
			/*Trường hợp lỗi của fullName*/
			listError.addAll(validateFullName(userInformation.getFullName()));
			/*Trường hợp lỗi chữ kana*/
			listError.addAll(validateFullNameKana(userInformation.getFullNameKana()));
			/*Trường hợp lỗi ngày sinh*/
			listError.addAll(validateBirthDay(userInformation.getIBirthYear(), userInformation.getIBirthMonth(), userInformation.getIBirthday()));
			/*Trường hợp lỗi của Email*/
			listError.addAll(validateEmail(userInformation.getEmail()));
			/*Trường hợp lỗi của telephone*/
			listError.addAll(validateTelephone(userInformation.getTelephone()));
			/*Trường hợp lỗi của Password*/
			listError.addAll(validatePassword(userInformation.getPassword()));
			/*Trường hợp lỗi của mật khẩu xác nhận*/
			listError.addAll(validateAuthenticatedPassword(userInformation.getPassword(), userInformation.getAuthenticatedPassword()));

			String codeLevel = userInformation.getCodeLevel();
			/*Trường hợp lỗi của trình độ tiếng nhật*/
			if(!Common.isEmpty(codeLevel)){
				//Validate trình độ tiếng nhật có tồn tại trong bảng mst_japan hay không ?
				listError.addAll(validateCodeLevel(codeLevel));
				//Trường hợp lỗi của startDate và endDate
				listError.addAll(validateStartDateAndEndDate(userInformation.getIStartYear(), userInformation.getIStartMonth(), userInformation.getIStartDay(),
															userInformation.getIEndYear(), userInformation.getIEndMonth(), userInformation.getIEndDay()));
				//Trường hợp lỗi của total
				listError.addAll(validateTotal(userInformation.getTotal()));
				//Nếu codeLevel tồn tại trong bảng MstJapan
			}

		}else{
			/*Trường hợp lỗi cho groupName*/
			listError.addAll(validateGroupName(userInformation.getGroupId()));
			/*Trường hợp lỗi của fullName*/
			listError.addAll(validateFullName(userInformation.getFullName()));
			/*Trường hợp lỗi chữ kana*/
			listError.addAll(validateFullNameKana(userInformation.getFullNameKana()));
			/*Trường hợp lỗi ngày sinh*/
			listError.addAll(validateBirthDay(userInformation.getIBirthYear(), userInformation.getIBirthMonth(), userInformation.getIBirthday()));
			/*Trường hợp lỗi của Email*/
			listError.addAll(validateUpdateEmail(userInformation.getEmail(), userInformation.getUserId()));
			/*Trường hợp lỗi của telephone*/
			listError.addAll(validateTelephone(userInformation.getTelephone()));
			/*Trường hợp lỗi của Password và AuthenticatedPassword*/
			listError.addAll(validateUpdatePassword(userInformation.getPassword(), userInformation.getAuthenticatedPassword()));

			String codeLevel = userInformation.getCodeLevel();
			/*Trường hợp lỗi của trình độ tiếng nhật*/
			if(!Common.isEmpty(codeLevel)){
				//Validate trình độ tiếng nhật có tồn tại trong bảng mst_japan hay không ?
				listError.addAll(validateCodeLevel(codeLevel));
				//Trường hợp lỗi của startDate và endDate
				listError.addAll(validateStartDateAndEndDate(userInformation.getIStartYear(), userInformation.getIStartMonth(), userInformation.getIStartDay(),
															userInformation.getIEndYear(), userInformation.getIEndMonth(), userInformation.getIEndDay()));
				//Trường hợp lỗi của total
				listError.addAll(validateTotal(userInformation.getTotal()));
				//Nếu codeLevel tồn tại trong bảng MstJapan
			}
		}
		return listError;
	}

	/**
	 * Các trường hợp bắt lỗi của userName
	 * @param userName : thuộc tính login_name trong bảng tbl_user
	 * @return Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng không có phần tử.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> validateUserName(String userName) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi cho userName*/
		if(Common.isEmpty(userName)){ //Kiểm tra đã nhập  userName chưa  ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_USER_NAME));

		}else if(Common.checkOutsize(userName, 4, 15)){ //Kiểm tra  độ dài của userName có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER007_USER_NAME));

		}else if(!Common.checkFormatByPattern(userName, CommonConstant.PATTERN_USERNAME)){ //Kiểm tra định dạng userName hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER019_USER_NAME));

		}else if(tblUserService.checkExistedLoginName(userName)){ // Kiểm tra userName đã tồn tại trong cơ sở dữ liệu hay chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER003_USER_NAME));
		}

		//Nếu có lỗi thì trả về một chuỗi lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * các trường hợp bắt lỗi của groupName
	 * @param groupId : id của bảng mst_group
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 *  Nếu không có lỗi thì trả mảng rỗng.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> validateGroupName(int groupId) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();

		/*Trường hợp lỗi cho groupName*/
		if( 0 == groupId){ // Kiểm tra đã chọn group hay chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER002_GROUP_NAME));

		}else if(!mstGroupService.checkExistedGroupName(groupId)){ //Kiểm tra nhóm này có tồn tại trong cơ sở dữ liệu không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER004_GROUP_NAME));
		}

		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về mảng rỗng.
		return listError;
	}

	/**
	 * Các trường hợp bắt lỗi của fullName
	 * @param fullName : full_name của bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả mảng rỗng.
	 */
	public ArrayList<String> validateFullName(String fullName){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của fullName*/
		if(Common.isEmpty(fullName)){ //Kiểm tra đã nhập fullName chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_FULL_NAME));

		}else if(!Common.maxLength(fullName, 255)){ // Kiểm tra độ dài fullName có hợp lệ không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER006_FULL_NAME));

		}
		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp bắt lỗi của full_name_kana
	 * @param fullNameKana : full_name_kana của bảng tbluser
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 */
	public ArrayList<String> validateFullNameKana(String fullNameKana){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi chữ kana*/
		if(!Common.isEmpty(fullNameKana)){
			if(!Common.checkKana(fullNameKana)){
				listError.add(messageErrorProperties.getData(CommonConstant.ER009_FULL_NAME_KANA));

			}else if(!Common.maxLength(fullNameKana, 255)){ // Kiểm tra độ dài fullNameKana có hợp lệ không ?
				listError.add(messageErrorProperties.getData(CommonConstant.ER006_FULL_NAME_KANA));
			}
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp bắt lỗi birthday
	 * @param year : năm sinh
	 * @param month : tháng sinh
	 * @param day : ngày sinh
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validateBirthDay(int year, int month, int day){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi ngày sinh*/
		if(!Common.checkExistedDate(year, month, day)){ //Kiểm tra xem ngày sinh có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER011_BIRTHDAY));
		}
		//Nếu có lỗi thì trả về một chuỗi lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi email
	 * @param email : thuộc tính trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String> validateEmail(String email) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của Email*/
		if(Common.isEmpty(email)){ //Kiểm tra đã nhập Email chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_EMAIL));

		}else if(!Common.maxLength(email, 255)){ //Kiểm tra độ dài của Email có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER006_EMAIL));

		}else if(!Common.checkFormatByPattern(email, CommonConstant.PATTERN_EMAIL)){ // Kiểm tra định dạng email có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER005_EMAIL));

		}else if(tblUserService.checkExistedEmail(email)){  //Kiểm tra email đã tồn tài trong cơ sở dữ liệu hay chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER003_EMAIL));

		}
		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về mảng rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi của telephone
	 * @param telephone : thuộc tính trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validateTelephone(String telephone){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của telephone*/
		if(Common.isEmpty(telephone)){ //Kiểm tra đã nhập số điện thoại chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_TELEPHONE));

		}else if(!Common.maxLength(telephone, 14)){ //Kiểm tra độ dài số điện thoại có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER006_TELEPHONE));

		}else if(!Common.checkFormatByPattern(telephone, CommonConstant.PATTERN_TELEPHONE)){ //Kiểm tra định dạng số điện thoại có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER005_TELEPHONE));
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về mảng rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi của password
	 * @param password : thuộc tính trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validatePassword(String password){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của Password*/
		if(Common.isEmpty(password)){ //Kiểm tra dã nhập password hay chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_PASSWORD));

		}else if(Common.checkOutsize(password, 5, 15)){ // Kiểm tra độ dài của password có hợp lệ không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER007_PASSWORD));

		}else if(!Common.checkOneBytePassword(password)){ //Kiểm tra check1byte cho password
			listError.add(messageErrorProperties.getData(CommonConstant.ER008_PASSWORD));

		}
		//Nếu có lỗi thì trả về một chuỗi lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi mật khẩu xác nhận
	 * @param password : thuộc tính trong bảng tbl_user
	 * @param authenticatedPassword : thuộc tính trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 */
	public ArrayList<String> validateAuthenticatedPassword(String password, String authenticatedPassword){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của mật khẩu xác nhận*/
		if(!Common.isEmpty(password)){
			if(Common.isEmpty(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận đã nhập chưa ?
				listError.add(messageErrorProperties.getData(CommonConstant.ER001_AUTHENTICATE_PASSWORD));

			}else if(!password.equals(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận và mật khẩu có giống nhau không ?
				listError.add(messageErrorProperties.getData(CommonConstant.ER017_AUTHENTICATE_PASSWORD));
			}
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi khi chọn codeLevel
	 * @param codeLevel : thuộc tính trong bảng mst_japan
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String> validateCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		if(!mstJapanService.checkExistedCodeLevel(codeLevel)){ //Nếu codelevel không tồn tại trong bảng MstJapan
			listError.add(messageErrorProperties.getData(CommonConstant.ER004_CODE_LEVEL));
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Trường hợp lỗi của startDate và endDate nếu chọn codeLevel
	 * @param startYear :năm bắt đầu
	 * @param startMonth : tháng bắt đầu
	 * @param startDay : ngày bắt đầu
	 * @param endYear : năm kết thúc
	 * @param endMonth : tháng kết thúc
	 * @param endDay : ngày kết thúc
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 */
	public ArrayList<String> validateStartDateAndEndDate(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();

		if(!Common.checkExistedDate(startYear, startMonth, startDay)){ //Kiểm tra xem startDate có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER011_START_DATE));
		}

		if(!Common.checkExistedDate(endYear, endMonth, endDay)){ //Kiểm tra xem startDate có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER012_END_DATE));
		}

		if(Common.checkExistedDate(startYear, startMonth, startDay)
				&& Common.checkExistedDate(endYear, endMonth, endDay)){ //Trường hợp startDate, endDate hợp lệ
			if(!Common.dateBiggerdate(Common.getDate(endYear, endMonth, endDay), Common.getDate(startYear, startMonth, startDay) )){
				listError.add(messageErrorProperties.getData(CommonConstant.ER012_END_DATE));
			}
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}


	/**
	 * Các trường hợp lỗi của total
	 * @param total : thuộc tính của tbl_details_user_japan
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 */
	public ArrayList<String> validateTotal(int total){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();

		String sTotal = String.valueOf(total);
		if( 0 == total){ //Trường hợp không nhập điểm
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_TOTAL));

		}else if(!Common.checkHalfSize(sTotal)){//Kiểm tra xem phải số halfsize hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER018_TOTAL));
		}
		//Nếu có lỗi thì trả về một chuỗi lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}

	/**
	 * Các trường hợp lỗi email trong update
	 * @param newEmail : email mới của user trong trường hợp update
	 * @param userId : user Id
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String> validateUpdateEmail(String newEmail, int userId) throws ClassNotFoundException, SQLException{
		//Mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của Email*/
		if(Common.isEmpty(newEmail)){ //Kiểm tra đã nhập Email chưa ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER001_EMAIL));

		}else if(!Common.maxLength(newEmail, 255)){ //Kiểm tra độ dài của Email có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER006_EMAIL));

		}else if(!Common.checkFormatByPattern(newEmail, CommonConstant.PATTERN_EMAIL)){ // Kiểm tra định dạng email có hợp lệ hay không ?
			listError.add(messageErrorProperties.getData(CommonConstant.ER005_EMAIL));

		}else if(tblUserService.checkExistedEmailExceptEmailOfUserId(userId, newEmail)){  //Kiểm tra tồn tại email ngoại trừ email của userid
			listError.add(messageErrorProperties.getData(CommonConstant.ER003_EMAIL));

		}
		return listError;
	}

	/**
	 * Trường hợp lỗi của update password và authenticatedPassword
	 * @param password :  thuộc tính password trong bảng tbl_user
	 * @param authenticatedPassword : thuộc tính authenticatedPassword trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validateUpdatePassword(String password, String authenticatedPassword){
		//Mảng lỗi
		ArrayList<String> listError = new ArrayList<>();

		if(!Common.isEmpty(password)){ //Nếu nhập password
			 if(Common.checkOutsize(password, 5, 15)){ // Kiểm tra độ dài của password có hợp lệ không ?
					listError.add(messageErrorProperties.getData(CommonConstant.ER007_PASSWORD));

			 }else if(!Common.checkOneBytePassword(password)){ //Kiểm tra check1byte cho password
					listError.add(messageErrorProperties.getData(CommonConstant.ER008_PASSWORD));
			 }
			 /*Trường hợp lỗi của mật khẩu xác nhận*/
			if(Common.isEmpty(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận đã nhập chưa ?
					listError.add(messageErrorProperties.getData(CommonConstant.ER001_AUTHENTICATE_PASSWORD));

			}else if(!password.equals(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận và mật khẩu có giống nhau không ?
					listError.add(messageErrorProperties.getData(CommonConstant.ER017_AUTHENTICATE_PASSWORD));

			}
		}
		return listError;
	}
} 
