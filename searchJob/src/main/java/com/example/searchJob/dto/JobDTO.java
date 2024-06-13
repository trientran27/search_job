package com.example.searchJob.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class JobDTO {
	private Integer id;
	
	@NotBlank
	private String title;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date startAt;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date endAt;
	
	private String desciption;
	
	private CompanyDTO company;
	
	private CateJobDTO cateJob;
}
