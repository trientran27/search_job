package com.example.searchJob.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.searchJob.entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	
	//tim kiem job thuoc cty
	@Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
	List<Job> findJobsByCompanyId(@Param("companyId") Integer companyId);
	
	//hien chi tiet job theo id
	
	//tim kiem job thuoc chuyen nganh cua cate
	@Query("SELECT j FROM Job j WHERE j.cateJob.major LIKE %:major%")
    List<Job> findJobByMajorOfCate(@Param("major") String major);
	
	//tim kiem job thuoc vi tri(name-cate)
	@Query("SELECT j FROM Job j WHERE j.cateJob.name LIKE %:name%")
    List<Job> findJobByNameOfCate(@Param("name") String name);
}
