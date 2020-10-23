package com.example.restservice.resource;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Cliente;
import com.example.restservice.repository.ClienteRepository;

@RestController
@RequestMapping(value="/v1/clientes")
public class ClienteResource {

	private final ClienteRepository clienteRepository;
	
	public ClienteResource(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@GetMapping
	public List<Cliente> Buscar() {
		return this.clienteRepository.findAll();
	}
	
	@GetMapping("/nome")
	@ResponseBody
	public List<Cliente> BuscarPorNome(@RequestParam String name ) {
		return this.clienteRepository.findByNameLike("%"+name+"%");
	}
	
	@GetMapping("/cpf")
	@ResponseBody
	public List<Cliente> BuscarPorCpf(@RequestParam String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}

	@PostMapping
	public void Criar(@RequestBody Cliente cliente) {
		cliente.setCpf(cliente.getCpf().replace(".", "").replace("-", ""));
		this.clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	public void Remover(@PathVariable Long id) {
		this.clienteRepository.deleteById(id);
	}
	
	@PutMapping
	public void Atualizar(@RequestBody Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
	
}
