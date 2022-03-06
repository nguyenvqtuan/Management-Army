package com.ld575.quanlycsm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ld575.quanlycsm.dto.CountChienSiDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.repository.ExportChienSiRepository;

@Component
public class ExportHelper {
	
	private static int DEFAULT_DISTANCE_STATISTICAL = 2;
	@Autowired
	private ExportChienSiRepository chienSiRepository;

	private int rowIdx = 0;
	
	public void export() {
		// workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();
  
        // spreadsheet object
        XSSFSheet sheet
            = workbook.createSheet("Thống kê");
        
        createHeader(workbook, sheet);
        createContent(workbook, sheet);
//        createContent(workbook, sheet);
        
        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        try (FileOutputStream out = new FileOutputStream(
                new File("D:/Project-Managent-CSM/TestExport.xlsx"))) {
        	workbook.write(out);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private void createHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
		setTitle(workbook, sheet);
		setNumberOfTrops(workbook, sheet);
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:K2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("I4:K4"));
	}
	
	private void createContent(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHomeTown(workbook, sheet);
		createEthnic(workbook, sheet);
		createReligion(workbook, sheet);
	}
	
//	private void createContent(XSSFWorkbook workbook, XSSFSheet sheet) {
//		setHomeTown(workbook, sheet);
//		setEthnic(workbook, sheet);
//		setReligion(workbook, sheet);
//		setLeagure(workbook, sheet);
//		setDegree(workbook, sheet);
//		setWedded(workbook, sheet);
//		setHealth(workbook, sheet);
//		setAge(workbook, sheet);
//		setDadPassed(workbook, sheet);
//		setMomPassed(workbook, sheet);
//		setDivorcedParents(workbook, sheet);
//		setNoFather(workbook, sheet);
//		setTattoo(workbook, sheet);
//		setKeepCharms(workbook, sheet);
//		setLover(workbook, sheet);
//		setSmoker(workbook, sheet);
//		setGoToBar(workbook, sheet);
//		setDifficultFamily(workbook, sheet);
//		setAcquaintanceInTheArmy(workbook, sheet);
//		setForte(workbook, sheet);
//		
//		mergeCell(sheet);
//	}
	
