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
import com.example.restservice.service.ClienteService;

@RestController
@RequestMapping(value="/v1/clientes")
public class ClienteResource {

	private final ClienteRepository clienteRepository;
	private final ClienteService clienteService;
	
	public ClienteResource(ClienteRepository clienteRepository, ClienteService clienteService) {
		this.clienteRepository = clienteRepository;
		this.clienteService = clienteService;
	}
	
	@GetMapping(produces= { MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE })
	public Page<Cliente> Buscar(@RequestParam(required = false, defaultValue = "0") int page) {
		return this.clienteRepository.findAll(PageRequest.of(page, 5));
	}
	
	@GetMapping(path="/cpf/{cpf}", 
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Page<Cliente> BuscarPorCpf(@PathVariable String cpf, 
			@RequestParam(required = false, defaultValue = "0") int page) {
		return this.clienteRepository.findByCpf(cpf, PageRequest.of(page, 5));
	}

	@GetMapping(path="/nome/{name}",
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Page<Cliente> BuscarPorNome(@PathVariable String name, 
			@RequestParam(required = false, defaultValue = "0") int page) {
		return this.clienteRepository.findByNameLike(name, PageRequest.of(page, 5));
	}
	

	@PostMapping(produces={ MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	public Cliente Criar(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
	}
	
	@DeleteMapping(path="/{id}", 
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	public void Remover(@PathVariable Long id) {
		this.clienteRepository.deleteById(id);
	}
	
	@PutMapping(produces={ MediaType.APPLICATION_JSON_VALUE, 
				MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	public Cliente Atualizar(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
	}
	
}
