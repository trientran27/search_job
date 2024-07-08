package com.example.searchJob.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dto.CompanyDTO;
import com.example.searchJob.entity.Company;
import com.example.searchJob.repo.CompanyRepo;

public interface CompanyService {
	void create(CompanyDTO companyDTO);
	
	void update(CompanyDTO companyDTO);
	
	void delete(int id);
	
	CompanyDTO getById(int id);
	
	List<CompanyDTO> getCompanyByName(String name);
	
	List<CompanyDTO> getCompanyByAddress(String address);
	
}

@Service
class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyRepo companyRepo;
	
	
	@Override
	@Transactional
	public void create(CompanyDTO companyDTO) {
		Company company = new ModelMapper().map(companyDTO, Company.class);
		companyRepo.save(company);
		
	}

	@Override
	@Transactional
	public void update(CompanyDTO companyDTO) {
		Company company = companyRepo.findById(companyDTO.getId()).orElse(null);
		
		if(company != null) {
			company.setName(companyDTO.getName());
			
			company.setAddress(companyDTO.getAddress());
			
			company.setDesciption(companyDTO.getDesciption());
			
			companyRepo.save(company);
		}
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		companyRepo.deleteById(id);
		
	}

	@Override
	@Transactional
	public CompanyDTO getById(int id) {
		Company company = companyRepo.findById(id).orElseThrow(NoResultException ::new);
		return convert(company);
	}
	
	
	@Override
	@Transactional
	public List<CompanyDTO> getCompanyByName(String name) {
		
		List<Company> companies = companyRepo.findCompanyByName(name);
		return companies.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CompanyDTO> getCompanyByAddress(String address) {
		List<Company> companies = companyRepo.findCompanyByAddress(address);
		return companies.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	

	//convert entity sang dto
	private CompanyDTO convert(Company company) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(company, CompanyDTO.class);
	}

}