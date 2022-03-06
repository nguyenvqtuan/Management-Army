package com.ld575.quanlycsm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.CountChienSiDto;
import com.ld575.quanlycsm.service.CommonService;

@Repository
public class ExportChienSiRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CommonService commonService;
	
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
		Query query = entityManager.createNativeQuery(queryCountTonGiao());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countDangVien() {
		Query query = entityManager.createNativeQuery(queryCountDangVien());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countTrinhDo() {
		Query query = entityManager.createNativeQuery(queryCountTrinhDo());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countSucKhoe() {
		Query query = entityManager.createQuery(queryCountSucKhoe());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countDoTuoi() {
		Query query = entityManager.createNativeQuery(queryCountDoTuoi());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countCoVo() {
		Query query = entityManager.createNativeQuery(queryCountCoVo());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countBoMat() {
		Query query = entityManager.createNativeQuery(queryCountBoMat());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countMeMat() {
		Query query = entityManager.createNativeQuery(queryCountMeMat());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}

	public List<CountChienSiDto> countBoMeLiDi() {
		Query query = entityManager.createNativeQuery(queryCountBoMeLiDi());
		List<CountChienSiDto> res = convertListObjToChienDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countKhongCoBo() {
		Query query = entityManager.createNativeQuery(queryCountKhongCoBo());
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
		sql.append("SELECT cs.ton_giao, count(cs.ton_giao) as countTonGiao, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("GROUP BY cs.ton_giao ");
		return sql.toString();
	}
	
	private String queryCountDangVien() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Đảng Viên', count(cs.ton_giao) as countTonGiao, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.ngay_vao_dang IS NOT NULL ");
		return sql.toString();
	}
	
	private String queryCountTrinhDo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.trinh_do, count(cs.trinh_do) as countTrinhDo, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("GROUP BY cs.trinh_do");
		return sql.toString();
	}
	
	private String queryCountCoVo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT (CASE WHEN cs.co_vo = 'r' THEN 'Đã đăng ký kết hôn' WHEN cs.co_vo = 'c' THEN 'Chưa đăng ký kết hôn' ELSE 'Chưa kết hôn' end) as co_vo2, count(cs.co_vo) as countCoVo, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.co_vo != 'n' ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.co_vo ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.co_vo");
		return sql.toString();
	}
	
	private String queryCountSucKhoe() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.sucKhoe, count(cs.sucKhoe) as countSucKhoe ")
		.append("FROM ChienSiEntity cs ")
		.append("GROUP BY cs.sucKhoe");
		return sql.toString();
	}
	
	private String queryCountDoTuoi() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT YEAR(cs.ngay_sinh) as nam_sinh, count(YEAR(cs.ngay_sinh)) as countDoTuoi ")
		.append("FROM chien_si_entity cs ")
		.append("GROUP BY nam_sinh ")
		.append("ORDER BY nam_sinh");
		return sql.toString();
	}
	
	private String queryCountBoMat() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Bố Mất', count(cs.bo_mat) as countBoMat, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.bo_mat IS NOT NULL ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.bo_mat ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.bo_mat");
		return sql.toString();
	}
	
	private String queryCountMeMat() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Mẹ Mất', count(cs.me_mat) as countMeMat, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.me_mat IS NOT NULL ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.me_mat ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.me_mat");
		return sql.toString();
	}
	
	private String queryCountBoMeLiDi() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Bố mẹ li dị', count(cs.bo_me_li_di) as countBoMeLiDi, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.bo_me_li_di IS NOT NULL ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.bo_me_li_di ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.bo_me_li_di");
		return sql.toString();
	}
	
	private String queryCountKhongCoBo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Không có bố', count(cs.khong_co_bo) as countKhongCoBo, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.khong_co_bo IS NOT NULL ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.khong_co_bo ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.khong_co_bo");
		return sql.toString();
	}
	
	private void setParamsQuanHuyen(Query query, String qqTinhThanh) {
		query.setParameter("qqTinhThanh", qqTinhThanh);
	}
	
	private String getQueryChienSiDetail() {
		StringBuilder sql = new StringBuilder();
		sql.append("GROUP_CONCAT(CONCAT(cs.ho_ten, ', ', cs.ngay_sinh, ', ', COALESCE(cs.qq_phuong_xa, ''), ', ', COALESCE(cs.qq_quan_huyen	, ''), ', ', COALESCE(cs.qq_tinh_thanh, '')) separator '*|*' ) as detail ");
		return sql.toString();
	}
	
	private String getQueryTrucThuocDoanhTrai() {
		StringBuilder sql = new StringBuilder();
		sql.append("dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten");
		return sql.toString();
	}
	
	private void setParamsPhuongXa(Query query, String qqTinhThanh, String qqQuanHuyen) {
		setParamsQuanHuyen(query, qqTinhThanh);
		query.setParameter("qqQuanHuyen", qqQuanHuyen);
	}
	
	private List<CountChienSiDto> convertListObjToChienDto(List<Object[]> input) {
		List<CountChienSiDto> res = new ArrayList<>();
		for (Object[] e : input) {
			String ten = String.valueOf(e[0]);
			if (commonService.isEmpty(ten)) {
				continue;
			}
			long count = ((Number) e[1]).longValue();
			CountChienSiDto countChienSiDto = new CountChienSiDto();
			countChienSiDto.setTen(ten);
			countChienSiDto.setAmount(count);
			if (e.length > 2) {
				String detail = (String) e[2];
				countChienSiDto.setDetail(detail);
			}
			
			if (e.length > 3) {
				String daiDoi = (String) e[3];
				countChienSiDto.setDaiDoi(daiDoi);
			}
			
			if (e.length > 4) {
				String trungDoi = (String) e[4];
				countChienSiDto.setTrungDoi(trungDoi);
			}
			
			if (e.length > 5) {
				String tieuDoi = (String) e[5];
				countChienSiDto.setTieuDoi(tieuDoi);
			}
			
			res.add(countChienSiDto);
		}
		return res;
	}
}
