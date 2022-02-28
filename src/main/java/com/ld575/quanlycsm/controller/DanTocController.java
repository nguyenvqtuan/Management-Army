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

import com.ld575.quanlycsm.dto.DanTocDto;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.service.DanTocService;

@Controller
@RequestMapping("dan-toc")
public class DanTocController {
	
	@Autowired
	public DanTocService danTocService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model) {
		String str = "Việt,Tày,Thái,Mường,Khmer,Hoa,Nùng,H'Mông,Dao,Gia Rai,Ê Đê,Ba Na,Sán Chay,Chăm,Cơ Ho,Xơ Đăng,Sán Dìu,Hrê,Ra Glai,Mnông,Thổ,Xtiêng,Khơ mú,Bru - Vân Kiều,Cơ Tu,Giáy,Tà Ôi,Mạ,Giẻ-Triêng,Co,Chơ Ro,Xinh Mun,Hà Nhì,Chu Ru,Lào,La Chí,Kháng,Phù Lá,La Hủ,La Ha,Pà Thẻn,Lự,Ngái,Chứt,Lô Lô,Mảng,Cơ Lao,Bố Y,Cống,Si La,Pu Péo,Rơ Măm,Brâu,Ơ Đu";
		String[] arr = str.split(",");
		for (String s : arr) {
			DanTocDto danTocDto = DanTocDto.builder().ten(s).build();
			danTocService.save(danTocDto);
		}
		Iterable<DanTocEntity> listDanToc = danTocService.findAll();
		model.addAttribute("listDanToc", listDanToc);
		return "/dantoc/list.html";
	}
	
	@GetMapping("/form")
	public String showInsert(Model model) {
		model.addAttribute("title", "Thêm dân tộc");
		model.addAttribute("dantoc", new DanTocEntity());
		return "/dantoc/form.html";
	}
	
	@PostMapping("/form")
	public String insert(@ModelAttribute("dantoc") DanTocDto danTocDto) {
		danTocService.save(danTocDto);
		return "redirect:/dan-toc/list";
	}
	
	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id) {
		model.addAttribute("title", "Cập nhật dân tộc");
		Optional<DanTocEntity> danTocEntity = danTocService.findById(id);
		model.addAttribute("dantoc", danTocEntity.get());
		return "/dantoc/form.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Optional<DanTocEntity> danTocEntity = danTocService.findById(id);
		if (!danTocEntity.isPresent()) {
			throw new RuntimeException("Id dan toc not found!");
		}
		danTocService.deleteById(id);
		return "redirect:/dan-toc/list";
	}
}
