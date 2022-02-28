package com.ld575.quanlycsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.repository.ChienSiRepository;

@Service
public class ChienSiService {

	@Autowired
	private ChienSiRepository chienSiRepository;
	
	public ChienSiEntity save(ChienSiEntity chienSiEntity) {
		return chienSiRepository.save(chienSiEntity);
	}
	
	public Iterable<ChienSiEntity> findAll() {
		return chienSiRepository.findAll();
	}
}
