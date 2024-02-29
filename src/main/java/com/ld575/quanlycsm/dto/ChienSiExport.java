package com.ld575.quanlycsm.dto;

import java.util.List;

import com.ld575.quanlycsm.entity.ChienSiEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChienSiExport {

	private String tenDayDu;
	private long soLuong;
	private List<ChienSiEntity> listChienSi;
}
