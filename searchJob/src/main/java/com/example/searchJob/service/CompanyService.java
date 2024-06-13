package com.example.searchJob.service;

import org.springframework.stereotype.Service;

import com.example.searchJob.dto.CompanyDTO;

public interface CompanyService {
	void create(CompanyDTO companyDTO);
	
	void update(CompanyDTO companyDTO);
	
	void delete(int id);
	
}

@Service
class CompanyServiceImpl implements CompanyService{

	@Override
	public void create(CompanyDTO companyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CompanyDTO companyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}