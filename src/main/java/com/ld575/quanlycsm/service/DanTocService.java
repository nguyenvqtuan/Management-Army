package com.ld575.quanlycsm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.DanTocDto;
import com.ld575.quanlycsm.dto.DanTocMessageDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.repository.DanTocRepository;

@Service
public class DanTocService {

	@Autowired
	public DanTocRepository danTocRepository;
	
	public List<DanTocEntity> findAll() {
		return danTocRepository.findAll();
	}
	
	public DanTocMessageDto save(DanTocDto danTocDto) {
		// Check duplicate ten
		if (findByTen(danTocDto.getTen()).isPresent()) {
			return DanTocMessageDto.builder().name(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
		}
		DanTocEntity danToc = DanTocEntity.builder()
			.ten(danTocDto.getTen())
			.moTa(danTocDto.getMoTa())
			.build();
		if (danTocDto.getId() != null) {
			danToc.setId(danTocDto.getId());
		}
		danTocRepository.save(danToc);
		return DanTocMessageDto.builder().name(Flag.SUCCESS.name()).type(Flag.SUCCESS).build();
	}
	
	public Optional<DanTocEntity> findById(Long id) {
		return danTocRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		danTocRepository.deleteById(id);
	}

	public Optional<DanTocEntity> findByTen(String name) {
		return danTocRepository.findByTen(name);
	}
	
	public List<DanTocEntity> findByTenContaining(String name) {
		return danTocRepository.findByTenContaining(name);
	}
}
