package com.ld575.quanlycsm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doanh_trai_entity")
@NoArgsConstructor
@Getter
@Setter
public class DoanhTraiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ten;
	
	@Column
	private String tenDayDu;
	
	@Column
	private String moTa;
	
	@Column
	private Long trucThuoc;
	
	@OneToMany(mappedBy="doanhTrai")
	private List<ChienSiEntity> danhSachChienSi;
	
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date NgayTao;
}
