package com.ld575.quanlycsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.entity.DoanhTraiEntity;

@Repository
public interface DoanhTraiRepository extends CrudRepository<DoanhTraiEntity, Long> {
}
