package com.ld575.quanlycsm.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ld575.quanlycsm.dto.DanTocDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.dto.MessageDto;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.service.CommonService;
import com.ld575.quanlycsm.service.DanTocService;

@Controller
@RequestMapping("dan-toc")
public class DanTocController {
	
	@Autowired
	public DanTocService danTocService;
	
	@Autowired
	public CommonService commonService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		List<DanTocEntity> listDanToc = name.isEmpty() ? danTocService.findAll() : danTocService.findByTenContaining(name);
		model.addAttribute("listDanToc", listDanToc);
		model.addAttribute("name", name);
		return "dantoc/list";
	}
	
	@GetMapping("/form")
	public String showInsert(Model model) {
		model.addAttribute("title", "Thêm dân tộc");
		model.addAttribute("dantoc", new DanTocEntity());
		return "dantoc/form.html";
	}
	
	@PostMapping("/form")
	public String insert(@ModelAttribute("dantoc") DanTocDto danTocDto, RedirectAttributes ra) {
		MessageDto messageDto = danTocService.save(danTocDto);
		
		String message = danTocDto.getId() == null ? "Thêm " : "Cập nhật ";
		ra.addFlashAttribute("message", message + messageDto.getMessage());
		if (messageDto.getType() == Flag.FAILED) {
			ra.addFlashAttribute("messageType", "error");
		} else {
			ra.addFlashAttribute("messageType", "success");
		}
		return "redirect:/dan-toc/list";
	}
	
	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id) {
		model.addAttribute("title", "Cập nhật dân tộc");
		Optional<DanTocEntity> danTocEntity = danTocService.findById(id);
		model.addAttribute("dantoc", danTocEntity.get());
		return "dantoc/form.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
		Optional<DanTocEntity> danTocEntity = danTocService.findById(id);
		if (!danTocEntity.isPresent()) {
			ra.addFlashAttribute("message", "Xóa thất bại");
			ra.addFlashAttribute("messageType", "error");
			throw new RuntimeException("Id dan toc not found!");
		}
		danTocService.deleteById(id);
		ra.addFlashAttribute("message", "Xóa thành công!");
		ra.addFlashAttribute("messageType", "success");
		return "redirect:/dan-toc/list";
	}
}
