package com.example.restservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restservice.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public List<Cliente> findByNameLike(@Param("name") String name);
	public List<Cliente> findByCpf(@Param("cpf") String cpf);
}
