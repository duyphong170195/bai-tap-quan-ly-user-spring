package com.example.baitapquanlyuser.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TblUser {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	@NotNull
	private int userId;
	@ManyToOne
	@JoinColumn(name = "group_id")
	@Valid
	@NotNull
	private MstGroup mstGroup;
	@Column(name = "login_name", length= 15, nullable = false)
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	@NotNull
	private String loginName;
	@Column(name = "password", length= 50, nullable = false)
	@NotNull
	@Pattern(regexp = "[a-zA-Z_][a-zA-Z0-9_]{4,15}")
	private String password;
	@Column(name = "full_name", length= 255, nullable = false)
	private String fullName;
	@Column(name = "full_name_kana", length= 255)
	private String fullNameKana;
	@Column(name = "email", length= 255, nullable = false)
	private String email;
	@Column(name = "tel", length= 15, nullable = false)
	private String telephone;
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = false)
	private Date birthday;
}
