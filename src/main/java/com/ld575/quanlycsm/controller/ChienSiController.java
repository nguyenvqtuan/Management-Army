package com.ld575.quanlycsm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.service.ChienSiService;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@Autowired
	private ChienSiService chienSiService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model, @ModelAttribute("chiensi") ChienSiDto chienSiDto) {
		Iterable<ChienSiEntity> listChienSi = chienSiService.findAll();
		model.addAttribute("listChienSi", listChienSi);
		model.addAttribute("chiensi", new ChienSiDto());
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
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Optional<ChienSiEntity> danTocEntity = chienSiService.findById(id);
		if (!danTocEntity.isPresent()) {
			throw new RuntimeException("Id chien si not found!");
		}
		chienSiService.deleteById(id);
		return "redirect:/chien-si/list";
	}
}
