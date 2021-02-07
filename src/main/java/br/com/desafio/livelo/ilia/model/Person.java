package br.com.desafio.livelo.ilia.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Person ID")
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 30)
	@ApiModelProperty(value = "Person name")
	private String name;
	
	@NotNull
	@Size(min = 11, max = 14)
	@ApiModelProperty(value = "Person CPF or CPNJ")
	private String cpfCnpj;
	
	@NotNull
	@ApiModelProperty(value = "Person status")
	private Boolean status;
	
	@Embedded
	@ApiModelProperty(value = "Person address")
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	
}
