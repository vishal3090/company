package com.adix.company.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adix.company.dto.CompanyDTO;
import com.adix.company.exception.CompanyNotFoundException;
import com.adix.company.mapper.CompanyMapper;
import com.adix.company.model.Company;
import com.adix.company.service.CompanyServiceImpl;

@RestController
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CompanyServiceImpl companyService;

	@GetMapping(value = "/company")
	public List<CompanyDTO> getCompany() {

		return companyService.getCompany().stream().map(company -> modelMapper.map(company, CompanyDTO.class))
				.collect(Collectors.toList());
	}


	/*@GetMapping(value = "/company/{id}")
	public ResponseEntity<Company> findById(@PathVariable("id") Long id) {

		return ResponseEntity.ok().body(company);
	}

	@PostMapping(value = "/company")
	public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyDto) {
	
		return companyService.save(companyDto);
	}*/
	
		
	@PutMapping(value = "/company/{id}")
	public @ResponseBody ResponseEntity<Company> update(@PathVariable Long id,
			@Valid @RequestBody CompanyDTO companyDto) {
		
		return companyService.updateCompany(id, companyDto);

	}


	@PutMapping(value = "company/active/{id}")
	@ResponseBody
	public void update(Company company) {
		companyService.updateActive(company.getId());
	}
	 

	@DeleteMapping(value = "/company/{id}")
	public String deleteCompany(@PathVariable("id") Long id) {
		
		companyService.deleteCompany(id);
		return "(\"deleted successfully!\");";
	}


}
