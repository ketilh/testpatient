/**
 * 
 */
package no.hnikt.testpatient.model;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Ketil Holden
 *
 */
public class TestPatient {
	private String firstname; 
	private String lastname; 
	private LocalDate birthDate; 
	private String address; 
	private String zipCode; 
	private String postOffice;
	private MaritalStatus maritalStatus; 
	private String phone; 
	private String email; 
	private Education education;
	private Integer sex; 
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPostOffice() {
		return postOffice;
	}
	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	@JsonIgnore
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public Integer getMaritalStatusValue() {
		return maritalStatus.getValue();
	}
	public String getMaritalStatusDesc() {
		return maritalStatus.getDescription();
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public Education getEducation() {
		return education;
	}
	public Integer getEducationValue() {
		return education.getValue();
	}
	public String getEducationDesc() {
		return education.getDescription();
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	} 
	public Integer getAge() {
		LocalDate now = LocalDate.now();
		
		Period period = Period.between(birthDate, now);
		return period.getYears();
	}
}
