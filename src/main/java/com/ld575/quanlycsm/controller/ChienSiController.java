package com.ld575.quanlycsm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ld575.quanlycsm.dto.ChienSiDto;
import com.ld575.quanlycsm.dto.ChienSiInsertDto;
import com.ld575.quanlycsm.dto.DoanhTraiDto;
import com.ld575.quanlycsm.dto.TrinhDoDto;
import com.ld575.quanlycsm.entity.ChienSiEntity;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.entity.DoanhTraiEntity;
import com.ld575.quanlycsm.service.ChienSiService;
import com.ld575.quanlycsm.service.DanTocService;
import com.ld575.quanlycsm.service.DoanhTraiService;
import com.ld575.quanlycsm.service.ExportHelper;

@Controller
@RequestMapping("/chien-si")
public class ChienSiController {

	@Autowired
	private ChienSiService chienSiService;

	@Autowired
	private DanTocService danTocService;

	@Autowired
	private DoanhTraiService doanhTraiService;

	@Autowired
	private ExportHelper exportHelper;

	@GetMapping(value = { "/", "/list" })
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
		return "chiensi/list";
	}

	@GetMapping("/upload")
	public String showImport() {
		return "chiensi/import";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
		try {
			chienSiService.readExcel(file);
			ra.addFlashAttribute("message", "Import thành công!");
			ra.addFlashAttribute("messageType", "success");
			return "redirect:/chien-si/list";
		} catch (Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("message", "Import thất bại!");
			ra.addFlashAttribute("messageType", "error");
			return "redirect:/chien-si/list";
		}
	}

	@GetMapping("/form")
	public String insert(Model model) {
		model.addAttribute("title", "Thêm chiến sĩ");
		model.addAttribute("chiensi", new ChienSiInsertDto());
		model.addAttribute("listTrinhDo", getListTrinhDo());
		model.addAttribute("listDanToc", getListDanToc());
		model.addAttribute("listDaiDoi", getListDoanhTraiDaiDoi());
		return "chiensi/form";
	}

	@PostMapping("/form")
	public String insert(Model model, @Valid @ModelAttribute("chiensi") ChienSiInsertDto chienSiDto, BindingResult result, 
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			ra.addFlashAttribute("message", "Thêm thất bại!");
			ra.addFlashAttribute("messageType", "error");
			getInfoUpdate(model, chienSiDto);
			return "chiensi/form";
		}
		chienSiService.save(chienSiDto);
		String message = chienSiDto.getId() != 0L ? "Cập nhật " : "Thêm ";
		ra.addFlashAttribute("message", message + " thành công!");
		ra.addFlashAttribute("messageType", "success");
		return "redirect:/chien-si/list";
	}

	@GetMapping("/form/{id}")
	public String showUpdate(Model model, @PathVariable("id") Long id) {
		model.addAttribute("title", "Cập nhật chiến sĩ");
		Optional<ChienSiEntity> chienSiEntity = chienSiService.findById(id);
		ChienSiInsertDto chienSiDto = getChienSiDto(chienSiEntity.get());
		getInfoUpdate(model, chienSiDto);
		return "/chiensi/form";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
		Optional<ChienSiEntity> danTocEntity = chienSiService.findById(id);
		if (!danTocEntity.isPresent()) {
			ra.addFlashAttribute("message", "Xóa thất bại!");
			ra.addFlashAttribute("messageType", "error");
			throw new RuntimeException("Id chien si not found!");
		}
		chienSiService.deleteById(id);
		ra.addFlashAttribute("message", "Xóa thành công!");
		ra.addFlashAttribute("messageType", "success");
		return "redirect:/chien-si/list";
	}

	@GetMapping("/download")
	public String showExport() {
		return "chiensi/download";
	}

	@PostMapping("/download")
	public void export(HttpServletResponse response, @RequestParam("namNhapNgu") String namNhapNgu, RedirectAttributes ra)
			throws IOException {
		ByteArrayInputStream byteArrayInputStream = exportHelper.export(namNhapNgu);
		response.setContentType("application/octet-stream");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = now.format(format);  
		response.setHeader("Content-Disposition", "attachment; filename=thong-ke-csm-" + namNhapNgu + " _" + formatDateTime + ".xlsx");
		IOUtils.copy(byteArrayInputStream, response.getOutputStream());
		ra.addFlashAttribute("message", "Download thành công!");
		ra.addFlashAttribute("messageType", "success");
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

	private ChienSiInsertDto getChienSiDto(ChienSiEntity chienSiEntity) {
		String[] arrIdDoanhTrai = chienSiEntity.getDoanhTrai().getStrIdTrucThuoc().split("-");
		return ChienSiInsertDto.builder().id(chienSiEntity.getId()).hoTen(chienSiEntity.getHoTen())
				.ngaySinh(chienSiEntity.getNgaySinh()).capBac(chienSiEntity.getCapBac())
				.thoiGianNhanCapBac(chienSiEntity.getThoiGianNhanCapBac()).chucVu(chienSiEntity.getChucVu())
				.ngayNhapNgu(chienSiEntity.getNgayNhapNgu()).sucKhoe(chienSiEntity.getSucKhoe())
				.ngayVaoDang(chienSiEntity.getNgayVaoDang())
				.ngayVaoDangChinhThuc(chienSiEntity.getNgayVaoDangChinhThuc()).soTheDang(chienSiEntity.getSoTheDang())
				.ngayVaoDoan(chienSiEntity.getNgayVaoDoan()).ngheNghiepGiaDinh(chienSiEntity.getNgheNghiepGiaDinh())
				.coMayAnhChiEm(chienSiEntity.getCoMayAnhChiEm()).conThuMayTrongNha(chienSiEntity.getConThuMayTrongNha())
				.coVo(Character.toString(chienSiEntity.getCoVo())).ghiChuCoVo(chienSiEntity.getGhiChuCoVo())
				.hoTenCha(chienSiEntity.getHoTenCha()).namSinhCha(chienSiEntity.getNamSinhCha())
				.ngheNghiepCha(chienSiEntity.getNgheNghiepCha()).hoTenMe(chienSiEntity.getHoTenMe())
				.namSinhMe(chienSiEntity.getNamSinhMe()).ngheNghiepMe(chienSiEntity.getNgheNghiepMe())
				.boMat(chienSiEntity.getBoMat()).meMat(chienSiEntity.getMeMat()).boMeLiDi(chienSiEntity.getBoMeLiDi())
				.khongCoBo(chienSiEntity.getKhongCoBo()).giaDinhAnhHuongCovid(chienSiEntity.getGiaDinhAnhHuongCovid())
				.giaDinhKhoKhan(chienSiEntity.getGiaDinhKhoKhan())
				.nguoiQuenTrongQuanDoi(chienSiEntity.getNguoiQuenTrongQuanDoi())
				.boMeLaLietSiHoacQuanNhan(chienSiEntity.getBoMeLaLietSiHoacQuanNhan())
				.ngheNghiepBanThan(chienSiEntity.getNgheNghiepBanThan()).trinhDo(chienSiEntity.getTrinhDo())
				.daQuaTruong(chienSiEntity.getDaQuaTruong()).qqPhuongXa(chienSiEntity.getQqPhuongXa())
				.qqQuanHuyen(chienSiEntity.getQqQuanHuyen()).qqTinhThanh(chienSiEntity.getQqTinhThanh())
				.nohnPhuongXa(chienSiEntity.getNohnPhuongXa()).nohnQuanHuyen(chienSiEntity.getNohnQuanHuyen())
				.nohnTinhThanh(chienSiEntity.getNohnTinhThanh()).hinhXam(chienSiEntity.getHinhXam())
				.giuBua(chienSiEntity.getGiuBua()).nguoiYeu(chienSiEntity.getNguoiYeu())
				.hutThuoc(chienSiEntity.getHutThuoc()).soTruong(chienSiEntity.getSoTruong())
				.ghiChu(chienSiEntity.getGhiChu()).idDanToc(chienSiEntity.getDanToc().getId())
				.idDoanhTrai(chienSiEntity.getDoanhTrai().getId())
				.idDaiDoi(doanhTraiService.findById(Long.parseLong(arrIdDoanhTrai[1])).get().getId())
				.idTrungDoi(doanhTraiService.findById(Long.parseLong(arrIdDoanhTrai[0])).get().getId())
				.idTieuDoi(chienSiEntity.getDoanhTrai().getId()).build();
	}
	
	private void getInfoUpdate(Model model, ChienSiInsertDto chienSiDto) {
		model.addAttribute("chiensi", chienSiDto);
		model.addAttribute("listTrinhDo", getListTrinhDo());
		model.addAttribute("listDanToc", getListDanToc());
		model.addAttribute("listDaiDoi", getListDoanhTraiDaiDoi());

		List<DoanhTraiDto> listTrungDoi;
		if (chienSiDto.getIdDaiDoi() != 0L) {
			listTrungDoi = getListDoanhTraiTrungDoi(chienSiDto.getIdDaiDoi());
		} else {
			listTrungDoi = new ArrayList<>();
		}
		model.addAttribute("listTrungDoi", listTrungDoi);

		List<DoanhTraiDto> listTieuDoi;
		if (chienSiDto.getIdTrungDoi() != 0L) {
			listTieuDoi = getListDoanhTraiTieuDoi(chienSiDto.getIdTrungDoi());
		} else {
			listTieuDoi = new ArrayList<>();
		}
		model.addAttribute("listTieuDoi", listTieuDoi);
	}
}
