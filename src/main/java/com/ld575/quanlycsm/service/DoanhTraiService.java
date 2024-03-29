package com.ld575.quanlycsm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.CapDoDto;
import com.ld575.quanlycsm.dto.CapDoEnum;
import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.dto.MessageDto;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.repository.DoanhTraiRepository;

@Service
public class DoanhTraiService {
	
	@Autowired
	public DoanhTraiRepository doanhTraiRepository;
	
	public List<DoanhTraiEntity> findAll() {
		return doanhTraiRepository.findAll();
	}
	
	public MessageDto save(DoanhTraiDto doanhTraiDto) {
		
		// Check duplicate by ten && ten-truc-thuoc
		List<DoanhTraiEntity> doanhTraiByTen = findByTen(doanhTraiDto.getTen());
		List<DoanhTraiEntity> doanhTraiByTenTrucThuoc = findByTenTrucThuoc(doanhTraiDto.getTenTrucThuoc());
		if (doanhTraiDto.getId() != null) {
			Optional<DoanhTraiEntity> doanhTraiById = findById(doanhTraiDto.getId());
			if (doanhTraiById.isPresent() & existsRecord(doanhTraiDto, doanhTraiByTen, doanhTraiByTenTrucThuoc)) {
				return MessageDto.builder().message(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
			}
		}
		
		if (doanhTraiDto.getId()  == null && doanhTraiByTen.size() > 0 
				&& doanhTraiByTenTrucThuoc.size() > 0) {
			return MessageDto.builder().message(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
		}	
				
		String strIdTrucThuoc = doanhTraiDto.getStrIdTrucThuoc().isEmpty() ? "1" : doanhTraiDto.getStrIdTrucThuoc();
		String tenTrucThuoc = doanhTraiDto.getTenTrucThuoc().isEmpty() ? CapDoEnum.BQP.name() : doanhTraiDto.getTenTrucThuoc();
		String tenDayDuTrucThuoc = doanhTraiDto.getTenDayDuTrucThuoc().isEmpty() ? CapDoEnum.BQP.name : doanhTraiDto.getTenDayDuTrucThuoc();
		
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
		return MessageDto.builder().message(Flag.SUCCESS.name).type(Flag.SUCCESS).build();
	}
	
	public Optional<DoanhTraiEntity> findById(Long id) {
		return doanhTraiRepository.findById((long) id);
	}
	
	public List<DoanhTraiEntity> findByTrucThuoc(Integer id) {
		return doanhTraiRepository.findByTrucThuoc(id);
	}
	
	public List<DoanhTraiEntity> findByCapDoAndTrucThuoc(Integer capDo, Long trucThuoc) {
		return doanhTraiRepository.findByCapDoAndTrucThuoc(capDo, trucThuoc);
	}
	
	public List<DoanhTraiEntity> findByTen(String ten) {
		return doanhTraiRepository.findByTen(ten);
	}
	
	public List<DoanhTraiEntity> findByTenTrucThuoc(String tenTrucThuoc) {
		return doanhTraiRepository.findByTenTrucThuoc(tenTrucThuoc);
	}
	
	public MessageDto deleteById(DoanhTraiEntity doanhTraiEntity) {
		
		Long id = doanhTraiEntity.getId();
		//  Check relationship (ensure not children)
		List<DoanhTraiEntity> strIdTrucThuoc = findByStrIdTrucThuocContaining(id);
		if (strIdTrucThuoc.size() > 0) {
			return MessageDto.builder().message(Flag.FAILED.name + ". Cần phải xóa hết tất cả quan hệ trong doanh trại.").type(Flag.FAILED).build();
		}
		
		doanhTraiRepository.deleteById(id);
		return MessageDto.builder().message(Flag.SUCCESS.name).type(Flag.SUCCESS).build();
	}
	
	public List<DoanhTraiEntity> findByLevelGreaterThan(Long level) {
		return doanhTraiRepository.findByCapDoGreaterThan(level);
	}
	
	public List<DoanhTraiEntity> findByCapDo(Integer level) {
		return doanhTraiRepository.findByCapDo(level);
	}
	
	public List<DoanhTraiEntity> findByStrIdTrucThuocContaining(Long id) {
		return doanhTraiRepository.findByStrIdTrucThuocContaining(id);
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
		res.add(new CapDoDto(i, "-", ""));
		for (CapDoEnum e : CapDoEnum.values()) {
			res.add(new CapDoDto(++i, e + " - " + CapDoDto.MAPPING, ""));
		}
		
		// Remove BQP (Default value can't exchange)
		res.remove(res.size() - 1);
		return res;
	}
	
	@PostConstruct
	public void saveDefault() {
		DoanhTraiEntity doanhTrai = DoanhTraiEntity.builder()
				.ten(CapDoEnum.BQP.name())
				.tenDayDu(CapDoEnum.BQP.name)
				.capDo(8)
				.build();
		if (findByTen(CapDoEnum.BQP.name()).size() == 0) {
			doanhTraiRepository.save(doanhTrai);	
		}
	}
	
	private boolean existsRecord(DoanhTraiDto doanhTraiById, List<DoanhTraiEntity> listDoanhTraiByTen, 
			List<DoanhTraiEntity> listDoanhTraiByTenTrucThuoc) {
		boolean existsByTen = false;
		boolean existsByTenTrucThuoc = false;
		for (DoanhTraiEntity doanhTrai : listDoanhTraiByTen) {
			if (doanhTrai.getTen().equals(doanhTraiById.getTen()) && doanhTrai.getId() != doanhTraiById.getId()) {
				existsByTen = true;
				break;
			}
		}
		
		for (DoanhTraiEntity doanhTrai : listDoanhTraiByTenTrucThuoc) {
			if (doanhTrai.getTenTrucThuoc().equals(doanhTraiById.getTenTrucThuoc()) && doanhTrai.getId() != doanhTraiById.getId()) {
				existsByTenTrucThuoc = true;
				break;
			}
		}
		
		return existsByTen && existsByTenTrucThuoc;
	}

	public Optional<DoanhTraiEntity> findByTenAndTenTrucThuoc(String ten, String tenTrucThuoc) {
		return doanhTraiRepository.findByTenAndTenTrucThuoc(ten, tenTrucThuoc);
	}
}
