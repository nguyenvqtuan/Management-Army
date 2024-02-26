package com.ld575.quanlycsm.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ld575.quanlycsm.dto.CountDanTocDto;
import com.ld575.quanlycsm.repository.ExportChienSiRepository;

@Component
public class ExportHelper {

	private static int DEFAULT_DISTANCE_STATISTICAL = 2;
	
	@Autowired
	private ExportChienSiRepository chienSiRepository;

	@Autowired
	private CommonService commonService;
	
	private int rowIdx = 0;

	public ByteArrayInputStream export(String namNhapNgu) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Thống kê");

		createHeader(workbook, sheet, namNhapNgu);
		createContent(workbook, sheet, namNhapNgu);
		
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void createHeader(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		setTitle(workbook, sheet, namNhapNgu);
		setNumberOfTrops(workbook, sheet, namNhapNgu);

		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:K2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("I4:K4"));
	}

	private void createContent(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHomeTown(workbook, sheet, namNhapNgu);
		createEthnic(workbook, sheet, namNhapNgu);
		createReligion(workbook, sheet, namNhapNgu);
		createLeagure(workbook, sheet, namNhapNgu);
		createDegree(workbook, sheet, namNhapNgu);
		createWedded(workbook, sheet, namNhapNgu);
		createHealth(workbook, sheet, namNhapNgu);
		createAge(workbook, sheet, namNhapNgu);
		createDadPassed(workbook, sheet, namNhapNgu);
		createMomPassed(workbook, sheet, namNhapNgu);
		createDivorcedParents(workbook, sheet, namNhapNgu);
		createNoFather(workbook, sheet, namNhapNgu);
		createTatoo(workbook, sheet, namNhapNgu);
		createKeepCharm(workbook, sheet, namNhapNgu);
		createLover(workbook, sheet, namNhapNgu);
		createSmoker(workbook, sheet, namNhapNgu);
		createDifficultFamily(workbook, sheet, namNhapNgu);
		createAcquaintanceInTheArmy(workbook, sheet, namNhapNgu);
		createForte(workbook, sheet, namNhapNgu);
	}

	private void setTitle(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		rowIdx = 1;
		XSSFRow row = getRow(sheet, rowIdx);

		CellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setFont(getFont(workbook, (short) 10, (short) 16));
		cellStyleHeader.setWrapText(true);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setFillForegroundColor((short) 13);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(cellStyleHeader);

		cell.setCellValue("BẢNG THỐNG KÊ CHIẾN SĨ MỚI NĂM " + namNhapNgu);
	}

	private void setNumberOfTrops(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
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

		long numberOfTrops = chienSiRepository.countSoldier(namNhapNgu).getAmount();
		cell.setCellValue("Tổng quân số: " + numberOfTrops + " đ/c");
	}

	private void createHomeTown(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderHomeTown(workbook, sheet);
		createContentHomeTown(workbook, sheet, namNhapNgu);
	}

	private void createEthnic(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Dân tộc");
		createContentNormal(workbook, sheet, chienSiRepository.countDanToc(namNhapNgu));
	}

	private void createReligion(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Tôn giáo");
		createContentNormal(workbook, sheet, chienSiRepository.countTonGiao(namNhapNgu));
	}
	
	private void createLeagure(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Đảng viên");
		createContentNormal(workbook, sheet, chienSiRepository.countDangVien(namNhapNgu));
	}
	
	private void createDegree(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Trình độ");
		createContentNormal(workbook, sheet, chienSiRepository.countTrinhDo(namNhapNgu));
	}
	
	private void createWedded(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Có vợ, con");
		createContentNormal(workbook, sheet, chienSiRepository.countCoVo(namNhapNgu));
	}
	
	private void createHealth(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Sức khỏe");
		createContentNormal(workbook, sheet, chienSiRepository.countSucKhoe(namNhapNgu));
	}
	
	private void createAge(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Năm sinh");
		createContentNormal(workbook, sheet, chienSiRepository.countDoTuoi(namNhapNgu));
	}
	
	private void createDadPassed(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Bố mất");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countBoMat(namNhapNgu));
	}
	
	private void createMomPassed(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Mẹ mất");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countMeMat(namNhapNgu));
	}
	
	private void createDivorcedParents(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Bố mẹ li dị");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countBoMeLiDi(namNhapNgu));
	}
	
	private void createNoFather(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Không có bố");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countKhongCoBo(namNhapNgu));
	}
	
	private void createTatoo(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Hình xăm");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countHinhXam(namNhapNgu));
	}

	private void createKeepCharm(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Giữ bùa");
		createContentNormal(workbook, sheet, chienSiRepository.countGiuBua(namNhapNgu));
	}
	
	private void createLover(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Người yêu");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countNguoiYeu(namNhapNgu));
	}
	
	private void createSmoker(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Hút thuốc");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countHutThuoc(namNhapNgu));
	}
	
	private void createDifficultFamily(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderBelongArmy(workbook, sheet, "Gia đình khó khăn");
		createContentBelongArmy(workbook, sheet, chienSiRepository.countGiaDinhKhoKhan(namNhapNgu));
	}
	
	private void createAcquaintanceInTheArmy(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Người quen trong quân đội");
		createContentNormal(workbook, sheet, chienSiRepository.countNguoiQuenTrongQuanDoi(namNhapNgu));
	}
	
	private void createForte(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		createHeaderNormal(workbook, sheet, "Sở trường");
		createContentNormal(workbook, sheet, chienSiRepository.countSoTruong(namNhapNgu));
	}
	
	private void createHeaderHomeTown(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx = 5;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(getStyleMiddle(workbook));

		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":E" + (rowIdx + 2)));
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
		
		XSSFCell cell4 = row.createCell(4);
		cell4.setCellStyle(getStyleMiddle(workbook));
		cell4.setCellValue("Chi tiết");
		rowIdx++;
	}

	private void createContentHomeTown(XSSFWorkbook workbook, XSSFSheet sheet, String namNhapNgu) {
		List<CountDanTocDto> listQueQuan = chienSiRepository.countQueQuan(namNhapNgu);
		
		Map<String, Integer> freq = getFreqDanToc(listQueQuan);
		String prevCellTinhThanh = "";
		String prevCellQuanHuyen = "";
		String prevCellPhuongXa = "";
		int prevIdxTinhThanh = rowIdx;
		int prevIdxQuanHuyen = rowIdx;
		int prevIdxPhuongXa = rowIdx;
		
		for (int i = 0; i < listQueQuan.size(); ++i) {
			CountDanTocDto chienSiDto = listQueQuan.get(i);
			XSSFRow row = getRow(sheet, rowIdx);
			
			if (commonService.isEmpty(chienSiDto.getDetail())) continue;
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");

			XSSFCell cellTinhThanh = row.createCell(1);
			cellTinhThanh.setCellStyle(getStyleContent(workbook));
			String cellTinhThanhVal = chienSiDto.getQqTinhThanh() + ": " + (freq.getOrDefault(chienSiDto.getQqTinhThanh(), 0));

			if (!prevCellTinhThanh.equals(cellTinhThanhVal) && chienSiDto.getQqTinhThanh() != "null") {
				cellTinhThanh.setCellValue(cellTinhThanhVal);
				if (prevIdxTinhThanh != rowIdx && rowIdx - prevIdxTinhThanh > 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxTinhThanh + 1) + ":B" + (rowIdx)));
				}
				prevIdxTinhThanh = rowIdx;
			} else {
				if (i == listQueQuan.size() - 1 && prevIdxTinhThanh != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (prevIdxTinhThanh + 1) + ":B" + (rowIdx + 1)));
				}
			}
			
			XSSFCell cellQuanHuyen = row.createCell(2);
			cellQuanHuyen.setCellStyle(getStyleContent(workbook));
			String cellQuanHuyenVal = chienSiDto.getQqQuanHuyen() + ": " + (freq.getOrDefault(chienSiDto.getQqTinhThanh() + "-" + chienSiDto.getQqQuanHuyen(), 0));
			
			if (!prevCellQuanHuyen.equals(cellQuanHuyenVal) && chienSiDto.getQqQuanHuyen() != "null") {
				cellQuanHuyen.setCellValue(cellQuanHuyenVal);
				if (prevIdxQuanHuyen != rowIdx && rowIdx - prevIdxQuanHuyen > 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxQuanHuyen + 1) + ":C" + (rowIdx)));
				}
				prevIdxQuanHuyen = rowIdx;
			} else {
				if (i == listQueQuan.size() - 1 && prevIdxQuanHuyen != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxQuanHuyen + 1) + ":C" + (rowIdx + 1)));
				}
			}
			
			String cellPhuongXaVal = chienSiDto.getQqPhuongXa() + ": " + (freq.getOrDefault(chienSiDto.getQqTinhThanh() + "-" + chienSiDto.getQqQuanHuyen() + "-" + chienSiDto.getQqPhuongXa(), 0));
			XSSFCell cellPhuongXa = row.createCell(3);
			cellPhuongXa.setCellStyle(getStyleContent(workbook));
			
			if (!prevCellPhuongXa.equals(cellPhuongXaVal)  && chienSiDto.getQqPhuongXa() != "null") {
				cellPhuongXa.setCellValue(cellPhuongXaVal);
				if (prevIdxPhuongXa != rowIdx && prevIdxPhuongXa - rowIdx > 1) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxPhuongXa + 1) + ":D" + (rowIdx)));
				}
				prevIdxPhuongXa = rowIdx;
			} else {
				if (i == listQueQuan.size() - 1 && prevIdxPhuongXa != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxPhuongXa + 1) + ":D" + (rowIdx + 1)));
				}
			}
			
			prevCellTinhThanh = cellTinhThanhVal;
			prevCellQuanHuyen = cellQuanHuyenVal;
			prevCellPhuongXa = cellPhuongXaVal;
			
			
			XSSFCell cell3 = row.createCell(4);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + "\r\n");
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
			
			if (commonService.isEmpty(chienSiDto.getDetail())) continue;
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
				if (i == listContent.size() - 1 && prevIdxDaiDoi != rowIdx) {
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
				if (i == listContent.size() - 1 && prevIdxTrungDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("C" + (prevIdxTrungDoi + 1) + ":C" + (rowIdx + 1)));
				}
			}
			
			String cellTieuDoiVal = chienSiDto.getTieuDoi() + ": " + (freq.getOrDefault(chienSiDto.getDaiDoi() + "-" + chienSiDto.getTrungDoi() + "-" + chienSiDto.getTieuDoi(), 0));
			XSSFCell cellTieuDoi = row.createCell(3);
			cellTieuDoi.setCellStyle(getStyleContent(workbook));
			
			if (!prevCellTieuDoi.equals(cellTieuDoiVal)) {
				cellTieuDoi.setCellValue(cellTieuDoiVal);
				if (prevIdxTieuDoi != rowIdx && (prevIdxTieuDoi + 1 != rowIdx)) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxTieuDoi + 1) + ":D" + (rowIdx)));
				}
				prevIdxTieuDoi = rowIdx;
			} else {
				if (i == listContent.size() - 1 && prevIdxTieuDoi != rowIdx) {
					sheet.addMergedRegion(CellRangeAddress.valueOf("D" + (prevIdxTieuDoi + 1) + ":D" + (rowIdx + 1)));
				}
			}
			
			prevCellTrungDoi = cellTrungDoiVal;
			prevCellDaiDoi = cellDaiDoiVal;
			prevCellTieuDoi = cellTieuDoiVal;
			
			
			XSSFCell cell3 = row.createCell(4);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + "\r\n");
			}
			cell3.setCellValue(strDetail.toString());
			rowIdx++;
		}
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
	
	private Map<String, Integer> getFreqChienSiDto(List<CountChienSiDto> input) {
		Map<String, Integer> freq = new HashMap<>();
		input.stream().forEach(e -> {
			int count = 0;
			
			if (e.getDetail() != null && e.getDetail().split("\\*\\|\\*").length > 1) {
				count = e.getDetail().split("\\*\\|\\*").length - 1;
			}
			freq.put(e.getDaiDoi(), freq.getOrDefault(e.getDaiDoi(), 0) + 1 + count);
			freq.put(e.getDaiDoi() + "-" + e.getTrungDoi(), freq.getOrDefault(e.getDaiDoi() + "-" + e.getTrungDoi(), 0) + 1 + count);
			freq.put(e.getTrungDoi(), freq.getOrDefault(e.getTrungDoi(), 0) + 1 + count);
			freq.put(e.getTrungDoi() + "-" + e.getTieuDoi(), freq.getOrDefault(e.getTrungDoi() + "-" + e.getTieuDoi(), 0) + 1 + count);
			freq.put(e.getDaiDoi() + "-" + e.getTrungDoi() + "-" + e.getTieuDoi(), freq.getOrDefault(e.getDaiDoi() + "-" + e.getTrungDoi() + "-" + e.getTieuDoi(), 0) + 1 + count);
		});
		
		return freq;
	}
	
	private Map<String, Integer> getFreqDanToc(List<CountDanTocDto> input) {
		Map<String, Integer> freq = new HashMap<>();
		input.stream().forEach(e -> {
			int count = 0;
			
			if (e.getDetail() != null && e.getDetail().split("\\*\\|\\*").length > 1) {
				count = e.getDetail().split("\\*\\|\\*").length - 1;
			}
			freq.put(e.getQqTinhThanh(), freq.getOrDefault(e.getQqTinhThanh(), 0) + 1 + count);
			freq.put(e.getQqTinhThanh() + "-" + e.getQqQuanHuyen(), freq.getOrDefault(e.getQqTinhThanh() + "-" + e.getQqQuanHuyen(), 0) + 1 + count);
			freq.put(e.getQqQuanHuyen(), freq.getOrDefault(e.getQqQuanHuyen(), 0) + 1 + count);
			freq.put(e.getQqQuanHuyen() + "-" + e.getQqPhuongXa(), freq.getOrDefault(e.getQqQuanHuyen() + "-" + e.getQqPhuongXa(), 0) + 1 + count);
			freq.put(e.getQqTinhThanh() + "-" + e.getQqQuanHuyen() + "-" + e.getQqPhuongXa(), freq.getOrDefault(e.getQqTinhThanh() + "-" + e.getQqQuanHuyen() + "-" + e.getQqPhuongXa(), 0) + 1 + count);
		});
		
		return freq;
	}
	
	private void createHeaderNormal(XSSFWorkbook workbook, XSSFSheet sheet, String title) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue(title);
		
		cell = row.createCell(2);
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");

		cell = row.createCell(3);
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Chi tiết");
		
		rowIdx++;
	}
	
	private void createContentNormal(XSSFWorkbook workbook, XSSFSheet sheet, List<CountChienSiDto> listCount) {
		for(CountChienSiDto chienSiDto : listCount) {
			XSSFRow row = getRow(sheet, rowIdx);
			if (commonService.isEmpty(chienSiDto.getTen()))	continue;
			XSSFCell cell = row.createCell(1);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getTen());
			
			cell = row.createCell(2);
			cell.setCellStyle(getStyleContent(workbook));
			cell.setCellValue(chienSiDto.getAmount());
			
			if (commonService.isEmpty(chienSiDto.getDetail()))	continue;
			String[] arrChienSi = chienSiDto.getDetail().split("\\*\\|\\*");
			
			cell = row.createCell(3);
			StringBuilder strDetail = new StringBuilder();
			for (String str : arrChienSi) {
				strDetail.append(str + "\r\n");
			}
			cell.setCellValue(strDetail.toString());
			rowIdx++;
		};
	}
	
	private void createHeaderBelongArmy(XSSFWorkbook workbook, XSSFSheet sheet, String title) {
		rowIdx += DEFAULT_DISTANCE_STATISTICAL;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);

		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue(title);
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B" + (rowIdx + 1) + ":D" + (rowIdx + 1)));
		createHeaderBelongArmyDetail(workbook, sheet);
		rowIdx++;
	}
	
	private void createHeaderBelongArmyDetail(XSSFWorkbook workbook, XSSFSheet sheet) {
		rowIdx++;
		XSSFRow row = getRow(sheet, rowIdx);

		XSSFCell cell = row.createCell(1);
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Đơn vị");
		
		cell = row.createCell(2);
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Số lượng");
		
		cell = row.createCell(3);
		cell.setCellStyle(getStyleMiddle(workbook));
		cell.setCellValue("Chi tiết");
	}
}
