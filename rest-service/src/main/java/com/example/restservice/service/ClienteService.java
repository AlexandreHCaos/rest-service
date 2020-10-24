package com.example.restservice.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.restservice.model.Cliente;
import com.example.restservice.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente salvar(Cliente cliente) {
		cliente.setCpf(tratamentoDeCpf(cliente.getCpf()));
		cliente.setIdade(calcularIdade(cliente.getData_de_nascimento()));
		return clienteRepository.save(cliente);
	}
	
	public Integer calcularIdade(String dataDeNascimento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataDeNascimentoFormatada = LocalDate.parse(dataDeNascimento, formatter);
		
		LocalDate today = LocalDate.now();
		Period periodo = Period.between(dataDeNascimentoFormatada, today);

		return periodo.getYears();
	}
	
	public String tratamentoDeCpf(String cpf) {
		return cpf.replace(".", "").replace("-", "");
	}
	
}
