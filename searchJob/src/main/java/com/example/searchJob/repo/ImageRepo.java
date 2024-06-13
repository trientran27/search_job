package com.example.searchJob.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.searchJob.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	//anh thuoc cty
	@Query("SELECT i.imageURL FROM Image i WHERE i.company.id = :companyId")
	List<String> findJobsByCompanyName(@Param("companyId") Integer companyId);
}
