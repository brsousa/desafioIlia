package br.com.desafio.livelo.ilia.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "public_place")
	private String publicPlace;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	
	public String getPublicPlace() {
		return publicPlace;
	}
	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
