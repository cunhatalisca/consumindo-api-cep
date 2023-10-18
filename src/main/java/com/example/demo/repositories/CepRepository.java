package com.example.demo.repositories;

import com.example.demo.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Endereco, Long> {
}
