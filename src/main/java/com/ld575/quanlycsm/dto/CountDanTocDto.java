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
public class CountDanTocDto {

	private String qqTinhThanh;
	private String qqQuanHuyen;
	private String qqPhuongXa;
	
	private String countQqTinhThanh;
	private String countQqQuanHuyen;
	private String countQqPhuongXa;
	
	private String detail;
	
}
