package com.ld575.quanlycsm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.service.DanTocService;

@Controller
@RequestMapping("dan-toc")
public class DanTocController {
	
	@Autowired
	public DanTocService danTocService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model) {
		List<DanTocEntity> listDanToc = danTocService.findAll();
		model.addAttribute("listDanToc", listDanToc);
		return "dantoc/list.html";
	}
	
	@GetMapping("/them")
	public String insert() {
		return "dantoc/form.html";
	}
}