	private void mergeCell(XSSFSheet sheet) {
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:AT2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AR4:AT4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("B6:D7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("E6:E7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("F6:G7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("H6:I7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("J6:K7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("L6:N6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("L7:M7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("O6:P8"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Q6:Q8"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("R6:S7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("T6:U7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("V6:W7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("X6:Y7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Z6:AB6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("Z7:AA7"));
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("AC6:AD7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AE6:AG6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AE7:AF7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AH6:AJ6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AH7:AI7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AK6:AL7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AM6:AO6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AM7:AN7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AP6:AQ7"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AR6:At6"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("AR7:AS7"));
	}
	
	private void setTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx = 1;
		XSSFRow row = getRow(sheet, rowIdx);
		
		CellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setFont(getFont(workbook, (short) 10, (short) 18));
		cellStyleHeader.setWrapText(true);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setFillForegroundColor((short) 13);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(cellStyleHeader);
		
		
		cell.setCellValue("BẢNG THỐNG KÊ CHIẾN SĨ MỚI NĂM 2020");
	}
	
	private void setNumberOfTrops(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx = 3;
		XSSFRow row = sheet.createRow(rowIdx);
		
		XSSFCell cell = row.createCell(8);
		
		CellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setFont(getFont(workbook, (short) 10, (short) 13));
		cellStyleHeader.setWrapText(true);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setFillForegroundColor((short) 13);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(cellStyleHeader);
		
		long numberOfTrops = chienSiRepository.countSoldier().getAmount();
		cell.setCellValue("Tổng quân số: " + numberOfTrops + " đ/c");
	}
	
	private void createHomeTown(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderHomeTown(workbook, sheet);
		createContentHomeTown(workbook, sheet);
	}
	
	private void createEthnic(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderEthnic(workbook, sheet);
		createContentEthnic(workbook, sheet);
	}
	
	private void createReligion(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderReligion(workbook, sheet);
		createContentReligion(workbook, sheet);
	}
	
	private void createHeaderHomeTown(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx = 5;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(getStyleMiddle(workbook));
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":D" + (rowIdx + 2)));
		cell.setCellValue("Quê quán");
		createHeaderDetailHomeTown(workbook, sheet);
	}
	
	private void createHeaderDetailHomeTown(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(1);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Tỉnh/Thành");
		
		XSSFCell cell2 = row.createCell(2);
		cell2.setCellStyle(getStyleMiddle(workbook));
		
		cell2.setCellValue("Quận/Huyện");
		
		XSSFCell cell3 = row.createCell(3);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Phường/Xã");
		rowIdx++;
	}
	
	private void createContentHomeTown(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountQqTinhThanh = chienSiRepository.countQqTinhThanh();
		for (CountChienSiDto chienSiDto : listCountQqTinhThanh) {
			createContentHomeTownCity(workbook, sheet, chienSiDto);
			List<CountChienSiDto> listCountQqQuanHuyen = chienSiRepository.countQqQuanHuyen(chienSiDto.getTen());
			for (CountChienSiDto chienSiQuanHuyen : listCountQqQuanHuyen) {
				createContentHomeTownDistrict(workbook, sheet, chienSiQuanHuyen);
				List<CountChienSiDto> listCountQqPhuongXa = chienSiRepository.countQqPhuongXa(chienSiDto.getTen(), chienSiQuanHuyen.getTen());
				listCountQqPhuongXa.stream().forEach(chienSiPhuongXa -> {
					createContentHomeTownWards(workbook, sheet, chienSiPhuongXa);
					rowIdx++;
				});
				
				if (listCountQqPhuongXa.size() == 0) {
					rowIdx++;
				}
				
			}
			if (listCountQqQuanHuyen.size() == 0) {
				rowIdx++;
			}
		}
	}
	
	private void createContentHomeTownCity(XSSFWorkbook workbook, XSSFSheet sheet, CountChienSiDto chienSiDto) {
		XSSFRow row = getRow(sheet, rowIdx);
		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(getStyleContent(workbook));
		cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
	}
	
	private void createContentHomeTownDistrict(XSSFWorkbook workbook, XSSFSheet sheet, CountChienSiDto chienSiDto) {
		XSSFRow row = getRow(sheet, rowIdx);
		XSSFCell cell = row.createCell(2);
		cell.setCellStyle(getStyleContent(workbook));
		cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
	}
	
	private void createContentHomeTownWards(XSSFWorkbook workbook, XSSFSheet sheet, CountChienSiDto chienSiDto) {
		XSSFRow row = getRow(sheet, rowIdx);
		XSSFCell cell = row.createCell(3);
		cell.setCellStyle(getStyleContent(workbook));
		cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
	}
	
	private void createContentEthnic(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountDanToc = chienSiRepository.countDanToc();
		listCountDanToc.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			rowIdx++;
		});
	}
	
	private void createContentReligion(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountDanToc = chienSiRepository.countTonGiao();
		listCountDanToc.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			String[] arrChienSi = chienSiDto.getDetail().split(", ");
			XSSFCell cell2 = row.createCell(2);
			for (String str : arrChienSi) {
				cell2.setCellValue(str + "\n");
			}
			rowIdx++;
		});
	}
	
	private void createHeaderEthnic(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(1);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Dân tộc");
		
		createHeaderEthnicDetail(workbook, sheet);
		rowIdx++;
	}
	
	private void createHeaderEthnicDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx++;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(1);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
	}
	
	private void createHeaderReligion(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(5);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Tôn giáo");
		
		setHeaderReligionDetail(workbook, sheet);
		rowIdx++;
	}
	
	private void setHeaderReligionDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx++;
		XSSFRow row = getRow(sheet, rowIdx);
		
		XSSFCell cell = row.createCell(5);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(6);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setLeagure(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(7);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Đảng viên");
		setLeagureDetail(workbook, sheet);
	}
	
	private void setLeagureDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(7);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(8);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setDegree(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(9);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Trình độ");
		setDegreeDetail(workbook, sheet);
	}
	
	private void setDegreeDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(9);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(10);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setWedded(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(11);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Có vợ, con");
		setWeddedDetail(workbook, sheet);
	}
	
	private void setWeddedDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
			
		XSSFCell cell = row.createCell(11);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(11);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(12);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(13);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private void setHealth(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(14);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Sức khỏe");
	}
	
	private void setAge(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(16);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Độ tuổi");
	}
	
	private void setDadPassed(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(17);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Bố mất");

		setDadPassedDetail(workbook, sheet);
	}
	
	private void setDadPassedDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(17);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(18);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setMomPassed(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(19);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Mẹ mất");

		setMomPassedDetail(workbook, sheet);
	}
	
	private void setMomPassedDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(19);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(20);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setDivorcedParents(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(21);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Bố mẹ li dị");

		setDivorcedDetail(workbook, sheet);
	}
	
	private void setDivorcedDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(21);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(22);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setNoFather(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(23);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Không có bố");

		setNoFatherDetail(workbook, sheet);
	}
	
	private void setNoFatherDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(23);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(24);
		
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setTattoo(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(25);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Hình xăm");

		setTattooDetail(workbook, sheet);
	}
	
	private void setTattooDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
		
		XSSFCell cell = row.createCell(25);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(25);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(26);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(27);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private void setKeepCharms(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(28);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Giữ bùa");

		setKeepCharmsDetail(workbook, sheet);
	}
	
	private void setKeepCharmsDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(28);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(29);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setLover(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(30);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Người yêu");

		setLoverDetail(workbook, sheet);
	}
	
	private void setLoverDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
		
		XSSFCell cell = row.createCell(30);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(30);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(31);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(32);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private void setSmoker(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(33);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Hút thuốc");

		setSmokerDetail(workbook, sheet);
	}
	
	private void setSmokerDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
		
		XSSFCell cell = row.createCell(33);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(33);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(34);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(35);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private void setGoToBar(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(36);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Đi bar");

		setGoToBarDetail(workbook, sheet);
	}
	
	private void setGoToBarDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(36);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(37);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setDifficultFamily(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(38);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Gia đình khó khăn");

		setDifficultFamilyDetail(workbook, sheet);
	}
	
	private void setDifficultFamilyDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
		
		XSSFCell cell = row.createCell(38);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(38);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(39);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(40);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private void setAcquaintanceInTheArmy(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(41);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Người quen trong quân đội");

		setAcquaintanceInTheArmyDetail(workbook, sheet);
	}
	
	private void setAcquaintanceInTheArmyDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(7);
		
		XSSFCell cell = row.createCell(41);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFCell cell2 = row.createCell(42);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void setForte(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(5);
		
		XSSFCell cell = row.createCell(43);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Sở trường");

		setForteDetail(workbook, sheet);
	}
	
	private void setForteDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(6);
		
		XSSFCell cell = row.createCell(43);
		
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		XSSFRow row2 = sheet.getRow(7);
		
		XSSFCell cell2 = row2.createCell(43);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Trung đội");
		
		XSSFCell cell3 = row2.createCell(44);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Tiểu đội");
		
		XSSFCell cell4 = row2.createCell(45);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
	}
	
	private XSSFFont getFont(XSSFWorkbook workbook, short color, short fontHeight) {
		XSSFFont fontTitle = workbook.createFont();
		fontTitle.setBold(true);
		fontTitle.setColor(color);
		fontTitle.setFontHeightInPoints(fontHeight);
		fontTitle.setItalic(false);
		fontTitle.setBold(true);
		return fontTitle;
	}
	
	private CellStyle getStyleMiddle(XSSFWorkbook workbook) {
		CellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setWrapText(true);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setFont(getFont(workbook, (short) 0, (short) 11));
		cellStyleHeader.setFillForegroundColor((short) 42);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(cellStyleHeader);
		return cellStyleHeader;
	}
	
	private CellStyle getStyleContent(XSSFWorkbook workbook) {
		CellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setWrapText(true);
		cellStyleHeader.setAlignment(HorizontalAlignment.LEFT);
		cellStyleHeader.setFont(getFont(workbook, (short) 0, (short) 10));
		setBorder(cellStyleHeader);
		return cellStyleHeader;
	}
	
	private void setBorder(CellStyle cellStyle) {
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
	}
	
	private XSSFRow getRow(XSSFSheet sheet, int rowIdx) {
		return sheet.getRow(rowIdx) == null ? sheet.createRow(rowIdx) : sheet.getRow(rowIdx);
	}
}
