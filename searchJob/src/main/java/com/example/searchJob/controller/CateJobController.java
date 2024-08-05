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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchJob.dto.CateJobDTO;
import com.example.searchJob.dto.JobDTO;
import com.example.searchJob.dto.ResponseDTO;
import com.example.searchJob.service.CateJobService;

@RestController
@RequestMapping("/catejob")
public class CateJobController {
	
	@Autowired
	CateJobService cateJobService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<CateJobDTO> create(@RequestBody CateJobDTO cateJobDTO){
		cateJobService.create(cateJobDTO);
		
		//tra ve cong ty da tao
		return ResponseDTO.<CateJobDTO>builder().code(200).data(cateJobDTO).build();
	}
	
	
	@PutMapping("update")
	public ResponseDTO<CateJobDTO> update(@RequestBody CateJobDTO cateJobDTO){
		
		cateJobService.update(cateJobDTO);
		
		return ResponseDTO.<CateJobDTO>builder().code(200).data(cateJobDTO).build();
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		cateJobService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<CateJobDTO> get(@PathVariable("id") int id){
		
		return ResponseDTO.<CateJobDTO>builder().code(200).data(cateJobService.getById(id)).build();
	}
	
	@GetMapping("/list")
	public ResponseDTO<List<CateJobDTO>> getList(){
		List<CateJobDTO> cateJobDTOs = cateJobService.getAll();
		
		return ResponseDTO.<List<CateJobDTO>>builder().code(200).data(cateJobDTOs).build();
	}
}
