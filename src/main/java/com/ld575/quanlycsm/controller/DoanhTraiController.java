package com.ld575.quanlycsm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ld575.quanlycsm.dto.CapDoDto;
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
		model.addAttribute("listDoanhTrai", getListDoanhTraiDto());
		return "/doanhtrai/list.html";
	}
	
	@GetMapping("/form")
	public String showInsert(Model model) {
		model.addAttribute("title", "Thêm doanh trại");
		model.addAttribute("doanhTrai", new DoanhTraiEntity());
		model.addAttribute("listCapDo", getCapDo());
		return "/doanhtrai/form.html";
	}
	
	@PostMapping("/form")
	public String insert(@ModelAttribute("doanhtrai") DoanhTraiDto doanhtraiDto) {
		doanhTraiService.save(doanhtraiDto);
		return "redirect:/doanh-trai/list";
	}
	
	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id, @RequestParam("strTrucThuoc") String strTrucThuoc) {
		model.addAttribute("title", "Cập nhật doanh trại");
		Optional<DoanhTraiEntity> doanhTraiEntity = doanhTraiService.findById(id);
//		List<DoanhTraiDto> listDoanhTraiEntity = getListDoanhTraiDto();
//		for (DoanhTraiDto doanhTrai : listDoanhTraiEntity) {
//			if (doanhTrai.getId().equals(doanhTraiEntity.get().getId())) {
//				
//				break;
//			}
//		}
		model.addAttribute("doanhTrai", doanhTraiEntity.get());
		model.addAttribute("listCapDo", getCapDo());
		model.addAttribute("strTrucThuoc", strTrucThuoc);
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
	
	@GetMapping("getTrucThuoc/{level}")
	public ResponseEntity<?> getTrucThuoc(@PathVariable("level") Integer level) {
		List<DoanhTraiEntity> listDoanhTraiByLevel = doanhTraiService.findByLevel(level);
		List<DoanhTraiDto> listDoanhTraiEntity = getListDoanhTraiDto();
		List<DoanhTraiDto> res = new ArrayList<>();
		for (DoanhTraiEntity doanhTrai : listDoanhTraiByLevel) {
			String strTrucThuoc = "";
			for (DoanhTraiDto doanhTraiEntity : listDoanhTraiEntity) {
				if (doanhTraiEntity.getId().equals(doanhTrai.getId())) {
					strTrucThuoc = doanhTraiEntity.getStrTrucThuoc();
					break;
				}
			}
			res.add(DoanhTraiDto.builder().id(doanhTrai.getId()).strTrucThuoc(doanhTrai.getTenDayDu() + " - " + strTrucThuoc).build());
		}
		return ResponseEntity.ok(res);
	}
	
	private List<DoanhTraiDto> getListDoanhTraiDto() {
		List<DoanhTraiDto> listDoanhTraiDto = new ArrayList<>();
		Iterable<DoanhTraiEntity> listDoanhTrai = doanhTraiService.findAll();
		Iterator<DoanhTraiEntity> iterator = listDoanhTrai.iterator();
		Map<Long, Long> mapTrucThuoc = new HashMap<>();
		Map<Long, String> mapTrucThuocStr = new HashMap<>();
		
		while (iterator.hasNext()) {
			DoanhTraiEntity doanhTraiEntity = iterator.next();
			mapTrucThuoc.put(doanhTraiEntity.getId(), doanhTraiEntity.getTrucThuoc());
			mapTrucThuocStr.put(doanhTraiEntity.getId(), doanhTraiEntity.getTenDayDu());
		}
		
		iterator = listDoanhTrai.iterator();
		while (iterator.hasNext()) {
			DoanhTraiEntity doanhTraiEntity = iterator.next();
			
			StringBuilder strTrucThuoc = new StringBuilder();
			Long current = doanhTraiEntity.getId();
			while (true) {
				if (mapTrucThuoc.get(current) == 0) {
					break;
				}
				current = mapTrucThuoc.get(current);
				if (strTrucThuoc.length() != 0) {
					strTrucThuoc.append(" - ");
				}
				strTrucThuoc.append(mapTrucThuocStr.get(current));
			}
			
			DoanhTraiDto doanhTraiDto = DoanhTraiDto.builder()
					.id(doanhTraiEntity.getId())
					.ten(doanhTraiEntity.getTen())
					.tenDayDu(doanhTraiEntity.getTenDayDu())
					.trucThuoc(doanhTraiEntity.getTrucThuoc())
					.strTrucThuoc(strTrucThuoc.toString())
					.build();
			listDoanhTraiDto.add(doanhTraiDto);
		}
		return listDoanhTraiDto;
	}
	
	private List<CapDoDto> getCapDo() {
		List<CapDoDto> res = new ArrayList<>();
		res.add(new CapDoDto(5, "-"));
		res.add(new CapDoDto(4, "Tiểu đoàn"));
		res.add(new CapDoDto(3, "Đại đội"));
		res.add(new CapDoDto(2, "Trung đội"));
		res.add(new CapDoDto(1, "Tiểu đội"));
		return res;
	}
}
