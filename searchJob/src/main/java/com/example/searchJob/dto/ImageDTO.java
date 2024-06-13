package com.example.searchJob.dto;


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ImageDTO {
	private Integer id;
    
    private String imageURL;
    
    @JsonIgnore
	private MultipartFile file;
   
    private CompanyDTO company;
}
