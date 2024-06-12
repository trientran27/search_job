package com.example.searchJob.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Job {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String title;
	
	private Date startAt;
	
	private Date endAt;
	
	private String desciption;
	
	@ManyToOne		//1 cty nhieu job
	private Company company;
	
	@ManyToOne		//1 loai co nhieu job
	private CateJob cateJob;
	
	
}
