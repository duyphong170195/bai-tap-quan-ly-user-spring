package com.example.baitapquanlyuser.model;

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
	private int userId;
	
	@NotNull
	private String fullName;
	
	private String fullNameKana;
	
	@NotNull
	private String userName;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date birthday;
	
	@NotNull
	private int groupId;
	
	@NotNull
	private String groupName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String telephone;
	
	@NotNull
	private String nameLevel;
	
	@NotNull
	private String password;
	
	@NotNull
	private String authenticatedPassword;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private Integer total;
	
	private String sTotal;
	
	private String codeLevel;
	
	
	public UserInformation(int userId, String fullName, Date birthday, String groupName, String email, String telephone,
			String nameLevel, Date endDate, Integer total) {
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
