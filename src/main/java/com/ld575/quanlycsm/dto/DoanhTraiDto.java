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
public class DoanhTraiDto {

	private Long id;
	
	private String ten;
	
	private String tenDayDu;
	
	private String moTa;
	
	private Long trucThuoc;
	
	private String tenDayDuTrucThuoc;
	
	private String tenTrucThuoc;
	
	private Integer capDo;
}
