package com.ld575.quanlycsm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;

@Repository
public interface ChienSiRepository extends JpaRepository<ChienSiEntity, Long> {
	Optional<ChienSiEntity> findById(Long id);
	
	@Query("SELECT cs FROM ChienSiEntity cs WHERE (cs.hoTen LIKE %:#{#chiensi.hoTen}% OR cs.hoTen is NULL)"
			+ "AND (DAY(cs.ngaySinh) = :#{#chiensi.ngaySinh} OR cs.ngaySinh is NULL)"
			+ "AND (MONTH(cs.ngaySinh) = :#{#chiensi.thangSinh} OR cs.ngaySinh is NULL")
	List<ChienSiEntity> findByCondition(@Param("chiensi") ChienSiDto chiensi);
}
