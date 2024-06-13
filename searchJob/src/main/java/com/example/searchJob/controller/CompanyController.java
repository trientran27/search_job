package com.example.searchJob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchJob.dto.CompanyDTO;
import com.example.searchJob.dto.ResponseDTO;
import com.example.searchJob.service.CompanyService;
import com.example.searchJob.service.ImageService;

import lombok.Delegate;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	ImageService imageService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<CompanyDTO> create(@RequestBody CompanyDTO companyDTO){
		companyService.create(companyDTO);
		
		//tra ve cong ty da tao
		return ResponseDTO.<CompanyDTO>builder().code(200).data(companyDTO).build();
	}
	
	
	@PutMapping("update")
	public ResponseDTO<CompanyDTO> update(@RequestBody CompanyDTO companyDTO){
		
		companyService.update(companyDTO);
		
		return ResponseDTO.<CompanyDTO>builder().code(200).data(companyDTO).build();
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		companyService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
	@GetMapping("/id")
	public ResponseDTO<CompanyDTO> get(@PathVariable("id") int id){
		
		return ResponseDTO.<CompanyDTO>builder().code(200).data(companyService.getById(id)).build();
	}
	
	@PostMapping("/search")
	public ResponseDTO<List<CompanyDTO>> searchByName(@RequestParam(required = false) String name){
		
		return ResponseDTO.<List<CompanyDTO>>builder().code(HttpStatus.OK.value()).build();
	}
	
}
