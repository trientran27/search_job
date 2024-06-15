package com.example.searchJob.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dto.ImageDTO;
import com.example.searchJob.entity.Image;
import com.example.searchJob.repo.ImageRepo;



public interface ImageService {
	void create(ImageDTO imageDTO);
	
	void delete(int id);
	
	List<String> getAll(int id_dd);
}
class ImageServiceImpl implements ImageService{
	@Autowired
	ImageRepo imageRepo;

	@Override
	@Transactional
	public void create(ImageDTO imageDTO) {
		Image image = new ModelMapper().map(imageDTO, Image.class);
		imageRepo.save(image);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		imageRepo.deleteById(id);
	}

	@Override
	@Transactional
	public List<String> getAll(int companyId) {
		List<String> images = imageRepo.findJobsByCompanyName(companyId);
		return images;
	}
}
