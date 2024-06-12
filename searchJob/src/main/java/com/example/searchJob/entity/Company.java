package com.example.searchJob.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String name;
	
	private String address;
	
	private String desciption;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)		//khi catejob xoa thi job cx xoa
	private List<Job> jobs;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)		//khi catejob xoa thi job cx xoa
	private List<Image> images;
}
