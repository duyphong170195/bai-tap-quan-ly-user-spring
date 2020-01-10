package com.example.baitapquanlyuser.model;

import com.example.baitapquanlyuser.utils.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

	@NotNull
	@JsonProperty("user_id")
	private int userId;

	@NotNull
	@JsonProperty("full_name")
	@Pattern(regexp = "[a-zA-Z]{5,255}")
	private String fullName;

	@JsonProperty("full_name_kana")
	@Pattern(regexp = "[ァ-ン]+")
	private String fullNameKana;

	@NotNull
	@JsonProperty("username")
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	private String userName;

	@NotNull
	@JsonProperty("birthday")
	private String birthday;
	private Integer iBirthday;
	private Integer iBirthMonth;
	private Integer iBirthYear;

	@NotNull
	@JsonProperty("group_id")
	private int groupId;

	@NotNull
	@JsonProperty("group_name")
	@Pattern(regexp = "[a-zA-Z0-9]{4,255}")
	private String groupName;

	@NotNull
	@JsonProperty("email")
	@Email
	@Size(min = 5, max = 255)
	private String email;

	@NotNull
	@JsonProperty("telephone")
	@Pattern(regexp = CommonConstant.PATTERN_TELEPHONE)
	@Size(min = 6, max = 15)
	private String telephone;

	@NotNull
	@JsonProperty(value = "name_level")
	@Pattern(regexp = "[a-zA-Z0-9]{4,255}")
	private String nameLevel;

	@NotNull
	@JsonProperty("password")
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	private String password;

	@NotNull
	@JsonProperty("authenticated_password")
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	private String authenticatedPassword;

	@Temporal(TemporalType.DATE)
	@JsonProperty("start_date")
	private String startDate;
	private int iStartDay;
	private int iStartMonth;
	private int iStartYear;

	@Temporal(TemporalType.DATE)
	@JsonProperty("end_date")
	private String endDate;
	private int iEndDay;
	private int iEndMonth;
	private int iEndYear;

	@JsonProperty("total")
	private Integer total;

	@JsonProperty(value = "code_level")
	private String codeLevel;


	public UserInformation(int userId, String fullName, String birthday, String groupName, String email, String telephone,
						   String nameLevel, String endDate, Integer total) {
		this.userId = userId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.groupName = groupName;
		this.email = email;
		this.telephone = telephone;
		this.nameLevel = nameLevel;
		this.endDate = endDate;
		this.total = total;
	}
}
