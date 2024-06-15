package com.example.searchJob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.searchJob.dto.JobDTO;
import com.example.searchJob.dto.ResponseDTO;
import com.example.searchJob.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	JobService jobService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<JobDTO> create(@RequestBody JobDTO jobDTO){
		jobService.create(jobDTO);
		
		//tra ve cong ty da tao
		return ResponseDTO.<JobDTO>builder().code(200).data(jobDTO).build();
	}
	
	
	@PutMapping("update")
	public ResponseDTO<JobDTO> update(@RequestBody JobDTO jobDTO){
		
		jobService.update(jobDTO);
		
		return ResponseDTO.<JobDTO>builder().code(200).data(jobDTO).build();
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		jobService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
	@GetMapping("/id")
	public ResponseDTO<JobDTO> get(@PathVariable("id") int id){
		
		return ResponseDTO.<JobDTO>builder().code(200).data(jobService.getById(id)).build();
	}
	
	@GetMapping("/list")
	public ResponseDTO<List<JobDTO>> getList(){
		List<JobDTO> jobDTOs = jobService.getAll();
		
		return ResponseDTO.<List<JobDTO>>builder().code(200).data(jobDTOs).build();
	}
	
	@PostMapping("/searchcompany")
	public ResponseDTO<List<JobDTO>> getListJobByOfCompany( @PathVariable("idCompany") int idCompany){
		List<JobDTO> jobDTOs = jobService.getByJobOfCompany(idCompany);
		
		return ResponseDTO.<List<JobDTO>>builder().code(200).data(jobDTOs).build();
	}
	
	@PostMapping("/searchcate")
	public ResponseDTO<List<JobDTO>> getListJobByOfNameCate( @RequestParam(required = false) String name){
		List<JobDTO> jobDTOs = jobService.getByJobOfNameCate(name);
		
		return ResponseDTO.<List<JobDTO>>builder().code(200).data(jobDTOs).build();
	}
	
	@PostMapping("/searchmajor")
	public ResponseDTO<List<JobDTO>> getListJobByOfMajorCate( @RequestParam(required = false) String major){
		List<JobDTO> jobDTOs = jobService.getByJobOfMajorCate(major);
		
		return ResponseDTO.<List<JobDTO>>builder().code(200).data(jobDTOs).build();
	}
}
