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
public class CountChienSiDto {

	private String ten;
	
	private long amount;
	
	private String detail;
	
	// TODO Change to other hard code
	private String daiDoi;
	private String trungDoi;
	private String tieuDoi;
}
