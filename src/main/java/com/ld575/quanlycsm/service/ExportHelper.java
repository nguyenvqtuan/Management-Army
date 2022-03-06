package com.ld575.quanlycsm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ld575.quanlycsm.repository.ExportChienSiRepository;

@Component
public class ExportHelper {

	private static int DEFAULT_DISTANCE_STATISTICAL = 2;
	
	@Autowired
	private ExportChienSiRepository chienSiRepository;

	@Autowired
	private CommonService commonService;
	
	private int rowIdx = 0;

	public void export() {
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet sheet = workbook.createSheet("Thống kê");

		createHeader(workbook, sheet);
		createContent(workbook, sheet);
		
		try (FileOutputStream out = new FileOutputStream(new File("D:/Project-Managent-CSM/TestExport.xlsx"))) {
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
		createLeagure(workbook, sheet);
		createDegree(workbook, sheet);
		createWedded(workbook, sheet);
		createHealth(workbook, sheet);
		createAge(workbook, sheet);
		createDadPassed(workbook, sheet);
		createMomPassed(workbook, sheet);
		createDivorcedParents(workbook, sheet);
		createNoFather(workbook, sheet);
	}

//	private void createContent(XSSFWorkbook workbook, XSSFSheet sheet) {
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
		createHeaderNormal(workbook, sheet, "Tôn giáo");
		createContentReligion(workbook, sheet);
	}
	
	private void createLeagure(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderNormal(workbook, sheet, "Đảng viên");
		createContentLeagure(workbook, sheet);
	}
	
	private void createDegree(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderNormal(workbook, sheet, "Trình độ");
		createContentDegree(workbook, sheet);
	}
	
	private void createWedded(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderBelongArmy(workbook, sheet, "Có vợ, con");
		createContentWedded(workbook, sheet);
	}
	
	private void createHealth(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderHealth(workbook, sheet);
		createContentHealth(workbook, sheet);
	}
	
	private void createAge(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderAge(workbook, sheet);
		createContentAge(workbook, sheet);
	}
	
