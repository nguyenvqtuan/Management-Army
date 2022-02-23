package com.ld575.quanlycsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dan-toc")
public class DanTocController {
	
	@GetMapping(value = {"/", "/list"})
	public String list() {
		return "dantoc/list.html";
	}
	
	@GetMapping("/them")
	public String insert() {
		return "dantoc/form.html";
	}
}
