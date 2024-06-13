package com.example.searchJob.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.searchJob.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
