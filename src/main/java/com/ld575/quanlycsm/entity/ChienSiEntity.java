package com.ld575.quanlycsm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
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
	
	@Column(columnDefinition="c: chưa đăng ký kết hôn, r: đã đăng ký kết hôn")
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
