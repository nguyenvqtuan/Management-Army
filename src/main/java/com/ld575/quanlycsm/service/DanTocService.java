package com.ld575.quanlycsm.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld575.quanlycsm.dto.DanTocDto;
import com.ld575.quanlycsm.dto.MessageDto;
import com.ld575.quanlycsm.dto.Flag;
import com.ld575.quanlycsm.entity.DanTocEntity;
import com.ld575.quanlycsm.repository.DanTocRepository;

@Service
public class DanTocService {

	private static final String DAN_TOC_DEFAULT = "Kinh,Tày,Thái,Mường,Khmer,Hoa,Nùng,H'Mông,Dao,Gia Rai,Ê Đê,Ba Na,Sán Chay,Chăm,Cơ Ho,Xơ Đăng,Sán Dìu,Hrê,Ra Glai,Mnông,Thổ,Xtiêng,Khơ mú,Bru - Vân Kiều,Cơ Tu,Giáy,Tà Ôi,Mạ,Giẻ-Triêng,Co,Chơ Ro,Xinh Mun,Hà Nhì,Chu Ru,Lào,La Chí,Kháng,Phù Lá,La Hủ,La Ha,Pà Thẻn,Lự,Ngái,Chứt,Lô Lô,Mảng,Cơ Lao,Bố Y,Cống,Si La,Pu Péo,Rơ Măm,Brâu,Ơ Đu";
	
	@Autowired
	public DanTocRepository danTocRepository;
	
	public List<DanTocEntity> findAll() {
		return danTocRepository.findAll();
	}
	
	public MessageDto save(DanTocDto danTocDto) {
		
		Optional<DanTocEntity> danTocByTen = findByTen(danTocDto.getTen());
		if (danTocDto.getId() != null) {
			Optional<DanTocEntity> danTocById = findById(danTocDto.getId());
			if (!danTocByTen.get().getTen().equals(danTocById.get().getTen()) 
					&& danTocByTen.isPresent()) {
				return MessageDto.builder().message(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
			}
		}
		// Check duplicate by name
		if (danTocDto.getId() == null && danTocByTen.isPresent()) {
			return MessageDto.builder().message(Flag.FAILED.name + ". Tên bị trùng").type(Flag.FAILED).build();
		}
		DanTocEntity danToc = DanTocEntity.builder()
			.ten(danTocDto.getTen())
			.moTa(danTocDto.getMoTa())
			.build();
		if (danTocDto.getId() != null) {
			danToc.setId(danTocDto.getId());
		}
		danTocRepository.save(danToc);
		return MessageDto.builder().message(Flag.SUCCESS.name).type(Flag.SUCCESS).build();
	}
	
	public Optional<DanTocEntity> findById(Long id) {
		return danTocRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		danTocRepository.deleteById(id);
	}

	public Optional<DanTocEntity> findByTen(String name) {
		return danTocRepository.findByTen(name);
	}
	
	public List<DanTocEntity> findByTenContaining(String name) {
		return danTocRepository.findByTenContaining(name);
	}
	
	@PostConstruct
	public void saveDefault() {
		String str = DAN_TOC_DEFAULT;
		if (findAll().size() != 0) {
			return;
		}
		String[] arr = str.split(",");
		for (String s : arr) {
			DanTocDto danTocDto = DanTocDto.builder().ten(s).build();
			save(danTocDto);
		}
	}
}
