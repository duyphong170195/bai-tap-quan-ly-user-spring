package com.example.baitapquanlyuser.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "mst_japan")
@AllArgsConstructor
@NoArgsConstructor
public class MstJapan {
	
	@Id
	@Column(name = "code_level", length = 15, nullable = false)
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{2,15}")
	private String codeLevel;
	
	@Column(name = "name_level", length = 255, nullable = false)
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{4,255}")
	private String nameLevel;
	
	
	public String getCodeLevel() {
		return codeLevel;
	}
	
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	
	public String getNameLevel() {
		return nameLevel;
	}
	
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
}
