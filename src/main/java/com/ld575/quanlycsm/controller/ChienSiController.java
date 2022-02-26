package com.ld575.quanlycsm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@GetMapping("/read-excel")
	public void readExcel() {
		try (FileInputStream file = new FileInputStream(new File("D:\\Project-Managent-CSM\\Nhập liệu cho phần mềm Quản lý - CSM.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);) {
			
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
			    for (Cell cell : row) {
			        switch (cell.getCellType()) {
			            case STRING:
			            	System.out.print(cell.getStringCellValue() + " - ");
			            	break;
			            default: System.out.println();
			        }
			       
			    }
			    System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private LocalDate convertStrToDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(strDate, formatter);
	}

	private boolean checkValidDate(String strDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(strDate, formatter);
		} catch (DateTimeParseException e) {
			return false;
		}
		return false;
	}
}
