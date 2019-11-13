package com.example.baitapquanlyuser.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "mst_group")
@AllArgsConstructor
@NoArgsConstructor
public class MstGroup implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id", nullable = false)
	@NotNull
	private int groupId;
	
	@Column(name = "group_name", length = 255, nullable = false)
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{4,255}")
	private String groupName;
	
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
