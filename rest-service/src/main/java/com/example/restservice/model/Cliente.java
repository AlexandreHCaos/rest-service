package com.example.restservice.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cliente {
	private Long id;
	private String name;
	private String cpf;
	private String data_de_nascimento;
	private Integer idade;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getData_de_nascimento() {
		return data_de_nascimento;
	}
	public void setData_de_nascimento(String data_de_nascimento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate nova_data_de_nascimento = LocalDate.parse(data_de_nascimento, formatter);
		
		LocalDate today = LocalDate.now();
		Period periodo = Period.between(nova_data_de_nascimento, today);

		setIdade(periodo.getYears());
		this.data_de_nascimento = data_de_nascimento;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
