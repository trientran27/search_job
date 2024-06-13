package com.example.searchJob.dto;

import java.util.List;

import lombok.Data;

@Data
public class CompanyDTO {
	private Integer id;
	
	private String name;
	
	private String address;
	
	private String desciption;
	
	private List<String> imageURLs;
}
