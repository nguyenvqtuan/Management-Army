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
public class DanTocDto {

	private Long id;
	
	private String ten;
	
	private String moTa;
}
