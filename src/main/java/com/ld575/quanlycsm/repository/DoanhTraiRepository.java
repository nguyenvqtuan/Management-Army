package com.ld575.quanlycsm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.entity.DoanhTraiEntity;

@Repository
public interface DoanhTraiRepository extends CrudRepository<DoanhTraiEntity, Long> {
	
	public List<DoanhTraiEntity> findAll();
	
	public List<DoanhTraiEntity> findByCapDoGreaterThan(Integer level);
	
	@Query("SELECT dt from DoanhTraiEntity dt WHERE ten LIKE %?1% or tenDayDu LIKE %?1% or capDo = ?2")
	public List<DoanhTraiEntity> findByNameOrCapDo(String name, Integer level);
	
	public List<DoanhTraiEntity> findByCapDo(Integer level);
	
	public Optional<DoanhTraiEntity> findByTen(String name);

	@Query("SELECT dt from DoanhTraiEntity dt WHERE ten LIKE %?1% or tenDayDu LIKE %?1%")
	public List<DoanhTraiEntity> findByName(String name);
	
	public List<DoanhTraiEntity> findByTrucThuoc(Long trucThuoc);
	
	public List<DoanhTraiEntity> findByCapDoAndTrucThuoc(Integer capDo, Long trucThuoc);
}
