package com.adix.company.service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adix.company.dto.CompanyDTO;
import com.adix.company.exception.CompanyNotFoundException;
import com.adix.company.mapper.CompanyMapper;
import com.adix.company.model.Company;
import com.adix.company.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> getCompany() {

		return companyRepository.findAll();
	}

	/*@Override
	public Optional<Company> findById(Long id) {

		return companyRepository.findById(id);
	}*/
	public ResponseEntity<Company> getById(@PathVariable("id") Long id) {

		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new CompanyNotFoundException("No Product with ID : " + id));

		return ResponseEntity.ok().body(company);
	}

	/*@Transactional
	@Override
	public Company save(Company company) {

		return companyRepository.save(company);
	}*/
	

	@Transactional
	//@Override
	public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyDto) {
		Company company = CompanyMapper.DtoToEntity(companyDto);
		Company addCompany = companyRepository.save(company);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addCompany.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id,
			@Valid @RequestBody CompanyDTO companyDto) {

		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new CompanyNotFoundException("No Product with ID : " + id));

		Company newCompany = CompanyMapper.DtoToEntity(companyDto);
		newCompany.setId(company.getId());
		companyRepository.save(newCompany);
		return ResponseEntity.ok().body(newCompany);

	}

	/*@Override
	public void delete(Long id) {
		
		companyRepository.deleteById(id);
	}*/
	
	@Override
	public void deleteCompany(@PathVariable("id") Long id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new CompanyNotFoundException("No Product with ID : " + id));

		companyRepository.deleteById(company.getId());
	}

	@Override
	public void updateActive(Long id) {
		
		companyRepository.updateActive(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@Override
	public Company save(CompanyDTO companyDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
