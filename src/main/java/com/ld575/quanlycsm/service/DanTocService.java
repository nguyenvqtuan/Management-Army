package com.ld575.quanlycsm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.DanTocDto;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.repository.DanTocRepository;

@Service
public class DanTocService {

	@Autowired
	public DanTocRepository danTocRepository;
	
	public Iterable<DanTocEntity> findAll() {
		return danTocRepository.findAll();
	}
	
	public void save(DanTocDto danTocDto) {
		DanTocEntity danToc = DanTocEntity.builder()
			.ten(danTocDto.getTen())
			.moTa(danTocDto.getMoTa())
			.build();
		if (danTocDto.getId() != null) {
			danToc.setId(danTocDto.getId());
		}
		danTocRepository.save(danToc);
	}
	
	public Optional<DanTocEntity> findById(Long id) {
		return danTocRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		danTocRepository.deleteById(id);
	}
}
