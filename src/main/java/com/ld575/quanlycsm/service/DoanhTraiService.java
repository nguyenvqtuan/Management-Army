package com.ld575.quanlycsm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.CapDoDto;
import com.ld575.quanlycsm.dto.CapDoEnum;
import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.repository.DoanhTraiRepository;

@Service
public class DoanhTraiService {
	
	@Autowired
	public DoanhTraiRepository doanhTraiRepository;
	
	public List<DoanhTraiEntity> findAll() {
		return doanhTraiRepository.findAll();
	}
	
	public void save(DoanhTraiDto doanhTraiDto) {
		
		String strIdTrucThuoc = doanhTraiDto.getStrIdTrucThuoc().isEmpty() ? "1" : doanhTraiDto.getStrIdTrucThuoc();
		String tenTrucThuoc = doanhTraiDto.getTenTrucThuoc().isEmpty() ? CapDoEnum.BQP.name() : doanhTraiDto.getStrIdTrucThuoc();
		String tenDayDuTrucThuoc = doanhTraiDto.getTenDayDuTrucThuoc().isEmpty() ? CapDoEnum.BQP.name : doanhTraiDto.getStrIdTrucThuoc();
		
		DoanhTraiEntity doanhTrai = DoanhTraiEntity.builder()
			.ten(doanhTraiDto.getTen())
			.tenDayDu(doanhTraiDto.getTenDayDu())
			.trucThuoc(doanhTraiDto.getTrucThuoc())
			.capDo(doanhTraiDto.getCapDo())
			.strIdTrucThuoc(strIdTrucThuoc)
			.tenTrucThuoc(tenTrucThuoc)
			.tenDayDuTrucThuoc(tenDayDuTrucThuoc)
			.build();
		if (doanhTraiDto.getId() != null) {
			doanhTrai.setId(doanhTraiDto.getId());
		}
		doanhTraiRepository.save(doanhTrai);
	}
	
	public Optional<DoanhTraiEntity> findById(Long id) {
		return doanhTraiRepository.findById(id);
	}
	
	public Optional<DoanhTraiEntity> findByTen(String ten) {
		return doanhTraiRepository.findByTen(ten);
	}
	
	public List<DoanhTraiEntity> findByTrucThuoc(Long id) {
		return doanhTraiRepository.findByTrucThuoc(id);
	}
	
	public List<DoanhTraiEntity> findByCapDoAndTrucThuoc(Integer capDo, Long trucThuoc) {
		return doanhTraiRepository.findByCapDoAndTrucThuoc(capDo, trucThuoc);
	}
	
	public void deleteById(Long id) {
		doanhTraiRepository.deleteById(id);
	}
	
	public List<DoanhTraiEntity> findByLevelGreaterThan(Integer level) {
		return doanhTraiRepository.findByCapDoGreaterThan(level);
	}
	
	public List<DoanhTraiEntity> findByCapDo(Integer level) {
		return doanhTraiRepository.findByCapDo(level);
	}

	public List<DoanhTraiEntity> search(String name, Integer level) {
		List<DoanhTraiEntity> res;
		if (!name.isEmpty() && level != 0) {
			res = doanhTraiRepository.findByNameAndCapDo(name, level);
		}
		else if (!name.isEmpty()) {
			res = doanhTraiRepository.findByName(name);
		}
		else if (level != 0) {
			res = doanhTraiRepository.findByCapDo(level);
		}
		else {
			res = doanhTraiRepository.findAll();
		}
		
		return res;
	}
	
	public List<CapDoDto> getLevel() {
		int i = 0;
		List<CapDoDto> res = new ArrayList<>();
		res.add(new CapDoDto(i, "-"));
		for (CapDoEnum e : CapDoEnum.values()) {
			res.add(new CapDoDto(++i, e + " - " + CapDoDto.MAPPING));
		}
		
		// Remove BQP (Default value can't exchange)
		res.remove(res.size() - 1);
		return res;
	}
}
