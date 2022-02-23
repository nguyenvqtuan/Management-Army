package com.ld575.quanlycsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.repository.DanTocRepository;

@Service
public class DanTocService {

	@Autowired
	public DanTocRepository danTocRepository;
	
	public List<DanTocEntity> findAll() {
		return danTocRepository.findAll();
	}
}
