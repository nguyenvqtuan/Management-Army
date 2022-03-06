package com.ld575.quanlycsm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.CountChienSiDto;

@Repository
public class ExportChienSiRepository {

	@Autowired
	private EntityManager entityManager;
	
	public CountChienSiDto countSoldier() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(cs.id) FROM ChienSiEntity cs"); 
		Query query = entityManager.createQuery(sql.toString());
		Object res = query.getSingleResult();
		return CountChienSiDto.builder().amount(((Number) res).longValue()).build();
	}
	
	public List<CountChienSiDto> countQqTinhThanh() {
		Query query = entityManager.createQuery(queryCountQqTinhThanh());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countQqQuanHuyen(String qqQuanHuyen) {
		Query query = entityManager.createQuery(queryCountQqQuanHuyen());
		setParamsQuanHuyen(query, qqQuanHuyen);
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countQqPhuongXa(String qqTinhThanh, String qqQuanHuyen) {
		Query query = entityManager.createQuery(queryCountQqPhuongXa());
		setParamsPhuongXa(query, qqTinhThanh, qqQuanHuyen);
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countDanToc() {
		Query query = entityManager.createQuery(queryCountDanToc());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countTonGiao() {
		Query query = entityManager.createQuery(queryCountTonGiao());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	private String queryCountQqTinhThanh() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.qqTinhThanh, count(cs.qqTinhThanh) as countTinhThanh ")
		.append("FROM ChienSiEntity cs ")
		.append("GROUP BY cs.qqTinhThanh ");
		return sql.toString();
	}
	
	private String queryCountQqQuanHuyen() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.qqQuanHuyen, count(cs.qqQuanHuyen) as countQuanHuyen ")
		.append("FROM ChienSiEntity cs ")
		.append("WHERE cs.qqTinhThanh = :qqTinhThanh ")
		.append("GROUP BY cs.qqQuanHuyen ");
		return sql.toString();
	}
	
	private String queryCountQqPhuongXa() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.qqPhuongXa, count(cs.qqPhuongXa) as countPhuongXa ")
		.append("FROM ChienSiEntity cs ")
		.append("WHERE cs.qqTinhThanh = :qqTinhThanh ")
		.append("AND cs.qqQuanHuyen = :qqQuanHuyen ")
		.append("GROUP BY cs.qqPhuongXa ");
		return sql.toString();
	}
	
	private String queryCountDanToc() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dt.ten, count(dt.ten) as countDanToc ")
		.append("FROM ChienSiEntity cs ")
		.append("INNER JOIN cs.danToc dt ")
		.append("GROUP BY dt.ten ");
		return sql.toString();
	}
	
	private String queryCountTonGiao() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.tonGiao, count(cs.tonGiao) as countTonGiao, ")
		.append("GROUP_CONCAT(CONCAT(cs.hoTen, ' ', dtrai.tenTrucThuoc, ' ', cs.ngaySinh, ' ', cs.qqPhuongXa, ' ', cs.qqQuanHuyen, ' ', cs.qqTinhThanh)) ")
		.append("FROM ChienSiEntity cs ")
		.append("GROUP BY cs.tonGiao ");
		return sql.toString();
	}
	
	private void setParamsQuanHuyen(Query query, String qqTinhThanh) {
		query.setParameter("qqTinhThanh", qqTinhThanh);
	}
	
	private void setParamsPhuongXa(Query query, String qqTinhThanh, String qqQuanHuyen) {
		setParamsQuanHuyen(query, qqTinhThanh);
		query.setParameter("qqQuanHuyen", qqQuanHuyen);
	}
	
	private List<CountChienSiDto> convertListObjToChienDto(List<Object[]> input) {
		List<CountChienSiDto> res = new ArrayList<>();
		for (Object[] e : input) {
			String ten = (String) e[0];
			long count = ((Number) e[1]).longValue();
			CountChienSiDto countChienSiDto = new CountChienSiDto();
			countChienSiDto.setTen(ten);
			countChienSiDto.setAmount(count);
			if (e.length == 3) {
				String detail = (String) e[2];
				countChienSiDto.setDetail(detail);
			}
			
			res.add(countChienSiDto);
		}
		return res;
	}
}
