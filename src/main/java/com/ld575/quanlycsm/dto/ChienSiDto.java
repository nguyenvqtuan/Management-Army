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
	public String hoten;
	
	private String ngaySinh;
	private String thangSinh;
	private String namSinh;
	private String tuoi;
	
	private String qqPhuongXa;
	private String qqQuanHuyen;
	private String qqTinhThanh;
	
	private String nohnPhuongXa;
	private String nohnQuanHuyen;
	private String nohnTinhThanh;
	
	
	private String ngayVaoDoan;
	private String thangVaoDoan;
	private String namVaoDoan;
	
	private boolean dangVien;

	private String danToc;
	private String sucKhoe;
	private String trinhDo;
	
	private DanTocDto danTocDto;
	private DoanhTraiDto doanhTraiDto;
}
