package com.ld575.quanlycsm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Builder
public class DoanhTraiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String Ten;
	
	private String TenDayDu;
	
	private String MoTa;
	
	private Long TrucThuoc;
	
	@OneToMany(mappedBy="doanhTrai")
	private List<ChienSiEntity> danhSachChienSi;
	
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date NgayTao;
}
