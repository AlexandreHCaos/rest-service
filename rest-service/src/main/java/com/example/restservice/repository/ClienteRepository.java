package com.example.restservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restservice.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public Page<Cliente> findByNameLike(@Param("name") String name, Pageable pageable);
	public Page<Cliente> findByCpf(@Param("cpf") String cpf, Pageable pageable);
	public Page<Cliente> findAll(Pageable pageable);
}
