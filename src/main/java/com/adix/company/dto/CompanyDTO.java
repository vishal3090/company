package com.adix.company.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CompanyDTO {
	
	private Long id;
	
	//@NotBlank(message = "Company name is required")
	private String companyName;
	
	//@NotNull
	private Long companyPhoneNumber;

	private Date openingDate;
	
	//@NotBlank(message = "Company email is required")
	private String companyEmail;
	
	private String companyPhoto;
	
	@Column(name = "actived")
	private Boolean actived;
}
