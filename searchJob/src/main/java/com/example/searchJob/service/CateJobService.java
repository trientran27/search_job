package com.example.searchJob.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dto.CateJobDTO;
import com.example.searchJob.dto.JobDTO;
import com.example.searchJob.entity.CateJob;
import com.example.searchJob.entity.Job;
import com.example.searchJob.repo.CateJobRepo;

public interface CateJobService {
	void create(CateJobDTO cateJobDTO);
	
	void update(CateJobDTO cateJobDTO);
	
	void delete(int id);
	
	List<CateJobDTO> getAll();
}

@Service
class CateJobServiceImpl implements CateJobService{
	@Autowired
	CateJobRepo cateJobRepo;

	@Override
	@Transactional
	public void create(CateJobDTO cateJobDTO) {
		CateJob cateJob = new ModelMapper().map(cateJobDTO, CateJob.class);
		cateJobRepo.save(cateJob);
		
	}

	@Override
	@Transactional
	public void update(CateJobDTO cateJobDTO) {
		CateJob cateJob = cateJobRepo.findById(cateJobDTO.getId()).orElse(null);
		if(cateJob != null) {
			cateJob.setName(cateJobDTO.getName());
			
			cateJob.setMajor(cateJobDTO.getMajor());
			
			cateJobRepo.save(cateJob);
		}
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		cateJobRepo.deleteById(id);
		
	}

	@Override
	public List<CateJobDTO> getAll() {
		List<CateJob> cateJobs = cateJobRepo.findAll();
		return cateJobs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	//convert entity sang dto
	private CateJobDTO convert(CateJob cateJob ) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(cateJob, CateJobDTO.class);
	}

	
}
