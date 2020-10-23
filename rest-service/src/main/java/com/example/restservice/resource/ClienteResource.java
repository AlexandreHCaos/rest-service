package com.example.restservice.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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
	
	@GetMapping(produces= { MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public Page<Cliente> Buscar(@RequestParam(required = false, defaultValue = "0") int page) {
		System.out.println(page);
		return this.clienteRepository.findAll(PageRequest.of(page, 5));
	}
	
	@GetMapping(path="/nome",
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Page<Cliente> BuscarPorNome(@RequestParam String name, 
			@RequestParam(required = false, defaultValue = "0") int page) {
		return this.clienteRepository.findByNameLike("%"+name+"%", PageRequest.of(page, 5));
	}
	
	@GetMapping(path="/cpf", 
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Page<Cliente> BuscarPorCpf(@RequestParam String cpf, 
			@RequestParam(required = false, defaultValue = "0") int page) {
		return this.clienteRepository.findByCpf(cpf, PageRequest.of(page, 5));
	}

	@PostMapping(produces={ MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public void Criar(@RequestBody Cliente cliente) {
		cliente.setCpf(cliente.getCpf().replace(".", "").replace("-", ""));
		this.clienteRepository.save(cliente);
	}
	
	@DeleteMapping(path="/{id}", 
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	public void Remover(@PathVariable Long id) {
		this.clienteRepository.deleteById(id);
	}
	
	@PutMapping(produces={ MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public void Atualizar(@RequestBody Cliente cliente) {
		cliente.setCpf(cliente.getCpf().replace(".", "").replace("-", ""));
		this.clienteRepository.save(cliente);
	}
	
}
