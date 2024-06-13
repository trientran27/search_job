package com.example.searchJob.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dto.JobDTO;
import com.example.searchJob.entity.Job;
import com.example.searchJob.repo.JobRepo;

public interface JobService {
	void create(JobDTO jobDTO);
	
	void update(JobDTO jobDTO);
	
	void delete(int id);
	
	JobDTO getById(int id);		// job by id
	
	List<JobDTO> getAll();
	
	List<JobDTO> getByJobOfCompany(int companyId);		//get job by company
	
	List<JobDTO> getByJobOfNameCate(String name);
	
	List<JobDTO> getByJobOfMajorCate(String major);
}

class JobServiceImpl implements JobService{
	
	@Autowired
	JobRepo jobRepo;

	@Override
	public void create(JobDTO jobDTO) {
		Job job = new ModelMapper().map(jobDTO, Job.class);
		jobRepo.save(job);
		
	}

	@Override
	@Transactional
	public void update(JobDTO jobDTO) {
		Job job = jobRepo.findById(jobDTO.getId()).orElse(null);
		
		if(job != null) {
			job.setTitle(jobDTO.getTitle());
			
			job.setStartAt(jobDTO.getStartAt());
			job.setEndAt(jobDTO.getEndAt());
			
			job.setDesciption(jobDTO.getDesciption());
			
			jobRepo.save(job);
		}
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		jobRepo.deleteById(id);
		
	}

	@Override
	@Transactional
	public JobDTO getById(int id) {
		
		Job job = jobRepo.findById(id).orElseThrow(NoResultException :: new);
		return convert(job);
	}

	@Override
	@Transactional
	public List<JobDTO> getByJobOfCompany(int companyId) {
		List<Job> jobs = jobRepo.findJobsByCompanyId(companyId);
		return jobs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<JobDTO> getByJobOfNameCate(String name) {
		List<Job> jobs = jobRepo.findJobByNameOfCate(name);
		return jobs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<JobDTO> getByJobOfMajorCate(String major) {
		List<Job> jobs = jobRepo.findJobByMajorOfCate(major);
		return jobs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	
	@Override
	public List<JobDTO> getAll() {
		List<Job> jobs = jobRepo.findAll();
		return jobs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	
	//convert entity sang dto
	private JobDTO convert(Job job) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(job, JobDTO.class);
	}

	
}