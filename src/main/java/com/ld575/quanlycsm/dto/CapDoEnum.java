package com.ld575.quanlycsm.dto;

public enum CapDoEnum{
	A("Tiểu đội"), B("Trung đội"), C("Đại đội"), D("Tiểu đoàn"), E("Trung đoàn"), 
	F("Sư đoàn"), G("Quân khu"), BQP("Bộ Quốc Phòng");
	String name;
	CapDoEnum(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
