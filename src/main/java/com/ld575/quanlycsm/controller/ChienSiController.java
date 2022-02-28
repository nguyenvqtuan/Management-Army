package com.ld575.quanlycsm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.ChienSiService;
import com.ld575.quanlycsm.service.DanTocService;
import com.ld575.quanlycsm.service.DoanhTraiService;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@Autowired
	private ChienSiService chienSiService;
	
	@Autowired
	private DoanhTraiService doanhTraiService;
	
	@Autowired
	private DanTocService danTocService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model) {
		Iterable<ChienSiEntity> listChienSi = chienSiService.findAll();
		model.addAttribute("listChienSi", listChienSi);
		return "/chiensi/list.html";
	}

	@GetMapping("/read-excel")
	public void readExcel() {
		try (FileInputStream file = new FileInputStream(new File("D:\\Project-Managent-CSM\\Nhập liệu cho phần mềm Quản lý - CSM.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);) {
			
			Sheet sheet = workbook.getSheetAt(0);
			int i = 0;
			for (Row row : sheet) {
				if (i != 0) {
					ChienSiEntity chienSiEntity = getChienSiEntityInsert(row);
					chienSiService.save(chienSiEntity);
				}
			    i++;
			}
		} catch (Exception e) {
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
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setNgaySinh(convertStrToDate(val)); 
	    			}
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 4: // Cấp bậc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setCapBac(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 5: // Thời gian nhận cấp bậc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setThoiGianNhanCapBac(convertStrToDate(val));
	    			}
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 6: // Chức vụ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setChucVu(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 7: // Ngày nhập ngũ 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setNgayNhapNgu(convertStrToDate(val));
	    			}
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
	    		default:
	    			break;
	    		}
	    		break;
	    	case 9: // Ngày vào Đảng chính thức
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setNgayVaoDang(convertStrToDate(val));
	    			}
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
	    		default:
	    			break;
	    		}
	    		break;
	    	case 11: // Ngày vào Đoàn
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			if (isValidDate(val)) {
	    				res.setNgayVaoDoan(convertStrToDate(val));
	    			}
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 12: // Nghề nghiệp gia đình
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepGiaDinh(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 13: // Có mấy anh chị em
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			System.out.println(val);
	    			res.setCoMayAnhChiEm(Integer.parseInt(val));
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
	    		default:
	    			break;
	    		}
	    		break;
	    	case 15: // Họ tên cha
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHoTenCha(val);
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
	    		default:
	    			break;
	    		}
	    		break;
	    	case 17: // Nghề nghiệp cha
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepCha(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 18: // Họ tên mẹ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHoTenMe(val);
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
	    		default:
	    			break;
	    		}
	    		break;
	    	case 20: // Nghề nghiệp mẹ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepMe(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 21: // Bố mất 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 22: // - Mẹ mất 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 23: // - Bố mẹ li dị 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);;
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 24: // - Không có bố 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 25: //Gia đình ảnh hưởng covid 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 26: // - Gia đình khó khăn 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			res.setBoMat(true);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 27: // Người quen trong quân đội- 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNguoiQuenTrongQuanDoi(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 28: // Bố mẹ đang là liệt sĩ - quân nhân 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setBoMeLaLietSiHoacQuanNhan(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 29: // Nghề nghiệp bản thân 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNgheNghiepBanThan(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 30: // - Trình độ văn hóa
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setTrinhDo(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 31: //  - Đã qua trường 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setDaQuaTruong(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 32: // - Dân tộc - 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setDanToc(danTocService.findByTen(formatDanToc(val)).get());
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 33: // Tôn giáo 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setTonGiao(val); 
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 34: // -Sức khỏe
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String trucThuoc = cell.getStringCellValue();
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 35: // - Có vợ [c: chưa dkkh, r: đã dkkh] 
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setCoVo(val.toCharArray()[0]);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 36: // - ghi chú có vợ
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGhiChuCoVo(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 37: // Quê quán phường/xã
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqPhuongXa(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 38: // qq quận/huyện
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqQuanHuyen(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 39: // qq tỉnh/thành
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setQqTinhThanh(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 40: // nohn phường/xã
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnPhuongXa(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 41: // nohn quận/huyện
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnQuanHuyen(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 42: // nohn tỉnh/thành
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNohnTinhThanh(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 43: // Hình xăm
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHinhXam(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 44: // Giữ bùa
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGiuBua(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 45: // Người yêu
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setNguoiYeu(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 46: // Hút thuốc
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setHutThuoc(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 47: // Sở trường
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setSoTruong(val);
	    			break;
	    		default:
	    			break;
	    		}
	    		break;
	    	case 48: // Ghi chú
	    		switch (cell.getCellType()) {
	    		case STRING:
	    			String val = cell.getStringCellValue();
	    			res.setGhiChu(val);
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
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			res = formatter.parse(strDate);
		} catch (ParseException e) {
		}
		
		return res;
	}

	private boolean isValidDate(String strDate) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return false;
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
	
	private String formatDanToc(String input) {
		StringBuilder res = new StringBuilder();
		String[] arr = input.split("\\s");
		for (String str : arr) {
			if (res.length() != 0) {
				res.append(" ");
			}
			String first = str.substring(0, 1).toUpperCase();
			String last = str.substring(1);
			res.append(first);
			res.append(last);
		}
		return res.toString();
	}
}
