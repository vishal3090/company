package com.adix.company.mapper;

import com.adix.company.dto.CompanyDTO;
import com.adix.company.model.Company;

public class CompanyMapper {

	public static Company DtoToEntity(CompanyDTO company) {
		return new Company().setCompanyName(company.getCompanyName())
				.setCompanyPhoneNumber(company.getCompanyPhoneNumber())
				.setOpeningDate(company.getOpeningDate())
				.setCompanyEmail(company.getCompanyEmail())
				.setCompanyPhoto(company.getCompanyPhoto());
	}

	public static CompanyDTO EntityToDto(Company company) {
		return new CompanyDTO().setCompanyName(company.getCompanyName())
				.setCompanyPhoneNumber(company.getCompanyPhoneNumber())
				.setOpeningDate(company.getOpeningDate())
				.setCompanyEmail(company.getCompanyEmail())
				.setCompanyPhoto(company.getCompanyPhoto());
	}
}
