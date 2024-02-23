package com.ld575.quanlycsm.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.dto.ChienSiInsertDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.dto.MessageDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.repository.ChienSiRepository;
import com.ld575.quanlycsm.repository.CustomChienSiRepository;

@Service
public class ChienSiService {

	private static long DEFAULT_DAN_TOC = 1L;
	
	@Autowired
	private ChienSiRepository chienSiRepository;
	
	@Autowired
	private DanTocService danTocService;
	
	@Autowired
	private DoanhTraiService doanhTraiService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CustomChienSiRepository customChienSiRepository;

	public MessageDto save(ChienSiInsertDto chienSiDto) {
		ChienSiEntity chienSiEntity = new ChienSiEntity();
		
		// Check duplicate HoTen and NickName
		if (chienSiRepository.findByHoTenAndNickNameContaining(chienSiDto.getHoTen(), chienSiDto.getNickName()).isPresent()) {
			return MessageDto.builder().message(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
		}
		
		if (chienSiDto.getId() != 0L) {
			chienSiEntity.setId(chienSiDto.getId());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHoTen())) {
			chienSiEntity.setHoTen(chienSiDto.getHoTen());
		}
		
		if (chienSiDto.getNgaySinh() != null) {
			chienSiEntity.setNgaySinh(chienSiDto.getNgaySinh());
		}
		
		if (!commonService.isEmpty(chienSiDto.getCapBac())) {
			chienSiEntity.setCapBac(chienSiDto.getCapBac());
		}
		
		if (chienSiDto.getThoiGianNhanCapBac() != null) {
			chienSiEntity.setThoiGianNhanCapBac(chienSiDto.getThoiGianNhanCapBac());
		}
		
		if (!commonService.isEmpty(chienSiDto.getChucVu())) {
			chienSiEntity.setChucVu(chienSiDto.getChucVu());
		}
		
		if (chienSiDto.getNgayNhapNgu() != null) {
			chienSiEntity.setNgayNhapNgu(chienSiDto.getNgayNhapNgu());
		}
		
		if (chienSiDto.getNgayVaoDang() != null) {
			chienSiEntity.setNgayVaoDang(chienSiDto.getNgayVaoDang());
		}
		
		if (chienSiDto.getNgayVaoDangChinhThuc() != null) {
			chienSiEntity.setNgayVaoDangChinhThuc(chienSiDto.getNgayVaoDangChinhThuc());
		}
		
		if (!commonService.isEmpty(chienSiDto.getSoTheDang())) {
			chienSiEntity.setSoTheDang(chienSiDto.getSoTheDang());
		}
		
		if (chienSiDto.getNgayVaoDoan() != null) {
			chienSiEntity.setNgayVaoDoan(chienSiDto.getNgayVaoDoan());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepGiaDinh())) {
			chienSiEntity.setNgheNghiepGiaDinh(chienSiDto.getNgheNghiepGiaDinh());
		}
		
		if (chienSiDto.getCoMayAnhChiEm() != 0) {
			chienSiEntity.setCoMayAnhChiEm(chienSiDto.getCoMayAnhChiEm());
		}
		
