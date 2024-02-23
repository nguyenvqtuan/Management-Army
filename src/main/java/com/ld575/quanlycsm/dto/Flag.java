package com.ld575.quanlycsm.dto;

public enum Flag {
	SUCCESS("Thành công"), FAILED("Thất bại");
	public String name;
	Flag(String name) {
		this.name = name;
	}
}