	private void createDadPassed(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderBelongArmy(workbook, sheet, "Bố mất");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countBoMat());
	}
	
	private void createMomPassed(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderBelongArmy(workbook, sheet, "Mẹ mất");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countMeMat());
	}
	
	private void createDivorcedParents(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderBelongArmy(workbook, sheet, "Bố mẹ li dị");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countBoMeLiDi());
	}
	
	private void createNoFather(XSSFWorkbook workbook, XSSFSheet sheet) {
		createHeaderBelongArmy(workbook, sheet, "Không có bố");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countKhongCoBo());
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
				List<CountChienSiDto> listCountQqPhuongXa = chienSiRepository.countQqPhuongXa(chienSiDto.getTen(),
						chienSiQuanHuyen.getTen());
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
		List<CountChienSiDto> listCountTonGiao = chienSiRepository.countTonGiao();
		listCountTonGiao.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");
			XSSFCell cell2 = row.createCell(2);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + "\r\n");
			}
			cell2.setCellValue(strDetail.toString());
			rowIdx++;
		});
	}
	
	private void createContentLeagure(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountDangVien = chienSiRepository.countDangVien();
		for (CountChienSiDto chienSiDto : listCountDangVien) {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			if (commonService.isEmpty(chienSiDto.getDetail())) {
				continue;
			}
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");
			XSSFCell cell2 = row.createCell(2);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + "\r\n");
			}
			cell2.setCellValue(strDetail.toString());
			rowIdx++;
		};
	}
	
	private void createContentDegree(XSSFWorkbook workbook, XSSFSheet sheet) {
		Set<String> showDetails = showTrinhDoDetail();
		List<CountChienSiDto> listCountTrinhDo = chienSiRepository.countTrinhDo();
		listCountTrinhDo.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			if (showDetails.contains(chienSiDto.getTen())) {
				String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");
				XSSFCell cell2 = row.createCell(2);
				StringBuilder strDetail = new StringBuilder();
				for (String str : arrChienSi) {
					strDetail.append(str + "\r\n");
				}
				cell2.setCellValue(strDetail.toString());
			}
			rowIdx++;
		});
	}
	
	private void createContentWedded(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountCoVo = chienSiRepository.countCoVo();
		
		Map<String, Integer> freq = getFreqChienSiDto(listCountCoVo);
		String prevCellDaiDoi = "";
		String prevCellTrungDoi = "";
		String prevCellTieuDoi = "";
		int prevIdxDaiDoi = rowIdx;
		int prevIdxTrungDoi = rowIdx;
		int prevIdxTieuDoi = rowIdx;
		
		for (int i = 0; i < listCountCoVo.size(); ++i) {
			CountChienSiDto chienSiDto = listCountCoVo.get(i);
			XSSFRow row = getRow(sheet, rowIdx);
			
			String[] arrChienSi = listCountCoVo.get(i).getDetail().split("\\*\\|\\*");

			XSSFCell cellDaiDoi = row.createCell(1);
			cellDaiDoi.setCellStyle(getStyleContent(workbook));
			String cellDaiDoiVal = listCountCoVo.get(i).getDaiDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi(), 0));

			if (!prevCellDaiDoi.equals(cellDaiDoiVal)) {
				cellDaiDoi.setCellValue(cellDaiDoiVal);
				if (prevIdxDaiDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx)));
				}
				prevIdxDaiDoi = rowIdx;
			} else {
				if (i == listCountCoVo.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx + 1)));
				}
			}
			
			XSSFCell cellTrungDoi = row.createCell(2);
			cellTrungDoi.setCellStyle(getStyleContent(workbook));
			String cellTrungDoiVal = chienSiDto.getTrungDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi(), 0));
			
			if (!prevCellTrungDoi.equals(cellTrungDoiVal)) {
				cellTrungDoi.setCellValue(cellTrungDoiVal);
				if (prevIdxTrungDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxTrungDoi + 1) + ":C" + (rowIdx)));
				}
				prevIdxTrungDoi = rowIdx;
			} else {
				if (i == listCountCoVo.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxDaiDoi + 1) + ":C" + (rowIdx + 1)));
				}
			}
			
			String cellTieuDoiVal = chienSiDto.getTieuDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi() + "-" + chienSiDto.getTieuDoi(), 0));
			XSSFCell cellTieuDoi = row.createCell(3);
			cellTieuDoi.setCellStyle(getStyleContent(workbook));
			
			if (!prevCellTieuDoi.equals(cellTieuDoiVal)) {
				cellTieuDoi.setCellValue(cellTieuDoiVal);
				if (prevIdxTrungDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxTieuDoi + 1) + ":D" + (rowIdx)));
				}
				prevIdxTieuDoi = rowIdx;
			} else {
				if (i == listCountCoVo.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxDaiDoi + 1) + ":D" + (rowIdx + 1)));
				}
			}
			
			prevCellTrungDoi = cellTrungDoiVal;
			prevCellDaiDoi = cellDaiDoiVal;
			prevCellTieuDoi = cellTieuDoiVal;
			
			
			XSSFCell cell3 = row.createCell(4);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + ", " + chienSiDto.getTen() + "\r\n");
			}
			cell3.setCellValue(strDetail.toString());
			rowIdx++;
		}
	}
	
	
	private void createContentHealth(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountSucKhoe = chienSiRepository.countSucKhoe();
		listCountSucKhoe.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			rowIdx++;
		});
	}
	
	private void createContentAge(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountDoTuoi = chienSiRepository.countDoTuoi();
		listCountDoTuoi.stream().forEach(chienSiDto -> {
			XSSFRow row = getRow(sheet, rowIdx);
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen() + ": " + chienSiDto.getAmount());
			rowIdx++;
		});
	}
	
	private void createContentDadPassed(XSSFWorkbook workbook, XSSFSheet sheet) {
		List<CountChienSiDto> listCountBoMat = chienSiRepository.countBoMat();
		
		Map<String, Integer> freq = getFreqChienSiDto(listCountBoMat);
		String prevCellDaiDoi = "";
		String prevCellTrungDoi = "";
		String prevCellTieuDoi = "";
		int prevIdxDaiDoi = rowIdx;
		int prevIdxTrungDoi = rowIdx;
		int prevIdxTieuDoi = rowIdx;
		
		for (int i = 0; i < listCountBoMat.size(); ++i) {
			CountChienSiDto chienSiDto = listCountBoMat.get(i);
			XSSFRow row = getRow(sheet, rowIdx);
			
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");

			XSSFCell cellDaiDoi = row.createCell(1);
			cellDaiDoi.setCellStyle(getStyleContent(workbook));
			String cellDaiDoiVal = chienSiDto.getDaiDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi(), 0));

			if (!prevCellDaiDoi.equals(cellDaiDoiVal)) {
				cellDaiDoi.setCellValue(cellDaiDoiVal);
				if (prevIdxDaiDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx)));
				}
				prevIdxDaiDoi = rowIdx;
			} else {
				if (i == listCountBoMat.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx + 1)));
				}
			}
			
			XSSFCell cellTrungDoi = row.createCell(2);
			cellTrungDoi.setCellStyle(getStyleContent(workbook));
			String cellTrungDoiVal = chienSiDto.getTrungDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi(), 0));
			
			if (!prevCellTrungDoi.equals(cellTrungDoiVal)) {
				cellTrungDoi.setCellValue(cellTrungDoiVal);
				if (prevIdxTrungDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxTrungDoi + 1) + ":C" + (rowIdx)));
				}
				prevIdxTrungDoi = rowIdx;
			} else {
				if (i == listCountBoMat.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxDaiDoi + 1) + ":C" + (rowIdx + 1)));
				}
			}
			
			String cellTieuDoiVal = chienSiDto.getTieuDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi() + "-" + chienSiDto.getTieuDoi(), 0));
			XSSFCell cellTieuDoi = row.createCell(3);
			cellTieuDoi.setCellStyle(getStyleContent(workbook));
			
			if (!prevCellTieuDoi.equals(cellTieuDoiVal)) {
				cellTieuDoi.setCellValue(cellTieuDoiVal);
				if (prevIdxTrungDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxTieuDoi + 1) + ":D" + (rowIdx)));
				}
				prevIdxTieuDoi = rowIdx;
			} else {
				if (i == listCountBoMat.size() - 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxDaiDoi + 1) + ":D" + (rowIdx + 1)));
				}
			}
			
			prevCellTrungDoi = cellTrungDoiVal;
			prevCellDaiDoi = cellDaiDoiVal;
			prevCellTieuDoi = cellTieuDoiVal;
			
			
			XSSFCell cell3 = row.createCell(4);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + ", " + chienSiDto.getTen() + "\r\n");
			}
			cell3.setCellValue(strDetail.toString());
			rowIdx++;
		}
	}
	
	private void createContentBelongArmy(XSSFWorkbook workbook, XSSFSheet sheet, List<CountChienSiDto> listContent) {
		
		Map<String, Integer> freq = getFreqChienSiDto(listContent);
		String prevCellDaiDoi = "";
		String prevCellTrungDoi = "";
		String prevCellTieuDoi = "";
		int prevIdxDaiDoi = rowIdx;
		int prevIdxTrungDoi = rowIdx;
		int prevIdxTieuDoi = rowIdx;
		
		for (int i = 0; i < listContent.size(); ++i) {
			CountChienSiDto chienSiDto = listContent.get(i);
			XSSFRow row = getRow(sheet, rowIdx);
			
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");

			XSSFCell cellDaiDoi = row.createCell(1);
			cellDaiDoi.setCellStyle(getStyleContent(workbook));
			String cellDaiDoiVal = chienSiDto.getDaiDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi(), 0));

			if (!prevCellDaiDoi.equals(cellDaiDoiVal)) {
				cellDaiDoi.setCellValue(cellDaiDoiVal);
//				if (prevIdxDaiDoi != rowIdx) {
//					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx)));
//				}
				prevIdxDaiDoi = rowIdx;
			} else {
				if (i == listContent.size() - 1 && prevIdxDaiDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxDaiDoi + 1) + ":B" + (rowIdx + 1)));
				}
			}
			
			XSSFCell cellTrungDoi = row.createCell(2);
			cellTrungDoi.setCellStyle(getStyleContent(workbook));
			String cellTrungDoiVal = chienSiDto.getTrungDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi(), 0));
			
			if (!prevCellTrungDoi.equals(cellTrungDoiVal)) {
				cellTrungDoi.setCellValue(cellTrungDoiVal);
//				if (prevIdxTrungDoi != rowIdx) {
//					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxTrungDoi + 1) + ":C" + (rowIdx)));
//				}
				prevIdxTrungDoi = rowIdx;
			} else {
				if (i == listContent.size() - 1 && prevIdxDaiDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxDaiDoi + 1) + ":C" + (rowIdx + 1)));
				}
			}
			
			String cellTieuDoiVal = chienSiDto.getTieuDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi() + "-" + chienSiDto.getTieuDoi(), 0));
			XSSFCell cellTieuDoi = row.createCell(3);
			cellTieuDoi.setCellStyle(getStyleContent(workbook));
			
			if (!prevCellTieuDoi.equals(cellTieuDoiVal)) {
				cellTieuDoi.setCellValue(cellTieuDoiVal);
//				if (prevIdxTrungDoi != rowIdx) {
//					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxTieuDoi + 1) + ":D" + (rowIdx)));
//				}
				prevIdxTieuDoi = rowIdx;
			} else {
				if (i == listContent.size() - 1 && prevIdxDaiDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxDaiDoi + 1) + ":D" + (rowIdx + 1)));
				}
			}
			
			prevCellTrungDoi = cellTrungDoiVal;
			prevCellDaiDoi = cellDaiDoiVal;
			prevCellTieuDoi = cellTieuDoiVal;
			
			
			XSSFCell cell3 = row.createCell(4);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + ", " + chienSiDto.getTen() + "\r\n");
			}
			cell3.setCellValue(strDetail.toString());
			rowIdx++;
		}
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

	private void createHeaderHealth(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Sức khỏe");
		rowIdx++;
	}

	private void createHeaderAge(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Độ tuổi");
		rowIdx++;
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
	
	private XSSFCell getCell(XSSFRow row, int cellIdx) {
		return row.getCell(cellIdx) == null ? row.createCell(cellIdx) : row.getCell(cellIdx);
	}
	
	private Set<String> showTrinhDoDetail() {
		Set<String> res = new HashSet<>();
		res.add("Đại Học");
		res.add("ĐẠI HỌC");
		res.add("ĐH");
		res.add("DH");
		res.add("TC");
		res.add("CĐ");
		res.add("CD");
		return res;
	}
	
	private Map<String, Integer> getFreqChienSiDto(List<CountChienSiDto> input) {
		Map<String, Integer> freq = new HashMap<>();
		input.stream().forEach(e -> {
			int count = 0;
			
			if (e.getDetail() != null && e.getDetail().split("\\*\\|\\*").length > 1) {
				count = e.getDetail().split("\\*\\|\\*").length - 1;
				System.out.println(e.getDetail().split("\\*\\|\\*").length);
			}
			freq.put(e.getDaiDoi(), freq.getOrDefault(e.getDaiDoi(), 0) + 1 + count);
			freq.put(e.getDaiDoi() + "-" + e.getTrungDoi(), freq.getOrDefault(e.getDaiDoi() + "-" + e.getTrungDoi(), 0) + 1 + count);
			freq.put(e.getTrungDoi(), freq.getOrDefault(e.getTrungDoi(), 0) + 1 + count);
			freq.put(e.getTrungDoi() + "-" + e.getTieuDoi(), freq.getOrDefault(e.getTrungDoi() + "-" + e.getTieuDoi(), 0) + 1 + count);
			freq.put(e.getDaiDoi() + "-" + e.getTrungDoi() + "-" + e.getTieuDoi(), freq.getOrDefault(e.getDaiDoi() + "-" + e.getTrungDoi() + "-" + e.getTieuDoi(), 0) + 1 + count);
		});
		
		return freq;
	}
	
	private void createHeaderNormal(XSSFWorkbook workbook, XSSFSheet sheet, String title) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue(title);
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":c" + (rowIdx + 1)));
		createHeaderNormalDetail(workbook, sheet);
		rowIdx++;
	}
	
	private void createHeaderNormalDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx++;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");

		XSSFCell cell2 = row.createCell(2);

		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Chi tiết");
	}
	
	private void createHeaderBelongArmy(XSSFWorkbook workbook, XSSFSheet sheet, String title) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue(title);
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":E" + (rowIdx + 1)));
		createHeaderBelongArmyDetail(workbook, sheet);
		rowIdx++;
	}
	
	private void createHeaderBelongArmyDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx++;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":D" + (rowIdx + 1)));

		rowIdx++;
		XSSFRow row2 = getRow(sheet, rowIdx);

		XSSFCell cell2 = row2.createCell(1);
		cell2.setCellStyle(getStyleMiddle(workbook));
		cell2.setCellValue("Đại đội");

		XSSFCell cell3 = row2.createCell(2);
		cell3.setCellStyle(getStyleMiddle(workbook));
		cell3.setCellValue("Trung đội");
		
		XSSFCell cell4 = row2.createCell(3);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Tiểu đội");

		XSSFCell cell5 = row2.createCell(4);
		cell5.setCellStyle(getStyleMiddle(workbook));
		cell5.setCellValue("Chi tiết");
	}
}
