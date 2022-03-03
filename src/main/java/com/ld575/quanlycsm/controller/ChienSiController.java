package com.ld575.quanlycsm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.dto.TrinhDoDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.ChienSiService;
import com.ld575.quanlycsm.service.DanTocService;
import com.ld575.quanlycsm.service.DoanhTraiService;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@Autowired
	private ChienSiService chienSiService;
	
	@Autowired
	private DanTocService danTocService;
	
	@Autowired
	private DoanhTraiService doanhTraiService;
	
	@GetMapping(value = {"/", "/list"})
	public String list(Model model, @ModelAttribute("chiensi") ChienSiDto chienSiDto) {
		List<ChienSiEntity> listChienSi = chienSiService.findByCondition(chienSiDto);
		model.addAttribute("listChienSi", listChienSi);
		model.addAttribute("listTrinhDo", getListTrinhDo());
		model.addAttribute("listDanToc", getListDanToc());
		model.addAttribute("listDaiDoi", getListDoanhTraiDaiDoi());
		
		List<DoanhTraiDto> listTrungDoi;
		if (chienSiDto.getIdDaiDoi() != null && chienSiDto.getIdDaiDoi() != 0L) {
			listTrungDoi = getListDoanhTraiTrungDoi(chienSiDto.getIdDaiDoi());
		} else {
			listTrungDoi = new ArrayList<>();
		}
		model.addAttribute("listTrungDoi", listTrungDoi);
		
		List<DoanhTraiDto> listTieuDoi;
		if (chienSiDto.getIdTrungDoi() != null && chienSiDto.getIdTrungDoi() != 0L) {
			listTieuDoi = getListDoanhTraiTieuDoi(chienSiDto.getIdTrungDoi());
		} else {
			listTieuDoi = new ArrayList<>();
		}
		model.addAttribute("listTieuDoi", listTieuDoi);
		model.addAttribute("chiensi", chienSiDto == null ? new ChienSiDto() : chienSiDto);
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
	
	private List<TrinhDoDto> getListTrinhDo() {
		List<TrinhDoDto> listTrinhDo = new ArrayList<>();
		listTrinhDo.add(new TrinhDoDto("", "Trình độ"));
		listTrinhDo.add(new TrinhDoDto("ĐH", "ĐH"));
		listTrinhDo.add(new TrinhDoDto("CĐ", "CĐ"));
		listTrinhDo.add(new TrinhDoDto("TC", "TC"));
		listTrinhDo.add(new TrinhDoDto("12", "12"));
		listTrinhDo.add(new TrinhDoDto("11", "11"));
		listTrinhDo.add(new TrinhDoDto("10", "10"));
		listTrinhDo.add(new TrinhDoDto("9", "9"));
		listTrinhDo.add(new TrinhDoDto("8", "8"));
		listTrinhDo.add(new TrinhDoDto("7", "7"));
		listTrinhDo.add(new TrinhDoDto("6", "6"));
		listTrinhDo.add(new TrinhDoDto("5", "5"));
		listTrinhDo.add(new TrinhDoDto("4", "4"));
		listTrinhDo.add(new TrinhDoDto("3", "3"));
		listTrinhDo.add(new TrinhDoDto("2", "2"));
		listTrinhDo.add(new TrinhDoDto("1", "1"));
		return listTrinhDo;
	}
	
	private List<DanTocEntity> getListDanToc() {
		Iterable<DanTocEntity> listDanToc = danTocService.findAll();
		List<DanTocEntity> res = new ArrayList<>();
		res.add(DanTocEntity.builder().id(0L).ten("Dân tộc").build());
		for (DanTocEntity danTocEntity : listDanToc) {
			res.add(danTocEntity);
		}
		return res;
	}
	
	private List<DoanhTraiDto> getListDoanhTraiDaiDoi() {
		List<DoanhTraiEntity> listDoanhTraiEntity = doanhTraiService.findByLevel(3);
		List<DoanhTraiDto> listDoanhTrai = listDoanhTraiEntity.stream().map(e -> {
			return DoanhTraiDto.builder().id(e.getId()).tenDayDu(e.getTenDayDu()).build();
		}).collect(Collectors.toList());
		
		listDoanhTrai.add(0, DoanhTraiDto.builder().id(0L).tenDayDu("Đại đội").build());
		return listDoanhTrai;
	}
	
	private List<DoanhTraiDto> getListDoanhTraiTrungDoi(Long idDaiDoi) {
		List<DoanhTraiEntity> listDoanhTraiEntity = doanhTraiService.findByCapDoAndTrucThuoc(2, idDaiDoi);
		List<DoanhTraiDto> listDoanhTrai = listDoanhTraiEntity.stream().map(e -> {
			return DoanhTraiDto.builder().id(e.getId()).tenDayDu(e.getTenDayDu()).build();
		}).collect(Collectors.toList());
		
		listDoanhTrai.add(0, DoanhTraiDto.builder().id(0L).tenDayDu("Trung đội").build());
		return listDoanhTrai;
	}
	
	private List<DoanhTraiDto> getListDoanhTraiTieuDoi(Long idTrungDoi) {
		List<DoanhTraiEntity> listDoanhTraiEntity = doanhTraiService.findByCapDoAndTrucThuoc(1, idTrungDoi);
		List<DoanhTraiDto> listDoanhTrai = listDoanhTraiEntity.stream().map(e -> {
			return DoanhTraiDto.builder().id(e.getId()).tenDayDu(e.getTenDayDu()).build();
		}).collect(Collectors.toList());
		
		listDoanhTrai.add(0, DoanhTraiDto.builder().id(0L).tenDayDu("Tiểu đội").build());
		return listDoanhTrai;
	}
}
