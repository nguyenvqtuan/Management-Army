package com.ld575.quanlycsm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.repository.DoanhTraiRepository;

@Service
public class DoanhTraiService {
	
	@Autowired
	public DoanhTraiRepository doanhTraiRepository;
	
	public Iterable<DoanhTraiEntity> findAll() {
		return doanhTraiRepository.findAll();
	}
	
	public void save(DoanhTraiDto doanhTraiDto) {
		DoanhTraiEntity doanhTrai = DoanhTraiEntity.builder()
			.ten(doanhTraiDto.getTen())
			.tenDayDu(doanhTraiDto.getTenDayDu())
			.trucThuoc(doanhTraiDto.getTrucThuoc())
			.build();
		if (doanhTraiDto.getId() != null) {
			doanhTrai.setId(doanhTraiDto.getId());
		}
		doanhTraiRepository.save(doanhTrai);
	}
	
	public Optional<DoanhTraiEntity> findById(Long id) {
		return doanhTraiRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		doanhTraiRepository.deleteById(id);
	}
}
