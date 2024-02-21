package com.ld575.quanlycsm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class CapDoDto {

	public static final String MAPPING = "Tương đương";
	
	// Id Auto-increament
	public static int id;
	private String name;
	
	public CapDoDto(String name) {
		id++;
		this.name = name;
	}
}