		if (chienSiDto.getConThuMayTrongNha() != 0) {
			chienSiEntity.setConThuMayTrongNha(chienSiDto.getConThuMayTrongNha());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHoTenCha())) {
			chienSiEntity.setHoTenCha(chienSiDto.getHoTenCha());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepCha())) {
			chienSiEntity.setNgheNghiepCha(chienSiDto.getNgheNghiepCha());
		}
		
		if (chienSiDto.getNamSinhCha() != 0) {
			chienSiEntity.setNamSinhCha(chienSiDto.getNamSinhCha());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHoTenMe())) {
			chienSiEntity.setHoTenMe(chienSiDto.getHoTenMe());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepMe())) {
			chienSiEntity.setNgheNghiepMe(chienSiDto.getNgheNghiepMe());
		}
		
		if (chienSiDto.getNamSinhMe() != 0) {
			chienSiEntity.setNamSinhMe(chienSiDto.getNamSinhMe());
		}
		
		if (!commonService.isEmpty(chienSiDto.getBoMat())) {
			chienSiEntity.setBoMat(chienSiDto.getBoMat());
		}
		
		if (!commonService.isEmpty(chienSiDto.getMeMat())) {
			chienSiEntity.setMeMat(chienSiDto.getMeMat());
		}
		
		if (!commonService.isEmpty(chienSiDto.getMeMat())) {
			chienSiEntity.setMeMat(chienSiDto.getMeMat());
		}
		
		if (!commonService.isEmpty(chienSiDto.getBoMeLiDi())) {
			chienSiEntity.setBoMeLiDi(chienSiDto.getBoMeLiDi());
		}
		
		if (!commonService.isEmpty(chienSiDto.getKhongCoBo())) {
			chienSiEntity.setKhongCoBo(chienSiDto.getKhongCoBo());
		}
		
		if (!commonService.isEmpty(chienSiDto.getGiaDinhAnhHuongCovid())) {
			chienSiEntity.setGiaDinhAnhHuongCovid(chienSiDto.getGiaDinhAnhHuongCovid());
		}
		if (!commonService.isEmpty(chienSiDto.getGiaDinhKhoKhan())) {
			chienSiEntity.setGiaDinhKhoKhan(chienSiDto.getGiaDinhKhoKhan());
		}
		if (!commonService.isEmpty(chienSiDto.getNguoiQuenTrongQuanDoi())) {
			chienSiEntity.setNguoiQuenTrongQuanDoi(chienSiDto.getNguoiQuenTrongQuanDoi());
		}
		
		if (!commonService.isEmpty(chienSiDto.getBoMeLaLietSiHoacQuanNhan())) {
			chienSiEntity.setBoMeLaLietSiHoacQuanNhan(chienSiDto.getBoMeLaLietSiHoacQuanNhan());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNgheNghiepBanThan())) {
			chienSiEntity.setNgheNghiepBanThan(chienSiDto.getNgheNghiepBanThan());
		}
		
		if (!commonService.isEmpty(chienSiDto.getTrinhDo())) {
			chienSiEntity.setTrinhDo(chienSiDto.getTrinhDo());
		}
		
		if (!commonService.isEmpty(chienSiDto.getDaQuaTruong())) {
			chienSiEntity.setDaQuaTruong(chienSiDto.getDaQuaTruong());
		}
		
		if (chienSiDto.getIdDanToc() != 0) {
			chienSiEntity.setDanToc(danTocService.findById(chienSiDto.getIdDanToc()).get());
		}
		
		if (!commonService.isEmpty(chienSiDto.getTonGiao())) {
			chienSiEntity.setTonGiao(chienSiDto.getTonGiao());
		}
		
		if (!commonService.isEmpty(chienSiDto.getSucKhoe())) {
			chienSiEntity.setSucKhoe(chienSiDto.getSucKhoe());
		}
		
		if (!commonService.isEmpty(chienSiDto.getCoVo())) {
			chienSiEntity.setCoVo(chienSiDto.getCoVo().toCharArray()[0]);
		}
		
		if (!commonService.isEmpty(chienSiDto.getGhiChuCoVo())) {
			chienSiEntity.setGhiChuCoVo(chienSiDto.getGhiChuCoVo());
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqPhuongXa())) {
			chienSiEntity.setQqPhuongXa(chienSiDto.getQqPhuongXa());
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqQuanHuyen())) {
			chienSiEntity.setQqQuanHuyen(chienSiDto.getQqQuanHuyen());
		}
		
		if (!commonService.isEmpty(chienSiDto.getQqTinhThanh())) {
			chienSiEntity.setQqTinhThanh(chienSiDto.getQqTinhThanh());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnPhuongXa())) {
			chienSiEntity.setNohnPhuongXa(chienSiDto.getNohnPhuongXa());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnQuanHuyen())) {
			chienSiEntity.setNohnPhuongXa(chienSiDto.getNohnQuanHuyen());
		}
		if (!commonService.isEmpty(chienSiDto.getNohnPhuongXa())) {
			chienSiEntity.setNohnPhuongXa(chienSiDto.getNohnPhuongXa());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnQuanHuyen())) {
			chienSiEntity.setNohnQuanHuyen(chienSiDto.getNohnQuanHuyen());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNohnTinhThanh())) {
			chienSiEntity.setNohnTinhThanh(chienSiDto.getNohnTinhThanh());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHinhXam())) {
			chienSiEntity.setHinhXam(chienSiDto.getHinhXam());
		}
		
		if (!commonService.isEmpty(chienSiDto.getGiuBua())) {
			chienSiEntity.setGiuBua(chienSiDto.getGiuBua());
		}
		
		if (!commonService.isEmpty(chienSiDto.getNguoiYeu())) {
			chienSiEntity.setNguoiYeu(chienSiDto.getNguoiYeu());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHutThuoc())) {
			chienSiEntity.setHutThuoc(chienSiDto.getHutThuoc());
		}
		
		if (!commonService.isEmpty(chienSiDto.getHutThuoc())) {
			chienSiEntity.setHutThuoc(chienSiDto.getHutThuoc());
		}
		
		if (!commonService.isEmpty(chienSiDto.getSoTruong())) {
			chienSiEntity.setSoTruong(chienSiDto.getSoTruong());
		}
		
		if (chienSiDto.getIdDoanhTrai() != null && chienSiDto.getIdDoanhTrai() != 0) {
			chienSiEntity.setDoanhTrai(doanhTraiService.findById(chienSiDto.getIdDoanhTrai()).get());
		}
		
		if (!commonService.isEmpty(chienSiDto.getGhiChu())) {
			chienSiEntity.setGhiChu(chienSiDto.getGhiChu());
		}
		
		if (chienSiEntity.getId() != null) {
			chienSiEntity.setId(chienSiDto.getId());
		}
		
		chienSiRepository.save(chienSiEntity);
		return MessageDto.builder().message(Flag.SUCCESS.name).type(Flag.SUCCESS).build();
	}
	
	public void save(ChienSiEntity chienSiEntity) {
		chienSiRepository.save(chienSiEntity);
	}

	public Iterable<ChienSiEntity> findAll() {
		return chienSiRepository.findAll();
	}

	public Optional<ChienSiEntity> findById(Long id) {
		return chienSiRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		chienSiRepository.deleteById(id);
	}
	
	public List<ChienSiEntity> findByCondition(ChienSiDto chienSiDto) {
		return customChienSiRepository.findByCondition(chienSiDto);
	}
	
	public void readExcel(MultipartFile file) {
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			int i = 0;
			for (Row row : sheet) {
				if (i != 0) {
					ChienSiEntity chienSiEntity = getChienSiEntityInsert(row);
					save(chienSiEntity);
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ChienSiEntity getChienSiEntityInsert(Row row) {
		int j = 0;
		ChienSiEntity res = new ChienSiEntity();
		for (Cell cell : row) {
	    	switch (j) {
	    	case 0: // STT
	    		break;
	    	case 1: // Trực thuộc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String[] arr = cell.getStringCellValue().split("-");
	    			System.out.println(Arrays.toString(arr));
	    			if (isValidDoanhTrai(arr)) {
	    				res.setDoanhTrai(doanhTraiService.findByTen(arr[arr.length - 1]).get());
	    			} else {
	    				throw new RuntimeException("Bộ phận trực thuộc không hợp lệ");
	    			}
	    			
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 2: // Họ tên 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setHoTen(cell.getStringCellValue());
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 3: // Ngay tháng năm sinh
	    		switch (cell.getCellType()) {
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setNgaySinh(date);
	    		default:
	    			break;
	    		}
	    		break;
	    	case 4: // Cấp bậc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setCapBac(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 5: // Thời gian nhận cấp bậc
	    		switch (cell.getCellType()) {
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setThoiGianNhanCapBac(date);
	    		default:
	    			break;
	    		}
	    		break;
	    	case 6: // Chức vụ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setChucVu(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 7: // Ngày nhập ngũ 
	    		switch (cell.getCellType()) {
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setNgayNhapNgu(date);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 8: // Ngày vào Đảng
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setNgayVaoDang(convertStrToDate(val));
	    			}
	    			break;
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setNgayVaoDang(date);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 9: // Ngày vào Đảng chính thức
	    		switch (cell.getCellType()) {
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setNgayVaoDangChinhThuc(date);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 10: // Số thẻ Đảng
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setSoTheDang(val);
	    			break;
	    		case NUMERIC:
	    			String val2 = String.valueOf(cell.getNumericCellValue());
	    			res.setSoTheDang(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 11: // Ngày vào Đoàn
	    		switch (cell.getCellType()) {
	    		case NUMERIC:
	    			Date date = cell.getDateCellValue();
	    			res.setNgayVaoDoan(date);
	    		default:
	    			break;
	    		}
	    		break;
	    	case 12: // Nghề nghiệp gia đình
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepGiaDinh(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 13: // Có mấy anh chị em
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setCoMayAnhChiEm(Integer.parseInt(val));
	    			break;
	    		case NUMERIC:
	    			int val2 = (int) cell.getNumericCellValue();
	    			res.setCoMayAnhChiEm(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 14: // Con thứ mấy trong nhà
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setConThuMayTrongNha(Integer.parseInt(val));
	    			break;
	    		case NUMERIC:
	    			int val2 = (int) cell.getNumericCellValue();
	    			res.setConThuMayTrongNha(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 15: // Họ tên cha
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHoTenCha(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 16: // Năm sinh cha
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNamSinhCha(Integer.parseInt(val));
	    			break;
	    		case NUMERIC:
	    			int val2 = (int) cell.getNumericCellValue();
	    			res.setNamSinhCha(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 17: // Nghề nghiệp cha
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepCha(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 18: // Họ tên mẹ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHoTenMe(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 19: // Năm sinh mẹ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNamSinhMe(Integer.parseInt(val));
	    			break;
	    		case NUMERIC:
	    			int val2 = (int) cell.getNumericCellValue();
	    			res.setNamSinhMe(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 20: // Nghề nghiệp mẹ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepMe(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 21: // Bố mất 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setBoMat(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 22: // - Mẹ mất 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setMeMat(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 23: // - Bố mẹ li dị 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setBoMeLiDi(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 24: // - Không có bố 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setKhongCoBo(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 25: //Gia đình ảnh hưởng covid 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGiaDinhAnhHuongCovid(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 26: // - Gia đình khó khăn 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGiaDinhKhoKhan(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 27: // Người quen trong quân đội- 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNguoiQuenTrongQuanDoi(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 28: // Bố mẹ đang là liệt sĩ - quân nhân 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setBoMeLaLietSiHoacQuanNhan(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 29: // Nghề nghiệp bản thân 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepBanThan(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 30: // - Trình độ văn hóa
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setTrinhDo(val.toUpperCase());
	    			break;
	    		case NUMERIC:
	    			String val2 = String.valueOf(cell.getNumericCellValue());
	    			res.setTrinhDo(val2);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 31: //  - Đã qua trường 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setDaQuaTruong(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 32: // - Dân tộc - 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			System.out.println(val);
	    			res.setDanToc(danTocService.findByTen(val).get());
	    			break;
	    		default:
	    			res.setDanToc(danTocService.findById(DEFAULT_DAN_TOC).get());
	    			break;
	    		}
	    		break;
	    	case 33: // Tôn giáo 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setTonGiao(commonService.upperFirstCharacter(val)); 
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 34: // -Sức khỏe
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setSucKhoe(val.toUpperCase());
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 35: // - Có vợ [c: chưa dkkh, r: đã dkkh] 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (commonService.isEmpty(val)) {
	    				res.setCoVo('n');
	    			} else {
	    				res.setCoVo(val.toCharArray()[0]);
	    			}
	    			break;
	    		default:
	    			res.setCoVo('n');
	    			break;
	    		}
	    		break;
	    	case 36: // - ghi chú có vợ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGhiChuCoVo(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 37: // Quê quán phường/xã
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqPhuongXa(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 38: // qq quận/huyện
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqQuanHuyen(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 39: // qq tỉnh/thành
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqTinhThanh(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 40: // nohn phường/xã
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnPhuongXa(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 41: // nohn quận/huyện
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnQuanHuyen(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 42: // nohn tỉnh/thành
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnTinhThanh(commonService.upperFirstCharInWords(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 43: // Hình xăm
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHinhXam(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 44: // Giữ bùa
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGiuBua(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 45: // Người yêu
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNguoiYeu(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 46: // Hút thuốc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHutThuoc(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 47: // Sở trường
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setSoTruong(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 48: // Ghi chú 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGhiChu(commonService.upperFirstCharacter(val));
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	default:
	    		throw new RuntimeException("Read cell error");
	    	}
	        j++;
	       
	    }
		return res;
	}

	private Date convertStrToDate(String strDate) {
		Date res = new Date();
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			res = formatter.parse(strDate);
		} catch (ParseException e) {
		}
		
		return res;
	}

	private boolean isValidDate(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	private boolean isValidDoanhTrai(String[] arr) {
		// previous.id == current.TrucThuoc
		long prevId = 0;
		for (int i = 0; i < arr.length; ++i) {
			DoanhTraiEntity doanhTraiEntity = doanhTraiService.findByTen(arr[i].toLowerCase()).get();
			if (i != 0) {
				if (prevId != doanhTraiEntity.getTrucThuoc()) {
					return false;
				}
			}
			prevId = doanhTraiEntity.getId();
		}
		return true;
	}
}
