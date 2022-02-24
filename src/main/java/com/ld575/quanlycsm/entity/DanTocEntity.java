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
@Table(name = "dan_toc_entity")
@NoArgsConstructor
@Getter
@Setter
public class DanTocEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ten;
	
	@Column
	private String moTa;
	
	@OneToMany(mappedBy="danToc")
	private List<ChienSiEntity> danhSachChienSi;
	
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date ngayTao;
}
