package com.ld575.quanlycsm.dto;

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
public class ChienSiDto {
	
	private String hoTen;
	
	private Integer ngaySinh;
	private Integer thangSinh;
	private Integer namSinh;
	private Integer tuoi;
	
	private String qqPhuongXa;
	private String qqQuanHuyen;
	private String qqTinhThanh;
	
	private String nohnPhuongXa;
	private String nohnQuanHuyen;
	private String nohnTinhThanh;
	
	private Integer ngayVaoDoan;
	private Integer thangVaoDoan;
	private Integer namVaoDoan;
	
	private boolean dangVien;
	
	private Integer ngayVaoDang;
	private Integer thangVaoDang;
	private Integer namVaoDang;

	private String sucKhoe;
	private String trinhDo;
	private String daQuaTruong;
	
	private Long danToc;
	private String tonGiao;
	
	private String ngheNghiepBanThan;
	private Integer coMayAnhChiEm;
	private Integer conThuMayTrongNha;
		
	private String hinhXam;
	private String giuBua;
	private String nguoiYeu;
	private String hutThuoc;
	private String soTruong;
	
	private boolean boMat;
	private boolean meMat;
	private boolean boMeLiDi;
	private boolean khongCoBo;
	private String coVo;
}
