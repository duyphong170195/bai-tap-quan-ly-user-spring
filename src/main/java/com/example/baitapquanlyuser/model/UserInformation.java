package com.example.baitapquanlyuser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserInformation {
	
	@NotNull
	@JsonProperty("user_id")
	private int userId;
	
	@NotNull
	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("full_name_kana")
	private String fullNameKana;
	
	@NotNull
	@JsonProperty("username")
	private String userName;
	
	@NotNull
	@JsonProperty("birthday")
	private String birthday;
	
	@NotNull
	@JsonProperty("group_id")
	private int groupId;
	
	@NotNull
	@JsonProperty("group_name")
	private String groupName;
	
	@NotNull
	@JsonProperty("email")
	private String email;
	
	@NotNull
	@JsonProperty("telephone")
	private String telephone;
	
	@NotNull
	@JsonProperty(value = "name_level")
	private String nameLevel;
	
	@NotNull
	@JsonProperty("password")
	private String password;
	
	@NotNull
	@JsonProperty("authenticated_password")
	private String authenticatedPassword;
	
	@Temporal(TemporalType.DATE)
	@JsonProperty("start_date")
	private String startDate;
	
	@Temporal(TemporalType.DATE)
	@JsonProperty("end_date")
	private String endDate;
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
