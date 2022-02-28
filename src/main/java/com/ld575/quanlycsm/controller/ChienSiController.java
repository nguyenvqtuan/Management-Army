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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.ChienSiService;
import com.ld575.quanlycsm.service.DanTocService;
import com.ld575.quanlycsm.service.DoanhTraiService;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@Autowired
	private ChienSiService chienSiService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model) {
		Iterable<ChienSiEntity> listChienSi = chienSiService.findAll();
		model.addAttribute("listChienSi", listChienSi);
		return "/chiensi/list.html";
	}
	
	@GetMapping("/upload")
	public String showImport() {
		return "/chiensi/import.html";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			chienSiService.readExcel(file);
			return "redirect:/chien-si/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/chien-si/list";
		}
	}
}
