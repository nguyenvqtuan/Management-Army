package com.ld575.quanlycsm.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	@NotEmpty(message = "Họ tên không được rỗng")
	private String hoTen;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Ngày sinh không được rỗng")
	private Date ngaySinh;
	
	@NotEmpty(message = "Cấp bậc không được rỗng")
	private String capBac;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date thoiGianNhanCapBac;
	
	@NotEmpty(message = "Cấp bậc không được rỗng")
	private String chucVu;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Ngày sinh không được rỗng")
	private Date ngayNhapNgu;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayVaoDang;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ngayVaoDangChinhThuc;
	private String soTheDang;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Ngày vào đoàn không được rỗng")
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
	
	@NotNull(message = "Trình độ không được rỗng")
	private String trinhDo;
	private String daQuaTruong;
	
	@NotNull(message = "Dân tộc không được rỗng")
	private long idDanToc;
	private String tonGiao;
	
	private String sucKhoe;
	
	private String coVo;
	private String ghiChuCoVo;
	
	@NotNull(message = "Quê quán phường/xã không được rỗng")
	private String qqPhuongXa;
	
	@NotNull(message = "Quê quán quận/huyện không được rỗng")
	private String qqQuanHuyen;
	
	@NotNull(message = "Quê quán tỉnh/thành không được rỗng")
	private String qqTinhThanh;
	
	@NotNull(message = "Nơi ở hiện tại phường/xã không được rỗng")
	private String nohnPhuongXa;
	
	@NotNull(message = "Nơi ở hiện tại quận/huyện không được rỗng")
	private String nohnQuanHuyen;
	
	@NotNull(message = "Nơi ở hiện tại tỉnh/thành không được rỗng")
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
