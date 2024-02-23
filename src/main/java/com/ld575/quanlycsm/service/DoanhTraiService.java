package com.ld575.quanlycsm.service;

import java.util.List;
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
	
	public List<DoanhTraiEntity> findAll() {
		return doanhTraiRepository.findAll();
	}
	
	public void save(DoanhTraiDto doanhTraiDto) {
		String[] arr = doanhTraiDto.getTenTrucThuoc().split("-");
		String daiDoi = "";
		String trungDoi = "";
		if (arr.length == 2) {
			daiDoi = arr[0];
		} else if (arr.length == 3) {
			daiDoi = arr[1];
			trungDoi = arr[0];
		}
		DoanhTraiEntity doanhTrai = DoanhTraiEntity.builder()
			.ten(doanhTraiDto.getTen())
			.tenDayDu(doanhTraiDto.getTenDayDu())
			.trucThuoc(doanhTraiDto.getTrucThuoc())
			.capDo(doanhTraiDto.getCapDo())
			.strIdTrucThuoc(doanhTraiDto.getStrIdTrucThuoc())
			.tenTrucThuoc(doanhTraiDto.getTenTrucThuoc())
			.trucThuocTrungDoi(trungDoi)
			.trucThuocDaiDoi(daiDoi)
			.tenDayDuTrucThuoc(doanhTraiDto.getTenDayDuTrucThuoc())
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

	public List<DoanhTraiEntity> findByNameOrCapDo(String name, Integer level) {
		return doanhTraiRepository.findByNameOrCapDo(name, level);
	}

	public List<DoanhTraiEntity> search(String name, Integer level) {
		List<DoanhTraiEntity> res;
		if (!name.isEmpty() && level != 0) {
			res = doanhTraiRepository.findByNameOrCapDo(name, level);
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
}
