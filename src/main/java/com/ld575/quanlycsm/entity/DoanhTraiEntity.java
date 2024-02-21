package com.ld575.quanlycsm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doanh_trai_entity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

	@Column
	private String strIdTrucThuoc;
	
	@Column
	private String tenTrucThuoc;

	@Column
	private String tenDayDuTrucThuoc;
	
	@Column 
	private String trucThuocDaiDoi;
	
	@Column 
	private String trucThuocTrungDoi;
	
	@Column(columnDefinition = "integer COMMENT \"1: Cấp tiểu đội, 2: Cấp trung đội, 3: Cấp đại đội, 4: Cấp tiểu đoàn\"")
	private Integer capDo;
	
	@OneToMany(mappedBy="doanhTrai", cascade=CascadeType.ALL)
	private List<ChienSiEntity> danhSachChienSi;
	
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date NgayTao;
}
