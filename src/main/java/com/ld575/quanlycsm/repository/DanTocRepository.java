package com.ld575.quanlycsm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.entity.DanTocEntity;

@Repository
public interface DanTocRepository extends CrudRepository<DanTocEntity, Long>{

	public List<DanTocEntity> findAll();
}
