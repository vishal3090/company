package com.adix.company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "company")
@SQLDelete(sql = "UPDATE company SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_phone_number")
	private Long companyPhoneNumber;

	@Column(name = "date_of_opening")
	private Date openingDate;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_photo")
	private String companyPhoto;

	@Column(name = "deleted")
	private boolean deleted = Boolean.FALSE;

}
