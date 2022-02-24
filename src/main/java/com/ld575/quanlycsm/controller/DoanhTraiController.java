package com.ld575.quanlycsm.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.DoanhTraiService;

@Controller
@RequestMapping("doanh-trai")
public class DoanhTraiController {
	
	@Autowired
	public DoanhTraiService doanhTraiService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model) {
		Iterable<DoanhTraiEntity> listdoanhtrai = doanhTraiService.findAll();
		model.addAttribute("listDoanhTrai", listdoanhtrai);
		return "/doanhtrai/list.html";
	}
	
	@GetMapping("/form")
	public String showInsert(Model model) {
		model.addAttribute("title", "Thêm doanh trại");
		model.addAttribute("doanhtrai", new DoanhTraiEntity());
		return "/doanhtrai/form.html";
	}
	
	@PostMapping("/form")
	public String insert(@ModelAttribute("doanhtrai") DoanhTraiDto doanhtraiDto) {
		doanhTraiService.save(doanhtraiDto);
		return "redirect:/doanh-trai/list";
	}
	
	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id) {
		model.addAttribute("title", "Cập nhật doanh trại");
		Optional<DoanhTraiEntity> DoanhTraiEntity = doanhTraiService.findById(id);
		model.addAttribute("doanhtrai", DoanhTraiEntity.get());
		return "/doanhtrai/form.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Optional<DoanhTraiEntity> DoanhTraiEntity = doanhTraiService.findById(id);
		if (!DoanhTraiEntity.isPresent()) {
			throw new RuntimeException("Id doanh trai not found!");
		}
		doanhTraiService.deleteById(id);
		return "redirect:/doanh-trai/list";
	}
	
	private List<DoanhTraiDto> getListDoanhTraiDto() {
		List<DoanhTraiDto> listDoanhTraiDto = new ArrayList<>();
		Iterable<DoanhTraiEntity> listDoanhTrai = doanhTraiService.findAll();
		Iterator<DoanhTraiEntity> iterator = listDoanhTrai.iterator();
		while (iterator.hasNext()) {
			DoanhTraiEntity doanhTraiEntity = iterator.next();
		}
		return listDoanhTraiDto;
	}
}
