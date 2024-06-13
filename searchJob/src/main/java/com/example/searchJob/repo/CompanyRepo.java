package com.example.searchJob.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.searchJob.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
	
	//tim kiem cong ty theo dia diem
	@Query("SELECT c FROM Company c WHERE c.address LIKE %:address%")
	List<Company> findCompanyByAddress(@Param("address") String address);
	
	//tim kiem cong ty theo ten
	@Query("SELECT c FROM Company c WHERE c.name LIKE %:name%")
	List<Company> findCompanyByName(@Param("name") String name);
}
