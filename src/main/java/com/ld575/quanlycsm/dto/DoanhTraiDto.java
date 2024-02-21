package com.ld575.quanlycsm.dto;

import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Tên không được rỗng")
	private String ten;
	
	@NotNull(message = "Tên đầy đủ không được rỗng")
	private String tenDayDu;
	
	private String moTa;
	
	private Long trucThuoc;
	
	private String tenDayDuTrucThuoc;
	private String strIdTrucThuoc;
	private String tenTrucThuoc;
	
	@NotNull(message = "Cấp độ không được rỗng")
	private Integer capDo;
}
