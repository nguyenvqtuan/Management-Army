package com.ld575.quanlycsm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;

@Repository
public class CustomChienSiRepository {

	@Autowired
	private EntityManager entityManager;
	
	public List<ChienSiEntity> findByCondition(ChienSiDto chienSiDto) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.* ")
		  .append("FROM ")
		  .append(getFromQuery())
		  .append("WHERE ")
		  .append(getWhereQuery());
		
//		SELECT cs FROM ChienSiEntity cs WHERE (cs.hoTen LIKE %:#{#chiensi.hoTen}% OR cs.hoTen is NULL)"
//				+ "AND (DAY(cs.ngaySinh) = :#{#chiensi.ngaySinh} OR cs.ngaySinh is NULL)"
//				+ "AND (MONTH(cs.ngaySinh) = :#{#chiensi.thangSinh} OR cs.ngaySinh is NULL
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("hoTen", chienSiDto.getHoTen());
		List<ChienSiEntity> res = (List<ChienSiEntity>) query.getResultList();
		return res;
	}
	
	private String getFromQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("chien_si_entity cs");
		return sb.toString();
	}
	
	private String getWhereQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(getConditionHoTen());
		return sb.toString();
	}
	
	private String getConditionHoTen() {
		StringBuilder res = new StringBuilder();
		res.append("cs.hoTen LIKE :hoTen OR cs.hoTen is NULL");
		return res.toString();
	}
}
