package com.ld575.quanlycsm.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chien_si_entity")
@NoArgsConstructor
@Getter
@Setter
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
	private Date ngaySinh;
	
	@Column
	private String capBac;
	
	@Temporal(TemporalType.DATE)
	private Date thoiGianNhanCapBac;
	
	@Column
	private String chucVu;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDang;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDangChinhThuc;
	
	@Column
	private String soTheDang;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDoan;
	
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
	private String hoTenme;
	
	@Column
	private int nameSinhme;
	
	@Column
	private String ngheNghiepMe;
	
	@Column
	private String boMat;
	
	@Column
	private String meMat;
	
	@Column
	private String boMeLiDi;
	
	@Column
	private String khongCoBo;
	
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
