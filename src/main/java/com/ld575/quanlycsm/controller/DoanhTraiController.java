package com.ld575.quanlycsm.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ld575.quanlycsm.dto.CapDoDto;
import com.ld575.quanlycsm.dto.CapDoEnum;
import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.dto.MessageDto;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.CommonService;
import com.ld575.quanlycsm.service.DoanhTraiService;

@Controller
@RequestMapping("doanh-trai")
public class DoanhTraiController {
	
	@Autowired
	public DoanhTraiService doanhTraiService;
	
	@Autowired
	public CommonService commonService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model, 
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "capDo", required = false, defaultValue = "0") String level) {
		
		List<DoanhTraiEntity> list = doanhTraiService.search(name, Integer.valueOf(level));
		
		model.addAttribute("listDoanhTrai", convertDoanhTraiEntity(list));
		model.addAttribute("listCapDo", doanhTraiService.getLevel());
		model.addAttribute("capDo", level);
		model.addAttribute("name", name);
		
		return "doanhtrai/list.html";
	}
	
	@GetMapping("/truc-thuoc/{id}")
	public ResponseEntity<?> trucThuoc(@PathVariable("id") Long id) {
		List<DoanhTraiEntity> listDoanhTraiEntity = doanhTraiService.findByTrucThuoc(id);
		String tenDayDu = "";
		if (!listDoanhTraiEntity.isEmpty()) {
			String[] arr = listDoanhTraiEntity.get(0).getTenDayDu().split(" ");
			tenDayDu = arr[0] + " " + arr[1];
		}
		
		List<DoanhTraiDto> listDoanhTrai = listDoanhTraiEntity.stream().map(e -> {
			return DoanhTraiDto.builder().id(e.getId()).tenDayDu(e.getTenDayDu()).build();
		}).collect(Collectors.toList());
		listDoanhTrai.add(0, DoanhTraiDto.builder().id(0L).tenDayDu(tenDayDu).build());
		return ResponseEntity.ok(listDoanhTrai);
	}
	
	@GetMapping("/form")
	public String showInsert(Model model) {
		model.addAttribute("title", "Thêm doanh trại");
		model.addAttribute("doanhTrai", new DoanhTraiEntity());
		model.addAttribute("listCapDo", doanhTraiService.getLevel());
		return "doanhtrai/form";
	}
	
	@PostMapping("/form")
	public String insert(@Valid @ModelAttribute("doanhtrai") DoanhTraiDto doanhTraiDto, RedirectAttributes ra, 
			BindingResult result) {
		if (result.hasErrors()) {
			ra.addFlashAttribute("message", "Thêm thất bại!");
			ra.addFlashAttribute("messageType", "error");
			return "doanhtrai/form";
		}
		
		MessageDto messageDto = doanhTraiService.save(doanhTraiDto);
		String message = doanhTraiDto.getId() == null ? "Thêm " : "Cập nhật " + messageDto.getMessage();
		ra.addFlashAttribute("message", message);
		if (messageDto.getType() == Flag.FAILED) {
			ra.addFlashAttribute("messageType", "error");
		} else {
			ra.addFlashAttribute("messageType", "success");
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:/doanh-trai/list";
	}
	
	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id, @RequestParam("tenDayDuTrucThuoc") String tenDayDuTrucThuoc) {
		model.addAttribute("title", "Cập nhật doanh trại");
		Optional<DoanhTraiEntity> doanhTraiEntity = doanhTraiService.findById(id);
		model.addAttribute("doanhTrai", doanhTraiEntity.get());
		model.addAttribute("listCapDo", doanhTraiService.getLevel());
		model.addAttribute("tenDayDuTrucThuoc", tenDayDuTrucThuoc);
		return "doanhtrai/form.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
		Optional<DoanhTraiEntity> DoanhTraiEntity = doanhTraiService.findById(id);
		if (!DoanhTraiEntity.isPresent()) {
			ra.addFlashAttribute("message", "Xóa thất bại!");
			ra.addFlashAttribute("messageType", "error");
			throw new RuntimeException("Id doanh trai not found!");
		}
		doanhTraiService.deleteById(id);
		ra.addFlashAttribute("message", "Xóa thành công!");
		ra.addFlashAttribute("messageType", "success");
		return "redirect:/doanh-trai/list";
	}
	
	@GetMapping("get-truc-thuoc-greater/{level}")
	public ResponseEntity<?> getTrucThuocGreater(@PathVariable("level") String level) {
		List<CapDoDto> res = getTrucThuocGreater(Integer.valueOf(level));
		return ResponseEntity.ok(res);
	}
	
	public List<CapDoDto> getTrucThuocGreater(Integer level) {
		int i = 1;
		List<CapDoDto> res = new ArrayList<>();
		for (CapDoEnum e : CapDoEnum.values()) {
			if (i > level) {
				res.add(new CapDoDto(i, e + " - " + CapDoDto.MAPPING));
			}
			i++;
		}
		return res;
	}
	
	@GetMapping("get-truc-thuoc/{level}")
	public ResponseEntity<?> getTrucThuoc(@PathVariable("level") Integer level) {
		List<DoanhTraiEntity> listDoanhTraiByLevel = doanhTraiService.findByCapDo(level);
		List<DoanhTraiDto> listDoanhTraiEntity = convertDoanhTraiEntity(doanhTraiService.findAll());
		List<DoanhTraiDto> res = new ArrayList<>();
		for (DoanhTraiEntity doanhTrai : listDoanhTraiByLevel) {
			String tenDayDuTrucThuoc = "";
			String strIdTrucThuoc = "";
			String tenTrucThuoc = "";
			for (DoanhTraiDto doanhTraiEntity : listDoanhTraiEntity) {
				if (doanhTraiEntity.getId().equals(doanhTrai.getId())) {
					tenDayDuTrucThuoc = doanhTraiEntity.getTenDayDuTrucThuoc();
					tenTrucThuoc = doanhTraiEntity.getTenTrucThuoc();
					strIdTrucThuoc = doanhTraiEntity.getStrIdTrucThuoc();
					break;
				}
			}
			String fullTenDayDuTrucThuoc = doanhTrai.getTenDayDu();
			String fullTenTrucThuoc = doanhTrai.getTen();
			String fullStrIdTrucThuoc = doanhTrai.getId() + "";
			if (!commonService.isEmpty(tenDayDuTrucThuoc)) {
				fullTenDayDuTrucThuoc += " - " + tenDayDuTrucThuoc;
			}
			if (!commonService.isEmpty(tenTrucThuoc)) {
				fullTenTrucThuoc += "-" + tenTrucThuoc;
			}
			if (!commonService.isEmpty(strIdTrucThuoc)) {
				fullStrIdTrucThuoc += "-" + strIdTrucThuoc;
			}
			res.add(DoanhTraiDto.builder().id(doanhTrai.getId())
					.strIdTrucThuoc(fullStrIdTrucThuoc)
					.tenDayDuTrucThuoc(fullTenDayDuTrucThuoc)
					.tenTrucThuoc(fullTenTrucThuoc)
					.build());
		}
		return ResponseEntity.ok(res);
	}
	
	private List<DoanhTraiDto> convertDoanhTraiEntity(List<DoanhTraiEntity> listDoanhTraiEntity) {
		List<DoanhTraiDto> listDoanhTraiDto = new ArrayList<>();
		Iterator<DoanhTraiEntity> iterator = listDoanhTraiEntity.iterator();
		
		iterator = listDoanhTraiEntity.iterator();
		while (iterator.hasNext()) {
			DoanhTraiEntity doanhTraiEntity = iterator.next();
			
			DoanhTraiDto doanhTraiDto = DoanhTraiDto.builder()
					.id(doanhTraiEntity.getId())
					.ten(doanhTraiEntity.getTen())
					.tenDayDu(doanhTraiEntity.getTenDayDu())
					.trucThuoc(doanhTraiEntity.getTrucThuoc())
					.tenTrucThuoc(doanhTraiEntity.getTenTrucThuoc())
					.tenDayDuTrucThuoc(doanhTraiEntity.getTenDayDuTrucThuoc())
					.build();
			listDoanhTraiDto.add(doanhTraiDto);
		}
		return listDoanhTraiDto;
	}
}
