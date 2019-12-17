package com.example.baitapquanlyuser.entities;

import com.example.baitapquanlyuser.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity TblUser
 */
@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
public class TblUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	@NotNull
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	@Valid
	@NotNull
	private MstGroup mstGroup;
	
	@Column(name = "login_name", length = 15, nullable = false)
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	@NotNull
	private String loginName;
	
	@Column(name = "password", length = 50, nullable = false)
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	@NotNull
	private String password;
	
	@Column(name = "full_name", length = 255, nullable = false)
	@Pattern(regexp = "[a-zA-Z]{5,255}")
	@NotNull
	private String fullName;
	
	@Column(name = "full_name_kana", length = 255)
	@Pattern(regexp = "[ァ-ン]+")
	private String fullNameKana;
	
	@Column(name = "email", length = 255, nullable = false)
	@Email
	@Size(min = 5, max = 255)
	@NotNull
	private String email;
	
	@Column(name = "tel", length = 15, nullable = false)
	@Pattern(regexp = CommonConstant.PATTERN_TELEPHONE)
	@Size(min = 6, max = 15)
	@NotNull
	private String telephone;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = false)
	@NotNull
	private Date birthday;

	@OneToMany(mappedBy="tblUser")
	private Set<UserRole> userRole = new HashSet<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public MstGroup getMstGroup() {
		return mstGroup;
	}

	public void setMstGroup(MstGroup mstGroup) {
		this.mstGroup = mstGroup;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullNameKana() {
		return fullNameKana;
	}

	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
