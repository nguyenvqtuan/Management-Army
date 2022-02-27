package com.ld575.quanlycsm.entity;

import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chien_si_entity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChienSiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="doanh_trai_id", nullable = false)
	private DoanhTraiEntity doanhTrai;
	
	@Column
	private String hoTen;
	
	@Temporal(TemporalType.DATE)
	private LocalDate ngaySinh;
	
	@Column
	private String capBac;
	
	@Temporal(TemporalType.DATE)
	private LocalDate thoiGianNhanCapBac;
	
	@Column
	private String chucVu;
	
	@Temporal(TemporalType.DATE)
	private LocalDate ngayNhapNgu;
	
	@Temporal(TemporalType.DATE)
	private LocalDate ngayVaoDang;
	
	@Temporal(TemporalType.DATE)
	private LocalDate ngayVaoDangChinhThuc;
	
	@Column
	private String soTheDang;
	
	@Temporal(TemporalType.DATE)
	private LocalDate ngayVaoDoan;
	
	@Column
	private String ngheNghiepGiaDinh;
	
	@Column
	private int coMayAnhChiEm;
	
	@Column
	private int conThuMayTrongNha;
	
	@Column
	private String hoTenCha;
	
	@Column
	private int namSinhCha;
	
	@Column
	private String ngheNghiepCha;
	
	@Column
	private String hoTenMe;
	
	@Column
	private int namSinhMe;
	
	@Column
	private String ngheNghiepMe;
	
	@Column
	private boolean boMat;
	
	@Column
	private boolean meMat;
	
	@Column
	private boolean boMeLiDi;
	
	@Column
	private boolean khongCoBo;
	
	@Column
	private boolean giaDinhAnhHuongCovid;
	
	@Column
	private boolean giaDinhKhoKhan;
	
	@Column
	private String nguoiQuenTrongQuanDoi;
	
	@Column
	private String boMeLaLietSiHoacQuanNhan;
	
	@Column
	private String ngheNghiepBanThan;
	
	@Column
	private String trinhDo;
	
	@Column
	private String daQuaTruong;
	
	@ManyToOne
	@JoinColumn(name="dan_toc_id", nullable = false)
	private DanTocEntity danToc;

	@Column
	private String tonGiao;
	
	@Column
	private String sucKhoe;
	
	@Column(columnDefinition = "char(1) COMMENT \"c: chua dang ky ket hon, r: da dang ky ket hon\"")
	private char coVo;
	
	@Column
	private String ghiChuCoVo;
	
	@Column
	private String qqPhuongXa;
	
	@Column
	private String qqQuanHuyen;
	
	@Column
	private String qqTinhThanh;
	
	@Column
	private String nohnPhuongXa;
	
	@Column
	private String nohnQuanHuyen;
	
	@Column
	private String nohnTinhThanh;
	
	@Column
	private String hinhXam;
	
	@Column
	private String giuBua;
	
	@Column
	private String nguoiYeu;
	
	@Column
	private String hutThuoc;
	
	@Column
	private String soTruong;
	
	@Column
	private String ghiChu;
}
