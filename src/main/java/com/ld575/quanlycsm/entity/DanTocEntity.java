package com.ld575.quanlycsm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "dan_toc_entity")
public class DanTocEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ten;
	
	private String moTa;
	
	@OneToMany(mappedBy="danToc")
	private List<ChienSiEntity> danhSachChienSi;
	
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date ngayTao;
}
