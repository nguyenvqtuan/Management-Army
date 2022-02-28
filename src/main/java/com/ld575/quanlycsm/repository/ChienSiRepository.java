package com.ld575.quanlycsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.entity.ChienSiEntity;

@Repository
public interface ChienSiRepository extends JpaRepository<ChienSiEntity, Long> {
}