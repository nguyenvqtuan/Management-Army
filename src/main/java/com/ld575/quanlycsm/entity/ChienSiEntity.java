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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Builder
@Table(name = "chien_si_entity")
public class ChienSiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="doanh_trai_id", nullable = false)
	private DoanhTraiEntity doanhTrai;
	
	private String hoTen;
	
	@Temporal(TemporalType.DATE)
	private Date NgaySinh;
	
	private String CapBac;
	
	@Temporal(TemporalType.DATE)
	private Date ThoiGianNhanCapBac;
	
	private String chucVu;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDang;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDangChinhThuc;
	
	private String soTheDang;
	
	@Temporal(TemporalType.DATE)
	private Date ngayVaoDoan;
	
	private String ngheNghiepGiaDinh;
	
	private int coMayAnhChiEm;
	
	private int conThuMayTrongNha;
	
	private String hoTenCha;
	private int namSinhCha;
	private String ngheNghiepCha;
	
	private String hoTenme;
	private int nameSinhme;
	private String ngheNghiepMe;
	
	private String boMat;
	private String meMat;
	private String boMeLiDi;
	private String khongCoBo;
	
	private boolean giaDinhAnhHuongCovid;
	
	private boolean giaDinhKhoKhan;
	
	private String nguoiQuenTrongQuanDoi;
	
	private String boMeLaLietSiHoacQuanNhan;
	
	private String ngheNghiepBanThan;
	private String trinhDo;
	private String daQuaTruong;
	
	@ManyToOne
	@JoinColumn(name="dan_toc_id", nullable = false)
	private DanTocEntity danToc;
	
	private String tonGiao;
	
	private String sucKhoe;
	
	private char coVo;
	
	private String qqPhuongXa;
	private String qqQuanHuyen;
	private String qqTinhThanh;
	
	private String nohnPhuongXa;
	private String nohnQuanHuyen;
	private String nohnTinhThanh;
	
	private String hinhXam;
	
	private String giuBua;
	
	private String nguoiYeu;
	
	private String hutThuoc;
	private String soTruong;
	
	private String ghiChu;
	
	
}
