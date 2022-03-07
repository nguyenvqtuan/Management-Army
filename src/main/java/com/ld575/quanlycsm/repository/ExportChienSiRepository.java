package com.ld575.quanlycsm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.CountChienSiDto;
import com.ld575.quanlycsm.dto.CountDanTocDto;
import com.ld575.quanlycsm.service.CommonService;

@Repository
public class ExportChienSiRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CommonService commonService;
	
	public CountChienSiDto countSoldier(String namNhapNgu) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(cs.id) FROM chien_si_entity cs ")
		.append("WHERE YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu"); 
		Query query = entityManager.createNativeQuery(sql.toString());
		setParamNamNhapNgu(query, namNhapNgu);
		Object res = query.getSingleResult();
		return CountChienSiDto.builder().amount(((Number) res).longValue()).build();
	}
	
	public List<CountDanTocDto> countQueQuan(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountQueQuan());
		setParamNamNhapNgu(query, namNhapNgu);
		List<Object[]> objects = (List<Object[]>) query.getResultList();
		List<CountDanTocDto> res = new ArrayList<>();
		for (Object[] obj : objects) {
			CountDanTocDto countDanTocDto = CountDanTocDto.builder()
			.qqTinhThanh(String.valueOf(obj[0]))
			.qqQuanHuyen(String.valueOf(obj[1]))
			.qqPhuongXa(String.valueOf(obj[2]))
			.countQqTinhThanh(String.valueOf(obj[3]))
			.countQqQuanHuyen(String.valueOf(obj[4]))
			.countQqPhuongXa(String.valueOf(obj[5]))
			.detail(String.valueOf(obj[6])).build();
			
			res.add(countDanTocDto);
		}
		return res;
	}
	
	public List<CountChienSiDto> countDanToc(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountDanToc());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countTonGiao(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountTonGiao());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countDangVien(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountDangVien());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countTrinhDo(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountTrinhDo());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countSucKhoe(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountSucKhoe());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countDoTuoi(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountDoTuoi());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countCoVo(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountCoVo());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countBoMat(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountBoMat());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countMeMat(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountMeMat());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}

	public List<CountChienSiDto> countBoMeLiDi(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountBoMeLiDi());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countKhongCoBo(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountKhongCoBo());
		setParamNamNhapNgu(query, namNhapNgu);
		List<CountChienSiDto> res = convertListObjToChienSiDto(query.getResultList());
		return res;
	}
	
	public List<CountChienSiDto> countHinhXam(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountHinhXam());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	public List<CountChienSiDto> countGiuBua(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountGiuBua());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	public List<CountChienSiDto> countNguoiYeu(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountNguoiYeu());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	public List<CountChienSiDto> countHutThuoc(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountHutThuoc());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}

	public List<CountChienSiDto> countGiaDinhKhoKhan(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountGiaDinhKhoKhan());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	public List<CountChienSiDto> countNguoiQuenTrongQuanDoi(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountNguoiQuenTrongQuanDoi());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	public List<CountChienSiDto> countSoTruong(String namNhapNgu) {
		Query query = entityManager.createNativeQuery(queryCountSoTruong());
		setParamNamNhapNgu(query, namNhapNgu);
		return convertListObjToChienSiDto(query.getResultList());
	}
	
	private String queryCountQueQuan() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.qq_tinh_thanh, cs.qq_quan_huyen, cs.qq_phuong_xa, count(cs.qq_tinh_thanh) as countTinhThanh, count(cs.qq_quan_huyen) as countQuanHuyen, count(cs.qq_phuong_xa) as countPhuongXa, ")
		.append("GROUP_CONCAT(CONCAT(cs.ho_ten, ', ', cs.ngay_sinh, ', ', dtrai.ten, '-', dtrai.ten_truc_thuoc) separator '*|*' ) as detail ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY cs.qq_tinh_thanh, cs.qq_quan_huyen, cs.qq_phuong_xa ")
		.append("ORDER BY cs.qq_tinh_thanh, cs.qq_quan_huyen, cs.qq_phuong_xa");
		return sql.toString();
	}
	
	private String queryCountDanToc() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dt.ten, count(dt.ten) as countDanToc ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN dan_toc_entity dt ")
		.append("ON dt.id = cs.dan_toc_id ")
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("WHERE ")
		.append("cs.ngay_vao_dang IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ");
		return sql.toString();
	}
	
	private String queryCountTrinhDo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.trinh_do, count(cs.trinh_do) as countTrinhDo, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.co_vo ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.co_vo");
		return sql.toString();
	}
	
	private String queryCountSucKhoe() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs.suc_khoe, count(cs.suc_khoe) as countSucKhoe ")
		.append("FROM chien_si_entity cs ")
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY cs.suc_khoe");
		return sql.toString();
	}
	
	private String queryCountDoTuoi() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT YEAR(cs.ngay_sinh) as nam_sinh, count(YEAR(cs.ngay_sinh)) as countDoTuoi ")
		.append("FROM chien_si_entity cs ")
		.append("WHERE ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("WHERE ")
		.append("cs.bo_mat IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("WHERE ")
		.append("cs.me_mat IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
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
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.khong_co_bo ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.khong_co_bo");
		return sql.toString();
	}
	
	private String queryCountHinhXam() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Hình xăm', count(cs.hinh_xam) as countHinhXam, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.hinh_xam IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.hinh_xam ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.hinh_xam");
		return sql.toString();
	}
	
	private String queryCountGiuBua() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Giữ bùa', count(cs.giu_bua) as countGiuBua, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.giu_bua IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY cs.giu_bua");
		return sql.toString();
	}
	
	private String queryCountNguoiYeu() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Người yêu', count(cs.nguoi_yeu) as countNguoiYeu, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.nguoi_yeu IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.nguoi_yeu ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.nguoi_yeu");
		return sql.toString();
	}
	
	private String queryCountHutThuoc() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Hút thuốc', count(cs.hut_thuoc) as countHutThuoc, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.nguoi_yeu IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.hut_thuoc ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.hut_thuoc");
		return sql.toString();
	}
	
	private String queryCountGiaDinhKhoKhan() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Gia đình khó khăn', count(cs.gia_dinh_kho_khan) as countGiaDinhKhoKhan, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.gia_dinh_kho_khan IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.gia_dinh_kho_khan ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.gia_dinh_kho_khan");
		return sql.toString();
	}
	
	private String queryCountNguoiQuenTrongQuanDoi() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Đi bar', count(cs.nguoi_quen_trong_quan_doi) as countDiBar, ")
		.append(getQueryChienSiDetail())
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.nguoi_quen_trong_quan_doi IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY cs.nguoi_quen_trong_quan_doi");
		return sql.toString();
	}
	
	private String queryCountSoTruong() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 'Sở trường', count(cs.so_truong) as countSoTruong, ")
		.append(getQueryChienSiDetail() + ", ")
		.append(getQueryTrucThuocDoanhTrai() + " ")
		.append("FROM chien_si_entity cs ")
		.append("INNER JOIN doanh_trai_entity dtrai ")
		.append("ON dtrai.id = cs.doanh_trai_id ")
		.append("WHERE cs.so_truong IS NOT NULL ")
		.append("AND ")
		.append("YEAR(cs.ngay_nhap_ngu) = :nam_nhap_ngu ")
		.append("GROUP BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.so_truong ")
		.append("ORDER BY dtrai.truc_thuoc_dai_doi, dtrai.truc_thuoc_trung_doi, dtrai.ten, cs.so_truong");
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
	
	private List<CountChienSiDto> convertListObjToChienSiDto(List<Object[]> input) {
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
	
	private void setParamNamNhapNgu(Query query, String namNhapNgu) {
		query.setParameter("nam_nhap_ngu", namNhapNgu);
	}
}
