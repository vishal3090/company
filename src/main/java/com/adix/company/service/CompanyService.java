package com.adix.company.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.adix.company.dto.CompanyDTO;
import com.adix.company.model.Company;

public interface CompanyService {

	List<Company> getCompany();

	//Optional<Company> findById(Long id);
	ResponseEntity<Company> getById(Long id);

	//Company save(Company company);

	void deleteCompany(Long id);
	
	void updateActive(Long id);

	//ResponseEntity<?> createCompany(@Valid CompanyDTO companyDto);
	Company save(CompanyDTO companyDto);
	
}
