package com.example.baitapquanlyuser.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_user_japan")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TblDetailUserJapan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int detailUserJapanId;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@Valid
	@NotNull
	private TblUser tblUser;
	
	@OneToOne
	@JoinColumn(name = "code_level")
	@Valid
	private MstJapan mstJapan;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@Column(name = "total", nullable = false)
	private int total;
}
