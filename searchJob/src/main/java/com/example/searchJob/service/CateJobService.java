package com.example.searchJob.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dto.CateJobDTO;
import com.example.searchJob.dto.PageResponse;
import com.example.searchJob.entity.CateJob;
import com.example.searchJob.repo.CateJobRepo;
import com.example.searchJob.repo.PageAndSearchRespository;

import lombok.RequiredArgsConstructor;

public interface CateJobService {
	void create(CateJobDTO cateJobDTO);
	
	void update(CateJobDTO cateJobDTO);
	
	void delete(int id);
	
	CateJobDTO getById(int id);
	
//	List<CateJobDTO> getAll();
	PageResponse<?> getAll(int offset, int pageSize, String search);
}

@Service
@RequiredArgsConstructor
class CateJobServiceImpl implements CateJobService{
	
	private final CateJobRepo cateJobRepo;
	
	private final PageAndSearchRespository searchRespository;

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
	@Transactional
	public CateJobDTO getById(int id) {
		
		CateJob cateJob = cateJobRepo.findById(id).orElseThrow(NoResultException :: new);
		return convert(cateJob);
	}

	
	//convert entity sang dto
	private CateJobDTO convert(CateJob cateJob ) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(cateJob, CateJobDTO.class);
	}

	@Override
	public PageResponse<?> getAll(int offset, int pageSize, String search) {
		
		return searchRespository.searchCateJobByKeyword(offset, pageSize, search);
	}

	
}
