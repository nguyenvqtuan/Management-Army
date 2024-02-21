package com.ld575.quanlycsm.controller.statichtml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticHTMLController {

	@GetMapping("/frame")
	public String frame() {
		return "convert/frame";
	}
	
	@GetMapping("/form")
	public String form() {
		return "convert/form";
	}
	
	@GetMapping("/footer")
	public String footer() {
		return "convert/footer";
	}
	
	@GetMapping("/nvarbar")
	public String nvarbar() {
		return "convert/nvarbar";
	}
	
	@GetMapping("/sidebar")
	public String sidebar() {
		return "convert/sidebar";
	}
	
	@GetMapping("/table")
	public String table() {
		return "convert/table";
	}
}
