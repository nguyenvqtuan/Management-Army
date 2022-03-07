package com.ld575.quanlycsm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChienSiInsertDto {
	private long id;
	private String trucThuoc;
	private String hoTen;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngaySinh;
	
	private String capBac;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date thoiGianNhanCapBac;
	
	private String chucVu;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayNhapNgu;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayVaoDang;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayVaoDangChinhThuc;
	private String soTheDang;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayVaoDoan;
	
	private String ngheNghiepGiaDinh;
	private int coMayAnhChiEm;
	private int conThuMayTrongNha;
	
	private String hoTenCha;
	private String ngheNghiepCha;
	private int namSinhCha;
	
	private String hoTenMe;
	private String ngheNghiepMe;
	private int namSinhMe;
	
	private String boMat;
	private String meMat;
	private String boMeLiDi;
	private String khongCoBo;
	
	private String giaDinhAnhHuongCovid;
	private String giaDinhKhoKhan;
	
	private String nguoiQuenTrongQuanDoi;
	private String boMeLaLietSiHoacQuanNhan;
	private String ngheNghiepBanThan;
	
	private String trinhDo;
	private String daQuaTruong;
	
	private long idDanToc;
	private String tonGiao;
	
	private String sucKhoe;
	
	private String coVo;
	private String ghiChuCoVo;
	
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
	private String diBar;
	private String ghiChu;
	
	private Long idDoanhTrai;
	
	private long idDaiDoi;
	private long idTrungDoi;
	private long idTieuDoi;
	
	private String strDaiDoi;
	private String strTrungDoi;
	private String strTieuDoi;
}
