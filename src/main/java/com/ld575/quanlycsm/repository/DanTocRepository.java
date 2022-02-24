package com.ld575.quanlycsm.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.entity.DanTocEntity;

@Repository
public interface DanTocRepository extends CrudRepository<DanTocEntity, Long>{
}
