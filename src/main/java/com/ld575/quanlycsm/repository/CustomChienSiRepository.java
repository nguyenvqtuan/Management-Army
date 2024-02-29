package com.ld575.quanlycsm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.service.CommonService;

@Repository
public class CustomChienSiRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CommonService commonService;
	
	@SuppressWarnings("unchecked")
	public List<ChienSiEntity> findByCondition(ChienSiDto chienSiDto) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs ")
			.append(" FROM ")
			.append(getFromQuery())
			.append(getWhereQuery(chienSiDto));

		Query query = entityManager.createQuery(sql.toString());
		
		setParams(query, chienSiDto);
		List<ChienSiEntity> res = (List<ChienSiEntity>) query.getResultList();
		
		return res;
	}

	private void setParams(Query query, ChienSiDto chienSiDto) {
		setParamsHoTen(query, chienSiDto);
		setParamsNgaySinh(query, chienSiDto);
		setParamsQueQuan(query, chienSiDto);
		setParamsNoiOHienNay(query, chienSiDto);
		setParamsDoanVien(query, chienSiDto);
		setParamsDangVien(query, chienSiDto);
		setParamsSucKhoe(query, chienSiDto);
		setParamsTrinhDo(query, chienSiDto);
		setParamsDanToc(query, chienSiDto);	
		setParamsTonGiao(query, chienSiDto);
		setParamsNgheNghiepBanThan(query, chienSiDto);
		setParamsSoThich(query, chienSiDto);
		setParamsCoVo(query, chienSiDto);
		setParamsCoMayAnhChiEm(query, chienSiDto);
		setParamsConThuMayTrongNha(query, chienSiDto);
	}
	
	private String getFromQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("ChienSiEntity cs ")
			.append("INNER JOIN cs.danToc dt ")
			.append("INNER JOIN cs.doanhTrai dtrai ");
		return sb.toString();
	}

	private String getWhereQuery(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getHoTen())) {
			sb.insert(0, " WHERE");
			sb.append(getConditionHoTen());
		}
		
		if ((chienSiDto.getNgaySinh() != null && chienSiDto.getNgaySinh() != 0) ||
				(chienSiDto.getThangSinh() != null && chienSiDto.getThangSinh() != 0) ||
				(chienSiDto.getNamSinh() != null && chienSiDto.getNamSinh() != 0) ||
				(chienSiDto.getTuoi() != null && chienSiDto.getTuoi() != 0)) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionNgaySinh(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqPhuongXa()) ||
				!commonService.isEmpty(chienSiDto.getQqQuanHuyen()) ||
				!commonService.isEmpty(chienSiDto.getQqTinhThanh())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionQueQuan(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnPhuongXa()) ||
				!commonService.isEmpty(chienSiDto.getNohnQuanHuyen()) ||
				!commonService.isEmpty(chienSiDto.getNohnTinhThanh())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionNoiOHienNay(chienSiDto));
		}
		
		if (chienSiDto.isDangVien()) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionDangVien(chienSiDto));
		}
		
		if ((chienSiDto.getNgayVaoDoan() != null && chienSiDto.getNgayVaoDoan() != 0) || 
				(chienSiDto.getThangVaoDoan() != null && chienSiDto.getThangVaoDoan() != 0) || 
				(chienSiDto.getNamVaoDoan() != null && chienSiDto.getNamVaoDoan() != 0)) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionDoanVien(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getSucKhoe())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionSucKhoe(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getTrinhDo())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionTrinhDo(chienSiDto));
		}
		
		if (chienSiDto.getDanToc() != null && chienSiDto.getDanToc() != 0) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionDanToc(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getTonGiao())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionTonGiao(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepBanThan())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionNgheNghiepBanThan(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getHinhXam())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionHinhXam(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getGiuBua())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionGiuBua(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getNguoiYeu())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionNguoiYeu(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getHutThuoc())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionHutThuoc(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getSoTruong())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionSoTruong(chienSiDto));
		}
		
		if (!commonService.isEmpty(chienSiDto.getCoVo())) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionCoVo(chienSiDto));
		}
		
		if (chienSiDto.isBoMat()) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionBoMat(chienSiDto));
		}
		
		if (chienSiDto.isMeMat()) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionMeMat(chienSiDto));
		}
		
		if (chienSiDto.isBoMeLiDi()) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionBoMeLiDi(chienSiDto));
		}
		
		if (chienSiDto.isKhongCoBo()) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionKhongCoBo(chienSiDto));
		}
		
		if (chienSiDto.getCoMayAnhChiEm() != null && chienSiDto.getCoMayAnhChiEm() != 0) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionCoMayAnhChiEm(chienSiDto));
		}
		
		if (chienSiDto.getConThuMayTrongNha() != null && chienSiDto.getConThuMayTrongNha() != 0) {
			if (sb.length() == 0) {
				sb.insert(0, " WHERE ");
			} else {
				sb.append(" AND ");
			}
			sb.append(getConditionConThuMayTrongNha(chienSiDto));
		}
		
		return sb.toString();
	}

	private String getConditionHoTen() {
		StringBuilder res = new StringBuilder();
		res.append("(cs.hoTen LIKE :hoTen) ");
		return res.toString();
	}
	
	private String getConditionNgaySinh(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		boolean flg = false;
		if (chienSiDto.getNgaySinh() != null && chienSiDto.getNgaySinh() != 0) {
			flg = true;
			sb.insert(0, "(");
			sb.append("DAY(cs.ngaySinh) = :ngaySinh ");
		}
		
		if (chienSiDto.getThangSinh() != null  && chienSiDto.getThangSinh() != 0) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("MONTH(cs.ngaySinh) = :thangSinh ");
		}
		
		if (chienSiDto.getNamSinh() != null  && chienSiDto.getNamSinh() != 0) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("YEAR(cs.ngaySinh) = :namSinh");
		}
		
		if (chienSiDto.getTuoi() != null  && chienSiDto.getTuoi() != 0) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("YEAR(CURRENT_DATE()) - YEAR(cs.ngaySinh) = :tuoi ");
		}
		if (!flg) {
			return "";
		}
		sb.append(") ");
		return sb.toString();
	}
	
	private String getConditionQueQuan(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		boolean flg = false;
		if (!commonService.isEmpty(chienSiDto.getQqPhuongXa())) {
			flg = true;
			sb.insert(0, " ( ");
			sb.append("cs.qqPhuongXa LIKE :qqPhuongXa");
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqQuanHuyen())) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("cs.qqQuanHuyen LIKE :qqQuanHuyen ");
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqTinhThanh())) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("cs.qqTinhThanh LIKE :qqTinhThanh ");
		}
		if (!flg) {
			return "";
		}
		sb.append(") ");
		return sb.toString();
	}
	
	private String getConditionNoiOHienNay(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		boolean flg = false;
		if (!commonService.isEmpty(chienSiDto.getNohnPhuongXa())) {
			flg = true;
			sb.insert(0, " ( ");
			sb.append("cs.nohnPhuongXa LIKE :nohnPhuongXa");
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnQuanHuyen())) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("cs.nohnQuanHuyen LIKE :nohnQuanHuyen ");
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnTinhThanh())) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("cs.nohnTinhThanh LIKE :nohnTinhThanh ");
		}
		if (!flg) {
			return "";
		}
		sb.append(") ");
		return sb.toString();
	}
	
	private String getConditionDoanVien(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		boolean flg = false;
		if (chienSiDto.getNgayVaoDoan() != null  && chienSiDto.getNgayVaoDoan() != 0) {
			flg = true;
			sb.insert(0, "(");
			sb.append("DAY(cs.ngayVaoDoan) = :ngayVaoDoan ");
		}
		
		if (chienSiDto.getThangVaoDoan() != null  && chienSiDto.getThangVaoDoan() != 0) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("MONTH(cs.thangVaoDoan) = :thangVaoDoan ");
		}
		
		if (chienSiDto.getNamVaoDoan() != null  && chienSiDto.getNamVaoDoan() != 0) {
			flg = true;
			if (sb.length() != 0) {
				sb.append(" AND ");
			} else {
				sb.insert(0, "(");
			}
			sb.append("MONTH(cs.namVaoDoan) = :namVaoDoan ");
		}
		
		if (!flg) {
			return "";
		}
			
		sb.append(") ");
		return sb.toString();
	}
	
	private String getConditionDangVien(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append("cs.ngayVaoDang IS NOT NULL ");
		if (chienSiDto.getNgayVaoDang() != null  && chienSiDto.getNgayVaoDang() != 0) {
			sb.append(" AND ");
			sb.append("DAY(cs.ngayVaoDang) = :ngayVaoDang ");
		}
		
		if (chienSiDto.getThangVaoDang() != null  && chienSiDto.getThangVaoDang() != 0) {
			sb.append(" AND ");
			sb.append("MONTH(cs.thangVaoDang) = :thangVaoDang ");
		}
		
		if (chienSiDto.getNamVaoDang() != null  && chienSiDto.getNamVaoDang() != 0) {
			sb.append(" AND ");
			sb.append("MONTH(cs.namVaoDang) = :namVaoDang ");
		}
		
		sb.append(") ");
		return sb.toString();
	}
	
	private String getConditionSucKhoe(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getSucKhoe())) {
			sb.insert(0, " ( ");
			sb.append("cs.sucKhoe = :sucKhoe");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionTrinhDo(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getTrinhDo())) {
			sb.insert(0, " ( ");
			sb.append("cs.trinhDo = :trinhDo");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionDanToc(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.getDanToc() != null && chienSiDto.getDanToc() != 0) {
			sb.insert(0, " ( ");
			sb.append("dt.id = :danToc");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionTonGiao(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getTonGiao())) {
			sb.insert(0, " ( ");
			sb.append("cs.tonGiao LIKE :tonGiao");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionNgheNghiepBanThan(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepBanThan())) {
			sb.insert(0, " ( ");
			sb.append("cs.ngheNghiepBanThan LIKE :ngheNghiepBanThan");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionHinhXam(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getHinhXam())) {
			sb.insert(0, " ( ");
			sb.append("cs.hinhXam IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionGiuBua(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getGiuBua())) {
			sb.insert(0, " ( ");
			sb.append("cs.giuBua IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionNguoiYeu(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getNguoiYeu())) {
			sb.insert(0, " ( ");
			sb.append("cs.nguoiYeu IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionHutThuoc(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getHutThuoc())) {
			sb.insert(0, " ( ");
			sb.append("cs.hutThuoc IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionSoTruong(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getSoTruong())) {
			sb.insert(0, " ( ");
			sb.append("cs.soTruong LIKE :soTruong");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionCoVo(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (!commonService.isEmpty(chienSiDto.getCoVo())) {
			sb.insert(0, " ( ");
			
			sb.append("cs.coVo = :coVo");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionBoMat(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.isBoMat()) {
			sb.insert(0, " ( ");
			sb.append("cs.boMat IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionMeMat(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.isMeMat()) {
			sb.insert(0, " ( ");
			sb.append("cs.meMat IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionBoMeLiDi(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.isMeMat()) {
			sb.insert(0, " ( ");
			sb.append("cs.boMeLiDi IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionKhongCoBo(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.isMeMat()) {
			sb.insert(0, " ( ");
			sb.append("cs.khongCoBo IS NOT NULL");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionCoMayAnhChiEm(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.getCoMayAnhChiEm() != null && chienSiDto.getCoMayAnhChiEm() != 0) {
			sb.insert(0, " ( ");
			sb.append("cs.coMayAnhChiEm = :coMayAnhChiEm");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private String getConditionConThuMayTrongNha(ChienSiDto chienSiDto) {
		StringBuilder sb = new StringBuilder();
		if (chienSiDto.getConThuMayTrongNha() != null && chienSiDto.getConThuMayTrongNha() != 0) {
			sb.insert(0, " ( ");
			sb.append("cs.conThuMayTrongNha = :conThuMayTrongNha");
		}
		if (sb.length() == 0) {
			return "";
		}
		sb.append(")");
		return sb.toString();
	}
	
	private void setParamsHoTen(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getHoTen())) {
			query.setParameter("hoTen", "%" + chienSiDto.getHoTen() + "%");
		}
	}
	
	private void setParamsNgaySinh(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getNgaySinh() != null && chienSiDto.getNgaySinh() != 0) {
			query.setParameter("ngaySinh", chienSiDto.getNgaySinh());
		}
		
		if (chienSiDto.getThangSinh() != null && chienSiDto.getThangSinh() != 0) {
			query.setParameter("thangSinh", chienSiDto.getThangSinh());
		}
		
		if (chienSiDto.getNamSinh() != null && chienSiDto.getNamSinh() != 0) {
			query.setParameter("namSinh", chienSiDto.getNamSinh());
		} 
		
		if (chienSiDto.getTuoi() != null && chienSiDto.getTuoi() != 0) {
			query.setParameter("tuoi", chienSiDto.getTuoi());
		}
	}
	
	
	private void setParamsQueQuan(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getQqPhuongXa())) {
			query.setParameter("qqPhuongXa", "%" + chienSiDto.getQqPhuongXa() + "%");
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqQuanHuyen())) {
			query.setParameter("qqQuanHuyen", "%" + chienSiDto.getQqQuanHuyen() + "%");
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqTinhThanh())) {
			query.setParameter("qqTinhThanh", "%" + chienSiDto.getQqTinhThanh() + "%");
		} 
	}
	
	private void setParamsNoiOHienNay(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getNohnPhuongXa())) {
			query.setParameter("nohnPhuongXa", "%" + chienSiDto.getNohnPhuongXa() + "%");
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnQuanHuyen())) {
			query.setParameter("nohnQuanHuyen", "%" + chienSiDto.getNohnQuanHuyen() + "%");
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnTinhThanh())) {
			query.setParameter("nohnTinhThanh", "%" + chienSiDto.getNohnTinhThanh() + "%");
		} 
	}
	
	private void setParamsDoanVien(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getNgayVaoDoan() != null && chienSiDto.getNgayVaoDoan() != 0) {
			query.setParameter("ngayVaoDoan", chienSiDto.getNgayVaoDang());
		}
		
		if (chienSiDto.getThangVaoDoan() != null && chienSiDto.getThangVaoDoan() != 0) {
			query.setParameter("thangVaoDoan", chienSiDto.getThangVaoDoan());
		} 
		
		if (chienSiDto.getNamVaoDoan() != null && chienSiDto.getNamVaoDoan() != 0) {
			query.setParameter("namVaoDoan", chienSiDto.getNamVaoDoan());
		}
	}
	
	private void setParamsDangVien(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getNgayVaoDang() != null && chienSiDto.getNgayVaoDang() != 0) {
			query.setParameter("ngayVaoDang", chienSiDto.getNgayVaoDang());
		}
		
		if (chienSiDto.getThangVaoDang() != null && chienSiDto.getThangVaoDang() != 0) {
			query.setParameter("thangVaoDang", chienSiDto.getThangVaoDang());
		} 
		
		if (chienSiDto.getNamVaoDang() != null && chienSiDto.getNamVaoDang() != 0) {
			query.setParameter("namVaoDang", chienSiDto.getNamVaoDang());
		}
	}
	
	private void setParamsSucKhoe(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getSucKhoe())) {
			query.setParameter("sucKhoe", chienSiDto.getSucKhoe());
		}
	}
	
	private void setParamsTrinhDo(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getTrinhDo())) {
			query.setParameter("trinhDo", chienSiDto.getTrinhDo());
		}
	}
	
	private void setParamsDanToc(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getDanToc() != null && chienSiDto.getDanToc() != 0) {
			query.setParameter("danToc", chienSiDto.getDanToc());
		}
	}
	
	private void setParamsTonGiao(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getTonGiao())) {
			query.setParameter("tonGiao", "%" + chienSiDto.getTonGiao() + "%");
		}
	}
	
	private void setParamsNgheNghiepBanThan(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepBanThan())) {
			query.setParameter("ngheNghiepBanThan", "%" + chienSiDto.getNgheNghiepBanThan() + "%");
		}
	}
	
	private void setParamsSoThich(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getSoTruong())) {
			query.setParameter("soTruong", "%" + chienSiDto.getSoTruong() + "%");
		}
	}
	
	private void setParamsCoVo(Query query, ChienSiDto chienSiDto) {
		if (!commonService.isEmpty(chienSiDto.getCoVo())) {
			query.setParameter("coVo", chienSiDto.getCoVo().toCharArray()[0]);
		}
	}
	
	private void setParamsCoMayAnhChiEm(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getCoMayAnhChiEm() != null && chienSiDto.getCoMayAnhChiEm() != 0) {
			query.setParameter("coMayAnhChiEm", chienSiDto.getCoMayAnhChiEm());
		}
	}
	
	private void setParamsConThuMayTrongNha(Query query, ChienSiDto chienSiDto) {
		if (chienSiDto.getConThuMayTrongNha() != null && chienSiDto.getConThuMayTrongNha() != 0) {
			query.setParameter("conThuMayTrongNha", chienSiDto.getConThuMayTrongNha());
		}
	}
}
